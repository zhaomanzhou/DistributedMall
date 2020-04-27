package com.zmz.mq;

import com.alibaba.fastjson.JSON;
import com.zmz.entity.po.Cart;
import com.zmz.entity.po.Order;
import com.zmz.entity.po.OrderItem;
import com.zmz.entity.po.OrderLog;
import com.zmz.entity.vo.ProductDetailVo;
import com.zmz.mapper.OrderItemMapper;
import com.zmz.mapper.OrderLogMapper;
import com.zmz.response.error.BizException;
import com.zmz.response.error.BusinessException;
import com.zmz.service.ICartService;
import com.zmz.service.IOrderService;
import com.zmz.service.IProductService;
import com.zmz.service.impl.OrderServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.*;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class MqProducer
{
    private DefaultMQProducer producer;

    private TransactionMQProducer transactionMQProducer;

    @Value("${mq.nameserver.addr}")
    private String nameServ;

    @Value("${mq.topic}")
    private String topic;

    @Value("${mq.cleanCartTopic}")
    private String cleanCartTopic;

    @Value("${mq.decreaseStockTopic}")
    private String decreaseStockTopic;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Reference
    private IProductService productService;

    @Autowired
    private OrderServiceImpl orderService;

    @Resource
    private OrderItemMapper orderItemMapper;

    @Resource
    private OrderLogMapper logMapper;

    @Reference
    private ICartService cartService;

    @PostConstruct
    public void init() throws MQClientException
    {
        producer = new DefaultMQProducer("producer_group");
        producer.setNamesrvAddr(nameServ);
        producer.start();

        transactionMQProducer = new TransactionMQProducer("producer_group");
        transactionMQProducer.setNamesrvAddr(nameServ);
        transactionMQProducer.setTransactionListener(new TransactionListener()
        {
            @Override
            public LocalTransactionState executeLocalTransaction(Message message, Object arg)
            {
                Integer userId = (Integer) ((Map)arg).get("userId");
                Integer shippingId = (Integer) ((Map)arg).get("shippingId");
                String logId = (String) ((Map)arg).get("logId");
                List<Cart> cartList = cartService.selectCheckedCartByUserId(userId);

                List<OrderItem> orderItemList = null;
                try
                {
                    orderItemList = orderService.getCartOrderItem(userId, cartList);
                    BigDecimal payment = orderService.getOrderTotalPrice(orderItemList);


                    //生成订单
                    Order order = orderService.assembleOrder(userId,shippingId,payment);


                    for(OrderItem orderItem : orderItemList){
                        orderItem.setOrderNo(order.getOrderNo());
                    }
                    //mybatis 批量插入
                    orderItemMapper.batchInsert(orderItemList);
                    OrderLog log = new OrderLog();
                    log.setId(logId);
                    log.setOrderId(order.getId());
                    log.setStatus(1);
                    logMapper.updateByPrimaryKey(log);

                } catch (BizException | BusinessException e)
                {
                    e.printStackTrace();
                    OrderLog log = new OrderLog();
                    log.setId(logId);
                    log.setStatus(2);
                    logMapper.updateByPrimaryKey(log);
                    return LocalTransactionState.ROLLBACK_MESSAGE;
                }

                return LocalTransactionState.COMMIT_MESSAGE;

            }

            @Override
            public LocalTransactionState checkLocalTransaction(MessageExt msg)
            {
                String jsonString  = new String(msg.getBody());
                Map<String,Object>map = JSON.parseObject(jsonString, Map.class);
                Integer userId = (Integer) ((Map)map).get("userId");
                Integer shippingId = (Integer) ((Map)map).get("shippingId");
                String logId = (String) ((Map)map).get("logId");
                OrderLog log = logMapper.selectByPrimaryKey(logId);
                if(log.getStatus() == null)
                {
                    return LocalTransactionState.UNKNOW;
                }else if(log.getStatus() == 1)
                {
                    return LocalTransactionState.COMMIT_MESSAGE;
                }else if(log.getStatus() == 2)
                {
                    return LocalTransactionState.ROLLBACK_MESSAGE;
                }
                return LocalTransactionState.UNKNOW;
            }
        });

        transactionMQProducer.start();
    }

    public boolean transactionAsyncReduceStock(Integer userId, Integer shippingId, List<OrderItem> orderItemList, String logId)
    {



        //计算这个订单的总价
        try
        {
            List<Cart> cartList = cartService.selectCheckedCartByUserId(userId);

            Map<String,Object> bodyMap = new HashMap<>();
            bodyMap.put("orderItemList",orderItemList);
            bodyMap.put("logId", logId);

            Map<String,Object> argsMap = new HashMap<>();
            argsMap.put("shippingId",shippingId);
            argsMap.put("userId",userId);
            argsMap.put("logId", logId);

            Message message = new Message(decreaseStockTopic,"increase",
                    JSON.toJSON(bodyMap).toString().getBytes(Charset.forName("UTF-8")));
            TransactionSendResult sendResult = null;

            sendResult = transactionMQProducer.sendMessageInTransaction(message,argsMap);


            if(sendResult.getLocalTransactionState() == LocalTransactionState.ROLLBACK_MESSAGE){
                return false;
            }else if(sendResult.getLocalTransactionState() == LocalTransactionState.COMMIT_MESSAGE){
                return true;
            }else{
                return false;
            }



        } catch (MQClientException e)
        {
            e.printStackTrace();
            OrderLog log = new OrderLog();
            log.setId(logId);
            log.setStatus(2);
            logMapper.updateByPrimaryKey(log);
            return false;
        }


    }

    private boolean preReduceProductStock(List<OrderItem> orderItemList) throws BizException, BusinessException
    {
        for(OrderItem orderItem : orderItemList){
            ProductDetailVo productDetail = productService.getProductDetail(orderItem.getProductId());
            Long result = redisTemplate.opsForValue().decrement("product_stock" + productDetail.getId(), productDetail.getStock() - orderItem.getQuantity());
            if(result == 0){
                return false;
            }
            productService.reduceStock(productDetail.getId(), productDetail.getStock()-orderItem.getQuantity());
        }
        return true;
    }


    public void sendCleanCartMessage(Integer userId)
    {
        Message message = new Message(cleanCartTopic, "tag", (userId + "").getBytes());
        try
        {
            producer.send(message);
        } catch (MQClientException | RemotingException | MQBrokerException | InterruptedException e)
        {
            log.info("send failed message {}", e);
            //TODO记录日志
        }
    }
}

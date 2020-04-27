package com.zmz.mq;

import com.alibaba.fastjson.JSON;
import com.zmz.entity.po.OrderItem;
import com.zmz.entity.vo.ProductDetailVo;
import com.zmz.service.IProductService;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

public class MqConsumer
{
    @Value("${mq.nameserver.addr}")
    private String nameServ;

    @Value("${mq.topic}")
    private String topic;
    private DefaultMQPushConsumer consumer;

    @Autowired
    private IProductService productService;

    @PostConstruct
    public void init() throws MQClientException
    {
        consumer = new DefaultMQPushConsumer();
        consumer.setNamesrvAddr(nameServ);
        consumer.subscribe(topic, "*");
        consumer.registerMessageListener(new MessageListenerConcurrently(){
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext)
            {
                for(MessageExt messageExt: list)
                {
                    String jsonString  = new String(messageExt.getBody());
                    Map<String,Object> map = JSON.parseObject(jsonString, Map.class);
                    List<OrderItem> orderItemList= (List<OrderItem>) map.get("orderItemList");
                    for(OrderItem orderItem: orderItemList)
                    {
                        productService.reduceStock(orderItem.getProductId(), orderItem.getQuantity());
                    }
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
    }
}

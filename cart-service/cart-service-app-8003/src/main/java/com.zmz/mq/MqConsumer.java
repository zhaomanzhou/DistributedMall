package com.zmz.mq;

import com.zmz.entity.po.Cart;
import com.zmz.entity.vo.CartVo;
import com.zmz.response.error.BizException;
import com.zmz.response.error.BusinessException;
import com.zmz.service.ICartService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class MqConsumer
{
    @Value("${mq.nameserver.addr}")
    private String nameServ;

    @Value("${mq.topic}")
    private String topic;


    private DefaultMQPushConsumer consumer;

    @Autowired
    private ICartService cartService;

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
                for (int i = 0; i < list.size(); i++)
                {
                    MessageExt messageExt = list.get(i);
                    int userId = Integer.parseInt(new String(messageExt.getBody()));
                    List<Cart> carts = cartService.selectCheckedCartByUserId(userId);
                    cartService.cleanCart(carts);


                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
    }
}

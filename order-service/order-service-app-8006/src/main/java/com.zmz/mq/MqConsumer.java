package com.zmz.mq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.util.List;

public class MqConsumer
{
    @Value("${mq.nameserver.addr}")
    private String nameServ;

    @Value("${mq.topic}")
    private String topic;
    private DefaultMQPushConsumer consumer;

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
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
    }
}

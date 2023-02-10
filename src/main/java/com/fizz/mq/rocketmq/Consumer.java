package com.fizz.mq.rocketmq;

import cn.hutool.core.date.DateUtil;
import com.fizz.utils.date.DateUtils;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Consumer {
    public static void main(String[] args) throws InterruptedException, MQClientException {
        // 初始化consumer，并设置consumer group name
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("consumerGroup-A");

        // 设置NameServer地址
        consumer.setNamesrvAddr("localhost:9876");
        //订阅一个或多个topic，并指定tag过滤条件，这里指定*表示接收所有tag的消息

//        consumer.subscribe("TopicTest-A", "*");
        consumer.subscribe("OrderTopicTest-A", "*");

        consumer.setConsumeMessageBatchMaxSize(12);
        System.out.printf("start at %s %n", DateUtil.formatDateTime(new Date()));
        //注册回调接口来处理从Broker中收到的消息
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                System.out.printf("%s收到 %s 条消息 %n", DateUtil.formatDateTime(new Date()),msgs.size());
                boolean flag = true;
                int index = 0;
                for (MessageExt msg : msgs) {
                    try {
                        System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(),
                                new String(msg.getBody()));
                        if (flag && index == 9) {
                            flag = false;
                            System.out.println(1/0);
                        }
                        index++;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                    }
                }
                // 返回消息消费状态，ConsumeConcurrentlyStatus.CONSUME_SUCCESS为消费成功
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        // 启动Consumer
        consumer.start();
        System.out.printf("Consumer Started.%n");
    }
}

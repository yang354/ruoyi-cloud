package com.ruoyi.rabbit.producer;

import com.ruoyi.common.core.utils.uuid.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.*;
import com.ruoyi.rabbit.enums.DelayTypeEnum;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import static com.ruoyi.rabbit.config.RabbitMQConfiguration.DELAY_EXCHANGE_NAME;
import static com.ruoyi.rabbit.config.RabbitMQConfiguration.DELAY_QUEUE_A_ROUTING_KEY;
import static com.ruoyi.rabbit.config.RabbitMQConfiguration.DELAY_QUEUE_B_ROUTING_KEY;

@Slf4j
@Component
public class DelayMessageProducer {

    @Resource
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * 向消息队列发消息
     * @param message 消息体
     * @param delayType 延迟类型
     */
    public void send(String message, DelayTypeEnum delayType) {
        String uniqueId = UUID.randomUUID().toString();
        MessageProperties properties = new MessageProperties();
        properties.setContentEncoding("utf-8");
        properties.setDeliveryMode(MessageDeliveryMode.PERSISTENT);
        Message messageHan = new Message(message.getBytes(), properties);
        switch (delayType) {
            case DELAY_30m:
                rabbitTemplate.convertAndSend(DELAY_EXCHANGE_NAME, DELAY_QUEUE_A_ROUTING_KEY, messageHan,new CorrelationData(uniqueId));
                break;
            case DELAY_60m:
                rabbitTemplate.convertAndSend(DELAY_EXCHANGE_NAME, DELAY_QUEUE_B_ROUTING_KEY, messageHan,new CorrelationData(uniqueId));
                break;
            default:
        }
    }


//    //发送学校提醒
//    public void sendArticleColumnNoticeDelayedMessage(String msg) {
//        try {
//            String msgId = UUID.randomUUID().toString();
//            MessageProperties messageProperties = new MessageProperties();
//            messageProperties.setMessageId(msgId);
//            messageProperties.setCorrelationId(msgId);
//            messageProperties.setDelay(10000); //延时时间（ms）
//            Message message = new Message(msg.getBytes(),messageProperties);
//            CorrelationData correlationData  = new CorrelationData();
//            correlationData.setId(msgId);
//            log.info("消息ID:{}",msgId);
//            log.info("内容：{}",message);
//            rabbitTemplate.convertAndSend("articleColumnNoticeDelayExchange", "articleColumnNoticeDelayedQueue.routingkey",message,correlationData);
//
//
//            //给延迟队列发送消息
//
//            log.info("MQ发送专栏提醒"+3);
//        }catch(Exception e){
//            log.error(e.getMessage());
//        }
//    }


    //发送学校提醒
    public void sendArticleColumnNoticeDelayedMessage(String message) {
        try {
//            if(articleColumnNoticeVO.getDelayedTime()<200){
//                articleColumnNoticeVO.setDelayedTime(200);
//            }
            //给延迟队列发送消息
            amqpTemplate.convertAndSend(
                    "articleColumnNoticeDelayExchange",
                    "articleColumnNoticeDelayedQueue.routingkey",
                    "articleColumnNoticeVO",
                    new MessagePostProcessor() {
                        public Message postProcessMessage(Message message) throws AmqpException {
                            message.getMessageProperties().setDelay(10000);
                            return message;
                        }
                    });
            log.info("MQ发送专栏提醒:{}"+4);
        }catch(Exception e){
            log.error(e.getMessage());
        }
    }


}

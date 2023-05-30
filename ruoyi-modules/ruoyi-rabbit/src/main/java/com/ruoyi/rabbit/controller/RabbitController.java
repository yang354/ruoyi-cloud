package com.ruoyi.rabbit.controller;

import com.ruoyi.rabbit.enums.DelayTypeEnum;
import com.ruoyi.rabbit.producer.DelayMessageProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("rabbitmq")
public class RabbitController {

    @Resource
    private DelayMessageProducer producer;



    /**
     * 向消息队列发消息
     */
    @GetMapping("/sendMQ/{message}/{delayType}")
    public void sendMQ(@PathVariable("message") String message,@PathVariable("delayType") int delayType) {
        log.info("当前时间：{}，消息：{}，延迟类型：{}", LocalDateTime.now(), message, delayType);
//        producer.send(message, Objects.requireNonNull(DelayTypeEnum.getDelayTypeEnum(delayType)));
        producer.sendArticleColumnNoticeDelayedMessage("vvvvv");
    }


}

package com.xtpeach.tiny.basics.api.server.kafka;

import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class SendMessageToTopic {

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    @Scheduled(cron = "0/5 * * * * ?")
    public void sendMessge() {
        String now = DateTime.now().toString("yyyy-MM-dd HH:mm:ss");
        log.info("send message:{}", now);
        kafkaTemplate.send("first_topic", now);
    }

}

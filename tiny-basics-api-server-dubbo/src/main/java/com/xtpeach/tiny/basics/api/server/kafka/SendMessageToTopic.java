package com.xtpeach.tiny.basics.api.server.kafka;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import org.joda.time.DateTime;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Random;

@Component
public class SendMessageToTopic {

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

//    @Scheduled(cron = "0/50 * * * * ?")
    public void sendMessge() {
        Map<String, String> data = Maps.newHashMap();
        Random random = new Random();
        data.put("metricTime", Long.toString(DateTime.now().toDate().getTime()));
        data.put("metricValue", Integer.toString(random.nextInt(100)));
        data.put("metricKey", "1101101110");
        data.put("metricTags", "{'uid':'110110868','name':'云平台裸金属数据库服务器125.215.193.23'}");
        kafkaTemplate.send("xtpeach_collect_index_data_1", JSON.toJSONString(data));
    }

}

package com.xxl.job.core.registry;

import com.alibaba.fastjson.JSONArray;
import com.netflix.discovery.EurekaClient;
import com.xxl.job.core.util.OSUtils;
import org.apache.commons.collections4.list.CursorableLinkedList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 通过 eureka 注册负载信息
 */
@Component
@ConditionalOnProperty(prefix = "xxl.job.executor", name = "title")
public class EurekaUpdateWeightManager {

    private final static Logger logger = LoggerFactory.getLogger(EurekaUpdateWeightManager.class);

    public final static String EXECUTOR_WEIGHT = "executor_weight";
    public final static String EXECUTOR_TITLE = "executor_title";
    public final static String EXECUTOR_HANDLER = "executor_handler";

    @Value("${xxl.job.executor.title:springBootApplication}")
    private String groupTitle;

    @Resource
    private EurekaClient eurekaClient;

    public final static List<String> xxlJobHandlerList = new CursorableLinkedList<>();

    private boolean stop = false;

    @PostConstruct
    public void init() {
        logger.info("更新支持任务信息>>>>>>>>>>>>>>>>>>>>>>>");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                logger.info("开始周期更新eureka负载信息任务>>>>>>>>>>>>>>>>>>>>>>>");
                while (!stop) {
                    try {
                        updateWeight();
                        Thread.sleep(5000);
                    } catch (Exception e) {
                        logger.error(e.getMessage(), e);
                    }
                }
                logger.info("结束周期更新eureka负载信息任务>>>>>>>>>>>>>>>>>>>>>>>");
            }
        });
        thread.setName("executor-weight-update-thread");
        thread.start();
    }

    @PreDestroy
    public void destroy() {
        stop = true;
    }

    public void updateWeight() {
        int weight = OSUtils.calculateWeight();
        logger.debug(">>> eureka 更新负载信息，当前负载：{}", weight);
        Map<String, String> metaData = eurekaClient.getApplicationInfoManager().getInfo().getMetadata();
        metaData.put(EXECUTOR_WEIGHT, String.valueOf(weight));
        metaData.put(EXECUTOR_TITLE, groupTitle);
        metaData.put(EXECUTOR_HANDLER, JSONArray.toJSONString(xxlJobHandlerList));
        eurekaClient.getApplicationInfoManager().registerAppMetadata(metaData);
    }
}

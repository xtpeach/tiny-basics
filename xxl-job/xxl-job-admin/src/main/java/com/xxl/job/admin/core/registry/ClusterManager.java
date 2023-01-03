package com.xxl.job.admin.core.registry;

import com.alibaba.fastjson.JSONArray;
import com.xtpeach.tiny.basics.common.module.entity.xxl.job.XxlJobGroupEntity;
import com.xtpeach.tiny.basics.common.module.entity.xxl.job.XxlJobRegistryEntity;
import com.netflix.discovery.EurekaClient;
import com.xxl.job.core.registry.EurekaUpdateWeightManager;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EurekaServiceInstance;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 通过 eureka 构建执行器地址
 */
@Component
public class ClusterManager {

    private final static Logger logger = LoggerFactory.getLogger(ClusterManager.class);

    /**
     * 本地 xxl-job-admin 缓存
     */
    private List<ServiceInstance> serviceInstanceList = Lists.newArrayList();

    /**
     * 当新的 xxl-job-admin 节点加入，先暂缓3个同步周期不执行任何触发
     */
    private final int initWaitCountTotal = 3;

    /**
     * 记录 xxl-job-admin 的同步队列次数
     */
    private int initWaitCount = 0;

    /**
     * 线程启停
     */
    private boolean stop = false;

    @Value("${spring.application.name}")
    private String applicationName;

    /**
     * eureka client
     */
    @Resource
    private EurekaClient eurekaClient;

    /**
     * eureka 服务发现 client
     */
    @Resource
    private EurekaDiscoveryClient discoveryClient;


    @PostConstruct
    public void init() {
        logger.info("更新 admin 队列>>>>>>>>>>>>>>>>>>>>>>>");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                logger.info("开始更新 admin 队列>>>>>>>>>>>>>>>>>>>>>>>");
                while (!stop) {
                    try {
                        updateAdminQueue();

                        // 5秒钟更新一次
                        Thread.sleep(5000);
                    } catch (Exception e) {
                        logger.error(e.getMessage(), e);
                    }
                }
                logger.info("结束更新 admin 队列>>>>>>>>>>>>>>>>>>>>>>>");
            }
        });
        thread.setName("admin-queue-update-thread");
        thread.start();
    }

    /**
     * xxl-job-admin 实例信息缓存到本地
     */
    public void updateAdminQueue() {
        serviceInstanceList = discoveryClient.getInstances(applicationName);
        if (initWaitCount < initWaitCountTotal) {
            initWaitCount++;
        }
    }

    /**
     * 初始化等待状态
     *
     * @return
     */
    public boolean getAdminInitWaitReady() {
        if (initWaitCount >= initWaitCountTotal) {
            return true;
        }
        return false;
    }

    @PreDestroy
    public void destroy() {
        stop = true;
    }

    /**
     * 获取 xxl-job-admin 实例数量
     *
     * @return
     */
    public Integer getAdminInstanceCount() {
        return CollectionUtils.isEmpty(serviceInstanceList) ? 1 : serviceInstanceList.size();
    }

    /**
     * 当前节点在注册中心里面所有 xxl-job-admin 实例中的排序位置
     *
     * @return
     */
    public Integer getAdminInstanceSortNum() {
        List<String> instanceIdList = serviceInstanceList.stream().map(serviceInstance -> serviceInstance.getInstanceId()).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(instanceIdList) || instanceIdList.size() == 1) {
            return 0;
        }
        String localInstanceId = eurekaClient.getApplicationInfoManager().getInfo().getInstanceId();
        Collections.sort(instanceIdList);
        for (int i = 0; i < instanceIdList.size(); i++) {
            if (StringUtils.equals(instanceIdList.get(i), localInstanceId)) {
                return i;
            }
        }
        return 0;
    }

    /**
     * 通过 eureka 获取执行器地址
     *
     * @param xxlJobGroup
     * @return
     */
    public XxlJobGroupEntity buildJobGroup(XxlJobGroupEntity xxlJobGroup) {
        if (0 == xxlJobGroup.getAddressType()) {
            List<ServiceInstance> list = discoveryClient.getInstances(xxlJobGroup.getAppName());
            if (CollectionUtils.isNotEmpty(list)) {
                Optional<String> optional = list.stream()
                        .map(x -> (EurekaServiceInstance) x)
                        .map(x -> "http://" + x.getInstanceInfo().getIPAddr() + ":" + x.getPort())
                        .reduce((x, y) -> x + "," + y);
                String addressList = optional.orElseGet(optional::get);
                xxlJobGroup.setAddressList(addressList);
                logger.info("通过eureka获取实例信息, 地址列表：[{} - {}]", xxlJobGroup.getAppName(), xxlJobGroup.getAddressList());
            }
        }
        return xxlJobGroup;
    }

    /**
     * 通过 eureka 获取执行器地址
     *
     * @param xxlJobGroup
     * @return
     */
    public List<XxlJobRegistryEntity> buildJobRegistry(XxlJobGroupEntity xxlJobGroup) {
        List<XxlJobRegistryEntity> xxlJobRegistryEntityList = null;
        if (0 == xxlJobGroup.getAddressType()) {
            List<ServiceInstance> list = discoveryClient.getInstances(xxlJobGroup.getAppName());
            if (CollectionUtils.isNotEmpty(list)) {
                xxlJobRegistryEntityList = list.stream()
                        .map(x -> (EurekaServiceInstance) x)
                        .map(x -> {
                            XxlJobRegistryEntity xxlJobRegistryEntity = new XxlJobRegistryEntity();
                            xxlJobRegistryEntity.setRegistryGroup(xxlJobGroup.getAppName());
                            xxlJobRegistryEntity.setRegistryKey(x.getInstanceId());
                            xxlJobRegistryEntity.setRegistryValue("http://" + x.getInstanceInfo().getIPAddr() + ":" + x.getPort());
                            xxlJobRegistryEntity.setCalculateWeight(MapUtils.getIntValue(x.getMetadata(), EurekaUpdateWeightManager.EXECUTOR_WEIGHT, 0));
                            return xxlJobRegistryEntity;
                        }).collect(Collectors.toList());
            }
        }
        return xxlJobRegistryEntityList;
    }

    /**
     * 获取 JobHandler 信息
     *
     * @param xxlJobGroup
     * @return
     */
    public List<String> buildJobHandlerList(XxlJobGroupEntity xxlJobGroup) {
        List<String> jobHandlerInnerBeanNameList = Lists.newArrayList();
        List<String> jobHandlerList = null;
        if (0 == xxlJobGroup.getAddressType()) {
            List<ServiceInstance> list = discoveryClient.getInstances(xxlJobGroup.getAppName());
            if (CollectionUtils.isNotEmpty(list)) {
                jobHandlerList = list.stream()
                        .map(x -> (EurekaServiceInstance) x)
                        .map(x -> MapUtils.getString(x.getMetadata(), EurekaUpdateWeightManager.EXECUTOR_HANDLER)
                        ).collect(Collectors.toList());
            }
        }

        if (CollectionUtils.isNotEmpty(jobHandlerList)) {
            jobHandlerList.stream().forEach(jobHandler
                    -> jobHandlerInnerBeanNameList.addAll(JSONArray.parseArray(jobHandler, String.class)));
        }

        return jobHandlerInnerBeanNameList;
    }

}

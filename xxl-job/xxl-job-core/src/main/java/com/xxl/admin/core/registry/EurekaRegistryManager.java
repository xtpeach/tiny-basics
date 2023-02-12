package com.xxl.admin.core.registry;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xtpeach.tiny.basics.common.module.entity.xxl.job.XxlJobGroupEntity;
import com.xtpeach.tiny.basics.common.module.entity.xxl.job.XxlJobInfoEntity;
import com.xtpeach.tiny.basics.common.module.entity.xxl.job.XxlJobRegistryEntity;
import com.xtpeach.tiny.basics.core.xxl.job.dao.XxlJobGroupDao;
import com.xtpeach.tiny.basics.core.xxl.job.dao.XxlJobInfoDao;
import com.xtpeach.tiny.basics.core.xxl.job.dao.XxlJobRegistryDao;
import com.xxl.admin.core.route.strategy.ExecutorRouteWeight;
import com.xxl.job.core.registry.UpdateWeightManager;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.netflix.eureka.EurekaClientAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EurekaServiceInstance;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * [自动通过 eureka 注册]
 * 通过刷新执行器注册信息
 */
@Component
@ConditionalOnClass(EurekaClientAutoConfiguration.class)
public class EurekaRegistryManager {

    private final static Logger logger = LoggerFactory.getLogger(EurekaRegistryManager.class);

    /**
     * 默认基础执行器（固定名称： xxl-job-executor-base）
     */
    private final static String XXL_JOB_EXECUTOR_BASE = "xxl-job-executor-base";

    /**
     * 执行器管理
     */
    @Resource
    private XxlJobGroupDao xxlJobGroupDao;

    /**
     * 注册地址管理
     */
    @Resource
    private XxlJobRegistryDao xxlJobRegistryDao;

    /**
     * 任务信息管理
     */
    @Resource
    private XxlJobInfoDao xxlJobInfoDao;

    /**
     * 通过 eureka 构建执行器地址
     */
    @Resource
    private ClusterManager clusterManager;

    /**
     * eureka 服务发现
     */
    @Resource
    private EurekaDiscoveryClient discoveryClient;

    /**
     * 线程启停
     */
    private boolean stop = false;

    @PostConstruct
    public void init() {
        logger.info("更新执行器>>>>>>>>>>>>>>>>>>>>>>>");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                logger.info("开始周期更新 eureka 执行器信息>>>>>>>>>>>>>>>>>>>>>>>");
                while (!stop) {
                    try {
                        // 让在注册列表排名第一位的 xxl-job-admin 执行注册
                        if (clusterManager.getAdminInstanceSortNum() == 0) {
                            updateExecutor();
                        }

                        // 5秒钟更新一次
                        Thread.sleep(5000);
                    } catch (Exception e) {
                        logger.error(e.getMessage(), e);
                    }
                }
                logger.info("结束周期更新 eureka 执行器信息>>>>>>>>>>>>>>>>>>>>>>>");
            }
        });
        thread.setName("executor-weight-update-thread");
        thread.start();
    }

    @PreDestroy
    public void destroy() {
        stop = true;
    }

    /**
     * 自动保存、更新、删除 xxl_job_group
     * 自动保存、更新、删除 xxl_job_registry
     * 自动插入 xxl_job_info
     */
    public void updateExecutor() {
        // 通过注册中心获取服务名，注册信息 metaData 里面包含 EXECUTOR_TITLE 为执行器服务
        List<String> serviceNameList = ListUtils.defaultIfNull(discoveryClient.getServices(), Lists.newArrayList())
                .stream().filter(serviceName -> {
                    List<ServiceInstance> serviceInstanceList = ListUtils.defaultIfNull(discoveryClient.getInstances(serviceName), Lists.newArrayList());
                    return CollectionUtils.isNotEmpty(serviceInstanceList.stream().map(x -> (EurekaServiceInstance) x)
                            .filter(x -> x.getMetadata().containsKey(UpdateWeightManager.EXECUTOR_TITLE)).collect(Collectors.toList()));
                }).collect(Collectors.toList());

        // 若注册中心，没有一个，已注册的有执行器能力的微服务，则清理执行器管理列表
        if (CollectionUtils.isEmpty(serviceNameList)) {
            // eureka 没有注册任何一个具有执行器能力的微服务，只保留一个 xxl-job-executor-base 默认初始化的执行器，其他历史执行器直接删掉
            QueryWrapper<XxlJobGroupEntity> xxlJobGroupEntityQueryWrapper = new QueryWrapper<>();
            // 保留基础执行器 xxl-job-executor-base
            xxlJobGroupEntityQueryWrapper.lambda().ne(XxlJobGroupEntity::getAppName, XXL_JOB_EXECUTOR_BASE);
            xxlJobGroupDao.delete(xxlJobGroupEntityQueryWrapper);
            xxlJobGroupEntityQueryWrapper = new QueryWrapper<>();
            xxlJobGroupEntityQueryWrapper.lambda().eq(XxlJobGroupEntity::getAppName, XXL_JOB_EXECUTOR_BASE);
            List<XxlJobGroupEntity> xxlJobGroupEntityList = xxlJobGroupDao.selectList(xxlJobGroupEntityQueryWrapper);
            xxlJobGroupEntityList.stream().forEach(xxlJobGroupEntity -> {
                xxlJobGroupEntity.setAddressList(null);
                xxlJobGroupDao.updateById(xxlJobGroupEntity);
            });
            xxlJobRegistryDao.delete(new QueryWrapper<>());
            return;
        }

        // 保存更新[执行器]
        serviceNameList.stream().forEach(serviceName -> {
            List<ServiceInstance> serviceInstanceList = discoveryClient.getInstances(serviceName);
            if (CollectionUtils.isNotEmpty(serviceInstanceList)) {
                EurekaServiceInstance serviceInstance = (EurekaServiceInstance) serviceInstanceList.get(0);
                // 查找已有，更新删除；查找不到，保存
                QueryWrapper<XxlJobGroupEntity> xxlJobGroupEntityQueryWrapper = new QueryWrapper<>();
                xxlJobGroupEntityQueryWrapper.lambda().eq(XxlJobGroupEntity::getAppName, serviceName);
                List<XxlJobGroupEntity> xxlJobGroupEntityList = xxlJobGroupDao.selectList(xxlJobGroupEntityQueryWrapper);
                XxlJobGroupEntity xxlJobGroupEntity;
                if (CollectionUtils.isNotEmpty(xxlJobGroupEntityList)) {
                    xxlJobGroupEntity = xxlJobGroupEntityList.remove(0);
                    xxlJobGroupEntityList.stream().forEach(item -> xxlJobGroupDao.deleteById(item));
                    xxlJobGroupEntity.setUpdateTime(DateTime.now().toDate());
                    clusterManager.buildJobGroup(xxlJobGroupEntity);
                    xxlJobGroupDao.updateById(xxlJobGroupEntity);
                } else {
                    xxlJobGroupEntity = new XxlJobGroupEntity();
                    xxlJobGroupEntity.setAppName(serviceName);
                    xxlJobGroupEntity.setAddressType(0);
                    xxlJobGroupEntity.setTitle(MapUtils.getString(serviceInstance.getMetadata(), UpdateWeightManager.EXECUTOR_TITLE, "springBootApplication"));
                    xxlJobGroupEntity.setCreateTime(DateTime.now().toDate());
                    xxlJobGroupEntity.setUpdateTime(DateTime.now().toDate());
                    xxlJobGroupEntity.setId(serviceName);
                    clusterManager.buildJobGroup(xxlJobGroupEntity);
                    xxlJobGroupDao.insert(xxlJobGroupEntity);
                }

                // 更新[执行器地址注册]列表
                List<XxlJobRegistryEntity> xxlJobRegistryEntityList = clusterManager.buildJobRegistry(xxlJobGroupEntity);
                if (CollectionUtils.isNotEmpty(xxlJobRegistryEntityList)) {
                    List<String> aliveRegistryIdList = Lists.newArrayList();
                    xxlJobRegistryEntityList.stream().forEach(xxlJobRegistryEntity -> {
                        QueryWrapper<XxlJobRegistryEntity> xxlJobRegistryEntityQueryWrapper = new QueryWrapper<>();
                        xxlJobRegistryEntityQueryWrapper.lambda().eq(XxlJobRegistryEntity::getRegistryGroup, xxlJobRegistryEntity.getRegistryGroup());
                        xxlJobRegistryEntityQueryWrapper.lambda().eq(XxlJobRegistryEntity::getRegistryKey, xxlJobRegistryEntity.getRegistryKey());
                        List<XxlJobRegistryEntity> xxlJobRegistryEntityListOld = xxlJobRegistryDao.selectList(xxlJobRegistryEntityQueryWrapper);
                        if (CollectionUtils.isNotEmpty(xxlJobRegistryEntityListOld)) {
                            xxlJobRegistryEntityListOld.stream().forEach(xxlJobRegistryEntityOld -> {
                                xxlJobRegistryEntityOld.setRegistryValue(xxlJobRegistryEntity.getRegistryValue());
                                xxlJobRegistryEntityOld.setCalculateWeight(xxlJobRegistryEntity.getCalculateWeight());
                                xxlJobRegistryEntityOld.setUpdateTime(DateTime.now().toDate());
                                xxlJobRegistryDao.updateById(xxlJobRegistryEntityOld);
                                xxlJobRegistryEntity.setId(xxlJobRegistryEntityOld.getId());
                                ExecutorRouteWeight.addressWeight.put(xxlJobRegistryEntityOld.getRegistryValue(), xxlJobRegistryEntityOld.getCalculateWeight());
                            });
                        } else {
                            xxlJobRegistryDao.insert(xxlJobRegistryEntity);
                            ExecutorRouteWeight.addressWeight.put(xxlJobRegistryEntity.getRegistryValue(), xxlJobRegistryEntity.getCalculateWeight());
                        }
                        aliveRegistryIdList.add(xxlJobRegistryEntity.getId());
                    });
                    QueryWrapper<XxlJobRegistryEntity> xxlJobRegistryEntityQueryWrapper = new QueryWrapper<>();
                    xxlJobRegistryEntityQueryWrapper.lambda().eq(XxlJobRegistryEntity::getRegistryGroup, xxlJobGroupEntity.getAppName());
                    xxlJobRegistryEntityQueryWrapper.lambda().notIn(XxlJobRegistryEntity::getId, aliveRegistryIdList);
                    xxlJobRegistryDao.selectList(xxlJobRegistryEntityQueryWrapper).stream().forEach(xxlJobRegistryEntity
                            -> ExecutorRouteWeight.addressWeight.remove(xxlJobRegistryEntity.getRegistryValue()));
                    xxlJobRegistryDao.delete(xxlJobRegistryEntityQueryWrapper);
                } else {
                    QueryWrapper<XxlJobRegistryEntity> xxlJobRegistryEntityQueryWrapper = new QueryWrapper<>();
                    xxlJobRegistryEntityQueryWrapper.lambda().eq(XxlJobRegistryEntity::getRegistryGroup, xxlJobGroupEntity.getAppName());
                    xxlJobRegistryDao.delete(xxlJobRegistryEntityQueryWrapper);
                }

                // 保存更新[任务信息]
                List<String> jobHandlerList = clusterManager.buildJobHandlerList(xxlJobGroupEntity);
                jobHandlerList.stream().forEach(jobHandler -> {
                    QueryWrapper<XxlJobInfoEntity> queryWrapper = new QueryWrapper<>();
                    queryWrapper.lambda().eq(XxlJobInfoEntity::getAuthor, serviceName);
                    queryWrapper.lambda().eq(XxlJobInfoEntity::getExecutorHandler, jobHandler);
                    List<XxlJobInfoEntity> xxlJobInfoEntityList = xxlJobInfoDao.selectList(queryWrapper);
                    if (CollectionUtils.isEmpty(xxlJobInfoEntityList)) {
                        XxlJobInfoEntity xxlJobInfoEntity = new XxlJobInfoEntity();
                        xxlJobInfoEntity.setAuthor(serviceName);
                        xxlJobInfoEntity.setExecutorBlockStrategy(StringUtils.upperCase("SERIAL_EXECUTION"));
                        xxlJobInfoEntity.setExecutorHandler(jobHandler);
                        xxlJobInfoEntity.setExecutorRouteStrategy(StringUtils.upperCase("WEIGHT"));
                        xxlJobInfoEntity.setExecutorTimeout(0);
                        xxlJobInfoEntity.setGlueRemark("GLUE代码初始化");
                        xxlJobInfoEntity.setGlueType("BEAN");
                        xxlJobInfoEntity.setJobDesc(serviceName + "-自动注册任务");
                        xxlJobInfoEntity.setJobGroup(xxlJobGroupEntity.getAppName());
                        xxlJobInfoEntity.setMisfireStrategy(StringUtils.upperCase("DO_NOTHING"));
                        xxlJobInfoEntity.setScheduleType(StringUtils.upperCase("NONE"));
                        xxlJobInfoEntity.setGlueUpdatetime(DateTime.now().toDate());
                        xxlJobInfoDao.insert(xxlJobInfoEntity);
                    }
                });
            }
        });
    }

}

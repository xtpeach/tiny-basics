package com.xxl.admin.core.registry;

import com.xtpeach.tiny.basics.common.module.entity.xxl.job.XxlJobGroupEntity;
import com.xtpeach.tiny.basics.common.module.entity.xxl.job.XxlJobRegistryEntity;

import java.util.List;

/**
 * 集群管理
 * ClusterManager
 */
public interface ClusterManager {

    /**
     * 初始化等待状态
     *
     * @return
     */
    boolean getAdminInitWaitReady();

    /**
     * 获取 xxl-job-admin 实例数量
     *
     * @return
     */
    Integer getAdminInstanceCount();

    /**
     * 当前节点在注册中心里面所有 xxl-job-admin 实例中的排序位置
     *
     * @return
     */
    Integer getAdminInstanceSortNum();

    /**
     * 通过 eureka 获取执行器地址
     *
     * @param xxlJobGroup
     * @return
     */
    XxlJobGroupEntity buildJobGroup(XxlJobGroupEntity xxlJobGroup);

    /**
     * 通过 eureka 获取执行器地址
     *
     * @param xxlJobGroup
     * @return
     */
    List<XxlJobRegistryEntity> buildJobRegistry(XxlJobGroupEntity xxlJobGroup);

    /**
     * 获取 JobHandler 信息
     *
     * @param xxlJobGroup
     * @return
     */
    List<String> buildJobHandlerList(XxlJobGroupEntity xxlJobGroup);

}

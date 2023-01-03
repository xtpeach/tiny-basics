package com.xxl.job.admin.core.alarm;

import com.xtpeach.tiny.basics.common.module.entity.xxl.job.XxlJobInfoEntity;
import com.xtpeach.tiny.basics.common.module.entity.xxl.job.XxlJobLogEntity;

/**
 * @author xuxueli 2020-01-19
 */
public interface JobAlarm {

    /**
     * job alarm
     *
     * @param info
     * @param jobLog
     * @return
     */
    public boolean doAlarm(XxlJobInfoEntity info, XxlJobLogEntity jobLog);

}

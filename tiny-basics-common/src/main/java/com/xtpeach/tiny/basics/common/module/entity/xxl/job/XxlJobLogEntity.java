package com.xtpeach.tiny.basics.common.module.entity.xxl.job;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xtpeach.tiny.basics.common.module.core.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * 表名： xxl_job_log
 * ------------------------------------------------------------
 * 字段：id                        varchar (32)        pk           主键编码
 * 字段：job_group                 varchar (32)        not null     执行器主键ID
 * 字段：job_id                    varchar (32)        not null     任务，主键ID
 * 字段：executor_address          varchar (255)                    执行器地址，本次执行的地址
 * 字段：executor_handler          varchar (255)                    执行器任务handler
 * 字段：executor_param            text                             执行器任务参数
 * 字段：executor_sharding_param   varchar (20)                     执行器任务分片参数，格式如 1/2
 * 字段：executor_fail_retry_count int (32)                         失败重试次数
 * 字段：trigger_time              timestamp (6)                    调度-时间
 * 字段：trigger_code              int (32)            not null     调度-结果
 * 字段：trigger_msg               text                             调度-日志
 * 字段：handle_time               timestamp (6)                    执行-时间
 * 字段：handle_code               int (32)            not null     执行-状态
 * 字段：handle_msg                text                             执行-日志
 * 字段：alarm_status              int (32)                         告警状态：0-默认、1-无需告警、2-告警成功、3-告警失败
 * 字段：create_time               timestamp (6)                    创建时间
 * 字段：update_time               timestamp (6)                    更新时间
 * ------------------------------------------------------------
 */

//lombok
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

// jpa-hibernate
@Entity
@Table(
        name = "xxl_job_log"
)

// mybatis-plus
@TableName("xxl_job_log")
public class XxlJobLogEntity extends BaseEntity {

    @Column(name = "job_group", length = 32, nullable = false)
    @Excel(name = "job_group", orderNum = "3")
    private String jobGroup;

    @Column(name = "job_id", length = 32, nullable = false)
    @Excel(name = "job_id", orderNum = "4")
    private String jobId;

    @Column(name = "executor_address", length = 255)
    @Excel(name = "executor_address", orderNum = "5")
    private String executorAddress;

    @Column(name = "executor_handler", length = 255)
    @Excel(name = "executor_handler", orderNum = "6")
    private String executorHandler;

    @Column(name = "executor_param", columnDefinition = "TEXT")
    @Excel(name = "executor_param", orderNum = "7")
    private String executorParam;

    @Column(name = "executor_sharding_param", length = 20)
    @Excel(name = "executor_sharding_param", orderNum = "8")
    private String executorShardingParam;

    @Column(name = "executor_fail_retry_count")
    @Excel(name = "executor_fail_retry_count", orderNum = "9")
    private Integer executorFailRetryCount;

    @Column(name = "trigger_time")
    @Excel(name = "trigger_time", orderNum = "10", importFormat = "yyyy-MM-dd HH:mm:ss", exportFormat = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date triggerTime;

    @Column(name = "trigger_code", nullable = false)
    @Excel(name = "trigger_code", orderNum = "11")
    private Integer triggerCode = 0;

    @Column(name = "trigger_msg", columnDefinition = "TEXT")
    @Excel(name = "trigger_msg", orderNum = "12")
    private String triggerMsg;

    @Column(name = "handle_time")
    @Excel(name = "handle_time", orderNum = "13", importFormat = "yyyy-MM-dd HH:mm:ss", exportFormat = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date handleTime;

    @Column(name = "handle_code", nullable = false)
    @Excel(name = "handle_code", orderNum = "14")
    private Integer handleCode = 0;

    @Column(name = "handle_msg", columnDefinition = "TEXT")
    @Excel(name = "handle_msg", orderNum = "15")
    private String handleMsg;

    @Column(name = "alarm_status")
    @Excel(name = "alarm_status", orderNum = "16")
    private Integer alarmStatus;

}

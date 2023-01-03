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
 * 表名： xxl_job_info
 * ------------------------------------------------------------
 * 字段：id                        varchar (32)        pk           主键编码
 * 字段：job_group                 varchar (32)        not null     执行器主键ID
 * 字段：job_desc                  varchar (2000)      not null     任务描述
 * 字段：author                    varchar (64)                     作者
 * 字段：alarm_email               varchar (255)                    报警邮件
 * 字段：schedule_type             varchar (50)        not null     调度类型
 * 字段：schedule_conf             varchar (128)                    调度配置，值含义取决于调度类型
 * 字段：misfire_strategy          varchar (50)        not null     调度过期策略
 * 字段：executor_route_strategy   varchar (50)                     执行器路由策略
 * 字段：executor_handler          varchar (255)                    执行器任务handler
 * 字段：executor_param            varchar (1024)                   执行器任务参数
 * 字段：executor_block_strategy   varchar (50)                     阻塞处理策略
 * 字段：executor_timeout          int (32)            not null     任务执行超时时间，单位秒
 * 字段：executor_fail_retry_count int (32)            not null     失败重试次数
 * 字段：glue_type                 varchar (50)        not null     失败重试次数
 * 字段：glue_source               text                             GLUE源代码
 * 字段：glue_remark               varchar (128)                    GLUE备注
 * 字段：glue_updatetime           timestamp (6)                    GLUE更新时间
 * 字段：child_job_id              varchar (2000)                   子任务ID，多个逗号分隔
 * 字段：trigger_status            int (32)            not null     调度状态：0-停止，1-运行
 * 字段：trigger_last_time         int (64)            not null     上次调度时间
 * 字段：trigger_next_time         int (64)            not null     下次调度时间
 * 字段：is_task                   bool                             调度规则task
 * 字段：task_id                   int (32)                         父任务id
 * 字段：task_type                 varchar (50)                     任务类型
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
        name = "xxl_job_info"
)

// mybatis-plus
@TableName("xxl_job_info")
public class XxlJobInfoEntity extends BaseEntity {

    @Column(name = "job_group", length = 32, nullable = false)
    @Excel(name = "job_group", orderNum = "3")
    private String jobGroup;

    @Column(name = "job_desc", length = 2000, nullable = false)
    @Excel(name = "job_desc", orderNum = "4")
    private String jobDesc;

    @Column(name = "author", length = 64)
    @Excel(name = "author", orderNum = "5")
    private String author;

    @Column(name = "alarm_email", length = 255)
    @Excel(name = "alarm_email", orderNum = "6")
    private String alarmEmail;

    @Column(name = "schedule_type", length = 50, nullable = false)
    @Excel(name = "schedule_type", orderNum = "7")
    private String scheduleType;

    @Column(name = "schedule_conf", length = 128)
    @Excel(name = "schedule_conf", orderNum = "8")
    private String scheduleConf;

    @Column(name = "misfire_strategy", length = 50, nullable = false)
    @Excel(name = "misfire_strategy", orderNum = "9")
    private String misfireStrategy;

    @Column(name = "executor_route_strategy", length = 50)
    @Excel(name = "executor_route_strategy", orderNum = "10")
    private String executorRouteStrategy;

    @Column(name = "executor_handler", length = 255)
    @Excel(name = "executor_handler", orderNum = "11")
    private String executorHandler;

    @Column(name = "executor_param", columnDefinition = "TEXT")
    @Excel(name = "executor_param", orderNum = "12")
    private String executorParam;

    @Column(name = "executor_block_strategy", length = 50)
    @Excel(name = "executor_block_strategy", orderNum = "13")
    private String executorBlockStrategy;

    @Column(name = "executor_timeout", nullable = false)
    @Excel(name = "executor_timeout", orderNum = "14")
    private Integer executorTimeout;

    @Column(name = "executor_fail_retry_count", nullable = false)
    @Excel(name = "executor_fail_retry_count", orderNum = "14")
    private Integer executorFailRetryCount = 0;

    @Column(name = "glue_type", length = 50, nullable = false)
    @Excel(name = "glue_type", orderNum = "15")
    private String glueType;

    @Column(name = "glue_source", columnDefinition = "TEXT")
    @Excel(name = "glue_source", orderNum = "16")
    private String glueSource;

    @Column(name = "glue_remark", length = 128)
    @Excel(name = "glue_remark", orderNum = "17")
    private String glueRemark;

    @Column(name = "glue_updatetime", length = 128)
    @Excel(name = "glue_updatetime", orderNum = "18", importFormat = "yyyy-MM-dd HH:mm:ss", exportFormat = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date glueUpdatetime;

    @Column(name = "child_job_id", length = 2000)
    @Excel(name = "child_job_id", orderNum = "19")
    private String childJobId;

    @Column(name = "trigger_status", nullable = false)
    @Excel(name = "trigger_status", orderNum = "20")
    private Integer triggerStatus = 0;

    @Column(name = "trigger_last_time", nullable = false)
    @Excel(name = "trigger_last_time", orderNum = "21")
    private Long triggerLastTime = 0L;

    @Column(name = "trigger_next_time", nullable = false)
    @Excel(name = "trigger_next_time", orderNum = "22")
    private Long triggerNextTime = 0L;

    @Column(name = "is_task")
    @Excel(name = "is_task", orderNum = "23")
    private Boolean isTask;

    @Column(name = "task_id")
    @Excel(name = "task_id", orderNum = "24")
    private Integer taskId;

    @Column(name = "task_type", length = 50)
    @Excel(name = "task_type", orderNum = "25")
    private String taskType;

}

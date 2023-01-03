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
 * 表名： xxl_job_log_report
 * ------------------------------------------------------------
 * 字段：id                        varchar (32)        pk           主键编码
 * 字段：trigger_day               timestamp (6)                    任务，主键ID
 * 字段：running_count             int (32)            not null     运行中-日志数量
 * 字段：suc_count                 int (32)            not null     执行成功-日志数量
 * 字段：fail_count                int (32)            not null     执行失败-日志数量
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
        name = "xxl_job_log_report"
)

// mybatis-plus
@TableName("xxl_job_log_report")
public class XxlJobLogReportEntity extends BaseEntity {

    @Column(name = "trigger_day")
    @Excel(name = "trigger_day", orderNum = "3", importFormat = "yyyy-MM-dd HH:mm:ss", exportFormat = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date triggerDay;

    @Column(name = "running_count", nullable = false)
    @Excel(name = "running_count", orderNum = "4")
    private Integer runningCount = 0;

    @Column(name = "suc_count", nullable = false)
    @Excel(name = "suc_count", orderNum = "5")
    private Integer sucCount = 0;

    @Column(name = "fail_count", nullable = false)
    @Excel(name = "fail_count", orderNum = "6")
    private Integer failCount = 0;
}

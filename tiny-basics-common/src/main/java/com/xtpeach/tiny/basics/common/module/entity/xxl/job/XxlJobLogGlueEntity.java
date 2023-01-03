package com.xtpeach.tiny.basics.common.module.entity.xxl.job;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xtpeach.tiny.basics.common.module.core.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 表名： xxl_job_logglue
 * ------------------------------------------------------------
 * 字段：id                        varchar (32)        pk           主键编码
 * 字段：job_id                    varchar (32)        not null     任务，主键ID
 * 字段：glue_type                 varchar (50)                     失败重试次数
 * 字段：glue_source               text                             GLUE源代码
 * 字段：glue_remark               varchar (128)       not null     GLUE备注
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
        name = "xxl_job_logglue"
)

// mybatis-plus
@TableName("xxl_job_logglue")
public class XxlJobLogGlueEntity extends BaseEntity {

    @Column(name = "job_id", length = 32, nullable = false)
    @Excel(name = "job_id", orderNum = "3")
    private String jobId;

    @Column(name = "glue_type", length = 50, nullable = false)
    @Excel(name = "glue_type", orderNum = "4")
    private String glueType;

    @Column(name = "glue_source", columnDefinition = "TEXT")
    @Excel(name = "glue_source", orderNum = "5")
    private String glueSource;

    @Column(name = "glue_remark", length = 128)
    @Excel(name = "glue_remark", orderNum = "6")
    private String glueRemark;

}

package com.xtpeach.tiny.basics.common.module.entity.kettle.ui;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xtpeach.tiny.basics.common.module.core.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * 表名： kettle_transform_cron_task
 * ------------------------------------------------------------
 * 字段：id varchar (32)                   pk           主键编码
 * 字段：create_time timestamp (6)                      创建时间
 * 字段：update_time timestamp (6)                      更新时间
 * 字段：ktr_name varchar (2000)           not null     ktr文件名
 * 字段：ktr_path varchar (2000)           not null     ktr文件路径
 * 字段：xxl_job_task_id varchar (32)                   任务编码
 * ------------------------------------------------------------
 * 索引：kettle_transform_cron_task_xxl_job_task_id_idx(xxl_job_task_id)
 */

//lombok
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

// jpa-hibernate
@Entity
@Table(
        name = "kettle_transform_cron_task",
        indexes = {
                @Index(columnList = "xxl_job_task_id", name = "kettle_transform_cron_task_xxl_job_task_id_idx")
        }
)

// mybatis-plus
@TableName("kettle_transform_cron_task")
/**
 * kettle transform cron task entity
 * @author xtpeach
 */
public class KettleTransformCronTaskEntity extends BaseEntity {

    /**
     * 转化文件名称
     */
    @Column(name = "ktr_name", length = 2000, nullable = false)
    @Excel(name = "ktr_name", orderNum = "3")
    private String ktrName;

    /**
     * 转化文件路径
     */
    @Column(name = "ktr_path", length = 2000, nullable = false)
    @Excel(name = "ktr_path", orderNum = "4")
    private String ktrPath;

    /**
     * 转化任务编码
     */
    @Column(name = "xxl_job_task_id", length = 32, nullable = false)
    @Excel(name = "xxl_job_task_id", orderNum = "5")
    private String xxlJobTaskId;

}

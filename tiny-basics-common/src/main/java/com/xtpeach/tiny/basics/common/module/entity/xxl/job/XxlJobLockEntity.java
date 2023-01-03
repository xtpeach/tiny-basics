package com.xtpeach.tiny.basics.common.module.entity.xxl.job;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xtpeach.tiny.basics.common.module.core.ID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 表名： xxl_job_lock
 * ------------------------------------------------------------
 * 字段：lock_name   varchar (50)        pk           锁名称
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
        name = "xxl_job_lock"
)

// mybatis-plus
@TableName("xxl_job_lock")
public class XxlJobLockEntity extends ID {

    @Column(name = "lock_name", length = 50, nullable = false)
    @Excel(name = "lock_name", orderNum = "0")
    private String lockName;

}

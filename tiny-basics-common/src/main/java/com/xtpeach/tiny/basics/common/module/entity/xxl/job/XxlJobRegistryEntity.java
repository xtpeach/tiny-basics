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
 * 表名： xxl_job_registry
 * ------------------------------------------------------------
 * 字段：id                        varchar (32)        pk           主键编码
 * 字段：registry_group            varchar (50)        not null
 * 字段：registry_key              varchar (255)       not null
 * 字段：registry_value            varchar (255)       not null
 * 字段：calculate_weight          int4 (32)           not null     负载
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
        name = "xxl_job_registry"
)

// mybatis-plus
@TableName("xxl_job_registry")
public class XxlJobRegistryEntity extends BaseEntity {

    @Column(name = "registry_group", length = 50, nullable = false)
    @Excel(name = "registry_group", orderNum = "3")
    private String registryGroup;

    @Column(name = "registry_key", length = 255, nullable = false)
    @Excel(name = "registry_key", orderNum = "4")
    private String registryKey;

    @Column(name = "registry_value", length = 255, nullable = false)
    @Excel(name = "registry_value", orderNum = "5")
    private String registryValue;

    @Column(name = "calculate_weight", nullable = false)
    @Excel(name = "calculate_weight", orderNum = "6")
    private Integer calculateWeight = 0;

}

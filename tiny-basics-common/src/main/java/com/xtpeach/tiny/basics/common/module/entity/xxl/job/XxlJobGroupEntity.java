package com.xtpeach.tiny.basics.common.module.entity.xxl.job;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xtpeach.tiny.basics.common.module.core.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 表名： xxl_job_group
 * ------------------------------------------------------------
 * 字段：id varchar (32)              pk           主键编码
 * 字段：app_name timestamp (6)       not null     执行器AppName
 * 字段：title timestamp (6)          not null     执行器名称
 * 字段：address_type varchar (20)    not null     执行器地址类型：0=自动注册、1=手动录入
 * 字段：address_list int (32)        not null     执行器地址列表，多地址逗号分隔
 * 字段：create_time timestamp (6)                 创建时间
 * 字段：update_time timestamp (6)                 更新时间
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
        name = "xxl_job_group"
)

// mybatis-plus
@TableName("xxl_job_group")
public class XxlJobGroupEntity extends BaseEntity {

    @Column(name = "app_name", length = 64, nullable = false)
    @Excel(name = "app_name", orderNum = "3")
    private String appName;

    @Column(name = "title", length = 12, nullable = false)
    @Excel(name = "title", orderNum = "4")
    private String title;

    @Column(name = "address_type", nullable = false)
    @Excel(name = "address_type", orderNum = "5")
    private Integer addressType = 0;

    @TableField(updateStrategy = FieldStrategy.IGNORED)
    @Column(name = "address_list", columnDefinition = "TEXT")
    @Excel(name = "address_list", orderNum = "6")
    private String addressList;

    @Transient
    @TableField(exist = false)
    private List<String> registryList;

    public List<String> getRegistryList() {
        List<String> registryList = Lists.newArrayList();
        if (StringUtils.isNotBlank(this.getAddressList())) {
            registryList = new ArrayList<>(Arrays.asList(this.getAddressList().split(",")));
        }
        return registryList;
    }

}

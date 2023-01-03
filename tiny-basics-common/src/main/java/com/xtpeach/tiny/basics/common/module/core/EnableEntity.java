package com.xtpeach.tiny.basics.common.module.core;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.xtpeach.tiny.basics.common.enums.enable.EnableStatusEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.joda.time.DateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * 基础实体（逻辑删除）
 * column: id,createTime,updateTime,enabled
 * excel: id(1),createTime(2),updateTime(3),enabled(4)
 *
 * @author xtpeach
 */
@Data
@MappedSuperclass
public abstract class EnableEntity extends ID {

    /**
     * 创建时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(updatable = false)
    @TableField(updateStrategy = FieldStrategy.NEVER)
    @Excel(name = "create_time", orderNum = "1", importFormat = "yyyy-MM-dd HH:mm:ss", exportFormat = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime = DateTime.now().toDate();

    /**
     * 更新时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @Excel(name = "update_time", orderNum = "2", importFormat = "yyyy-MM-dd HH:mm:ss", exportFormat = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    /**
     * 是否生效 1-生效 0-不生效
     */
    @Excel(name = "enabled", orderNum = "3")
    protected Boolean enabled = EnableStatusEnum.ENABLED.getEnableStatus();

}
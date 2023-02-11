package com.xtpeach.tiny.basics.common.module.entity.init;

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
 * 表名： xtpeach_tiny_basics_init_data_his
 * ------------------------------------------------------------
 * 字段：id varchar (32)              pk           主键编码
 * 字段：create_time timestamp (6)                 创建时间
 * 字段：update_time timestamp (6)                 更新时间
 * 字段：init_outcome varchar (20)    not null     初始化执行结果
 * 字段：count int (32)               not null     执行次数
 * ------------------------------------------------------------
 * 索引：init_his_init_outcome_idx(init_outcome)
 */

//lombok
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

// jpa-hibernate
@Entity
@Table(
        name = "tiny_basics_init_his",
        indexes = {
                @Index(columnList = "init_outcome", name = "init_his_init_outcome_idx")
        }
)

// mybatis-plus
@TableName("tiny_basics_init_his")
/**
 * 初始化数据操作历史记录
 * @author xtpeach
 */
public class TinyBaseInitHisEntity extends BaseEntity {

    @Column(name = "init_outcome", length = 20, nullable = false)
    @Excel(name = "init_outcome", orderNum = "3")
    private String initOutcome;

    @Column(name = "count", nullable = false)
    @Excel(name = "count", orderNum = "4")
    private Integer count;

}

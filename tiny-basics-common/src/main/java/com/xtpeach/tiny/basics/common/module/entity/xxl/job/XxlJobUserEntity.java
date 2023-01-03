package com.xtpeach.tiny.basics.common.module.entity.xxl.job;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xtpeach.tiny.basics.common.module.core.ID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 表名： xxl_job_user
 * ------------------------------------------------------------
 * 字段：id                        varchar (32)        pk           主键编码
 * 字段：username                  varchar (50)        not null     账号
 * 字段：password                  varchar (50)        not null     密码
 * 字段：role                      int4 (32)           not null     角色：0-普通用户、1-管理员
 * 字段：permission                varchar (255)                    权限：执行器ID列表，多个逗号分割
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
        name = "xxl_job_user"
)

// mybatis-plus
@TableName("xxl_job_user")
public class XxlJobUserEntity extends ID {

    @Column(name = "username", length = 50, nullable = false)
    @Excel(name = "username", orderNum = "1")
    private String username;

    @Column(name = "password", length = 50, nullable = false)
    @Excel(name = "password", orderNum = "2")
    private String password;

    @Column(name = "role", nullable = false)
    @Excel(name = "role", orderNum = "3")
    private Integer role = 0;

    @Column(name = "permission", length = 255)
    @Excel(name = "permission", orderNum = "4")
    private String permission;

    public static boolean validPermission(XxlJobUserEntity xxlJobUser, String jobGroup) {
        if (xxlJobUser.role == 1) {
            return true;
        } else {
            if (StringUtils.hasText(xxlJobUser.permission)) {
                for (String permissionItem : xxlJobUser.permission.split(",")) {
                    if (String.valueOf(jobGroup).equals(permissionItem)) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

}

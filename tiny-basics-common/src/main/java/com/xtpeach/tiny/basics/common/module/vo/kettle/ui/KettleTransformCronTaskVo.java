package com.xtpeach.tiny.basics.common.module.vo.kettle.ui;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KettleTransformCronTaskVo {

    /**
     * ID
     */
    @Schema(description = "ID")
    private String id;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    private Date updateTime;

    /**
     * 转化文件名称
     */
    @Schema(description = "转化文件名称")
    private String ktrName;

    /**
     * 转化文件路径
     */
    @Schema(description = "转化文件路径")
    private String ktrPath;

    /**
     * 转化任务编码
     */
    @Schema(description = "转化任务编码")
    private String xxlJobTaskId;

}

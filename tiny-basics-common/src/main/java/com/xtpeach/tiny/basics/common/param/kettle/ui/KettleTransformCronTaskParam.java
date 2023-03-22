package com.xtpeach.tiny.basics.common.param.kettle.ui;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xtpeach.tiny.basics.common.module.entity.kettle.ui.KettleTransformCronTaskEntity;
import com.xtpeach.tiny.basics.common.page.IPageWebAdaptor;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KettleTransformCronTaskParam extends IPageWebAdaptor {

    /**
     * 主键业务编码
     */
    @Schema(description = "主键业务编码")
    private String id;

    /**
     * 任务平台任务编码
     */
    @Schema(description = "任务平台任务编码")
    private String xxlJobTaskId;

    /**
     * 转化文件名称
     */
    @Schema(description = "转化文件名称")
    private String ktrName;

    /**
     * 转化文件描述
     */
    @Schema(description = "转化文件描述")
    private String ktrDesc;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private String createTime;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    private String updateTime;

    /**
     * mybatis-plus 查询参数
     */
    @Schema(hidden = true)
    private QueryWrapper<KettleTransformCronTaskEntity> queryWrapper = new QueryWrapper<>();

    public void setId(String id) {
        this.id = id;
        if (StringUtils.isNotBlank(id)) {
            queryWrapper.lambda().like(KettleTransformCronTaskEntity::getId, id);
        }
    }

    public void setXxlJobTaskId(String xxlJobTaskId) {
        this.xxlJobTaskId = xxlJobTaskId;
        if (StringUtils.isNotBlank(xxlJobTaskId)) {
            queryWrapper.lambda().like(KettleTransformCronTaskEntity::getXxlJobTaskId, xxlJobTaskId);
        }
    }

    public void setKtrName(String ktrName) {
        this.ktrName = ktrName;
        if (StringUtils.isNotBlank(ktrName)) {
            queryWrapper.lambda().like(KettleTransformCronTaskEntity::getKtrName, ktrName);
        }
    }

    public void setKtrDesc(String ktrDesc) {
        this.ktrDesc = ktrDesc;
        if (StringUtils.isNotBlank(ktrDesc)) {
            queryWrapper.lambda().like(KettleTransformCronTaskEntity::getKtrDesc, ktrDesc);
        }
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
        if (StringUtils.isNotBlank(createTime)) {
            String[] timeParams = StringUtils.split(createTime);
            if (CollectionUtils.size(timeParams) == 3) {
                queryWrapper.lambda().between(KettleTransformCronTaskEntity::getCreateTime, DateTime.parse(timeParams[0]).toDate(), DateTime.parse(timeParams[2]).toDate());
            }
        }
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
        if (StringUtils.isNotBlank(updateTime)) {
            String[] timeParams = StringUtils.split(updateTime);
            if (CollectionUtils.size(timeParams) == 3) {
                queryWrapper.lambda().between(KettleTransformCronTaskEntity::getUpdateTime, DateTime.parse(timeParams[0]).toDate(), DateTime.parse(timeParams[2]).toDate());
            }
        }
    }

}

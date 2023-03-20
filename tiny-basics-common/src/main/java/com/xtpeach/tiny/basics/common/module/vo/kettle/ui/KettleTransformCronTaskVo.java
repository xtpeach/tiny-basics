package com.xtpeach.tiny.basics.common.module.vo.kettle.ui;

import com.xtpeach.tiny.basics.common.module.entity.kettle.ui.KettleTransformCronTaskEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.compress.utils.Lists;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ktr定时任务
 */
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
     * 转化文件描述
     */
    @Schema(description = "转化文件描述")
    private String ktrDesc;

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

    /**
     * of
     *
     * @param kettleTransformCronTaskEntity
     * @return
     */
    public static KettleTransformCronTaskVo of(KettleTransformCronTaskEntity kettleTransformCronTaskEntity) {
        KettleTransformCronTaskVo kettleTransformCronTaskVo = new KettleTransformCronTaskVo();
        kettleTransformCronTaskVo.setId(kettleTransformCronTaskEntity.getId());
        kettleTransformCronTaskVo.setKtrPath(kettleTransformCronTaskEntity.getKtrPath());
        kettleTransformCronTaskVo.setKtrName(kettleTransformCronTaskEntity.getKtrName());
        kettleTransformCronTaskVo.setKtrDesc(kettleTransformCronTaskEntity.getKtrDesc());
        kettleTransformCronTaskVo.setUpdateTime(kettleTransformCronTaskEntity.getUpdateTime());
        kettleTransformCronTaskVo.setCreateTime(kettleTransformCronTaskEntity.getCreateTime());
        kettleTransformCronTaskVo.setXxlJobTaskId(kettleTransformCronTaskEntity.getXxlJobTaskId());
        return kettleTransformCronTaskVo;
    }

    /**
     * of
     *
     * @param kettleTransformCronTaskEntityList
     * @return
     */
    public static List<KettleTransformCronTaskVo> of(List<KettleTransformCronTaskEntity> kettleTransformCronTaskEntityList) {
        List<KettleTransformCronTaskVo> kettleTransformCronTaskVoList = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(kettleTransformCronTaskEntityList)) {
            kettleTransformCronTaskVoList = kettleTransformCronTaskEntityList
                    .stream().map(kettleTransformCronTaskEntity
                            -> KettleTransformCronTaskVo.of(kettleTransformCronTaskEntity)).collect(Collectors.toList());
        }
        return kettleTransformCronTaskVoList;
    }

    /**
     * to
     *
     * @param kettleTransformCronTaskVo
     * @return
     */
    public static KettleTransformCronTaskEntity to(KettleTransformCronTaskVo kettleTransformCronTaskVo) {
        KettleTransformCronTaskEntity kettleTransformCronTaskEntity = new KettleTransformCronTaskEntity();
        kettleTransformCronTaskEntity.setId(kettleTransformCronTaskVo.getId());
        kettleTransformCronTaskEntity.setKtrPath(kettleTransformCronTaskVo.getKtrPath());
        kettleTransformCronTaskEntity.setKtrName(kettleTransformCronTaskVo.getKtrName());
        kettleTransformCronTaskEntity.setKtrDesc(kettleTransformCronTaskVo.getKtrDesc());
        kettleTransformCronTaskEntity.setUpdateTime(kettleTransformCronTaskVo.getUpdateTime());
        kettleTransformCronTaskEntity.setCreateTime(kettleTransformCronTaskVo.getCreateTime());
        kettleTransformCronTaskEntity.setXxlJobTaskId(kettleTransformCronTaskVo.getXxlJobTaskId());
        return kettleTransformCronTaskEntity;
    }

    /**
     * to
     *
     * @param kettleTransformCronTaskVoList
     * @return
     */
    public static List<KettleTransformCronTaskEntity> to(List<KettleTransformCronTaskVo> kettleTransformCronTaskVoList) {
        List<KettleTransformCronTaskEntity> kettleTransformCronTaskEntityList = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(kettleTransformCronTaskVoList)) {
            kettleTransformCronTaskEntityList = kettleTransformCronTaskVoList
                    .stream().map(kettleTransformCronTaskVo
                            -> KettleTransformCronTaskVo.to(kettleTransformCronTaskVo)).collect(Collectors.toList());
        }
        return kettleTransformCronTaskEntityList;
    }

}

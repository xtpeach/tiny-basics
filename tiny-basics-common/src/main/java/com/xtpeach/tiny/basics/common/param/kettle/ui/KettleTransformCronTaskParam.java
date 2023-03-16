package com.xtpeach.tiny.basics.common.param.kettle.ui;

import com.xtpeach.tiny.basics.common.page.IPageWebAdaptor;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KettleTransformCronTaskParam extends IPageWebAdaptor {

    /**
     * 转化文件名称
     */
    @Schema(description = "转化文件名称")
    private String ktrName;

}

package com.xtpeach.tiny.basics.common.page;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.pagehelper.IPage;
import com.google.common.base.CaseFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * @author xtpeach
 */
@Data
public abstract class IPageWebAdaptor<T> implements IPage {

    /**
     * 当前查询的页码
     */
    @Schema(description = "当前查询的页码", required = true, example = "1")
    private Integer page = 0;

    /**
     * 每页的条数
     */
    @Schema(description = "每页的条数", required = true, example = "10")
    private Integer rows = 10;

    /**
     * 排序方式（"asc"|"desc"）
     */
    @Schema(description = "排序方式（'asc'|'desc'）", example = "desc")
    private String order;

    /**
     * 排序的属性名
     */
    @Schema(description = "排序的属性名", example = "fireTime")
    private String sidx;

    @Override
    @Schema(hidden = true)
    public Integer getPageNum() {
        if (page <= 0) {
            this.page = 1;
        }
        return page;
    }

    @Override
    @Schema(hidden = true)
    public Integer getPageSize() {
        if (rows <= 0) {
            this.rows = 10;
        }
        return rows;
    }

    @Override
    @Schema(hidden = true)
    public String getOrderBy() {
        if (!StringUtils.isBlank(sidx)) {
            // sidx = "id";
            if (StringUtils.isBlank(order)) {
                order = "desc";
            }
            return StringUtils.joinWith(" ",
                    CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.LOWER_UNDERSCORE).convert(sidx), order);
        }
        return null;
    }

    @JsonIgnore
    @Schema(hidden = true)
    public QueryWrapper<T> getQueryWrapper() {
        return new QueryWrapper<T>();
    }

}

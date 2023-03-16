package com.xtpeach.tiny.basics.common.page;

import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * mybatis PageInfo view object
 *
 * @author xtpeach
 * @see PageInfo
 */
@Data
public class PageInfoVo<T> {

    /**
     * 总记录数
     */
    @Schema(description = "总记录数", example = "2969")
    protected long total;

    /**
     * 结果集
     */
    @Schema(description = "结果集")
    protected List<T> list;

    /**
     * 当前页
     */
    @Schema(description = "当前页", example = "1")
    private int pageNum;

    /**
     * 每页的数量
     */
    @Schema(description = "每页的数量", example = "10")
    private int pageSize;

    /**
     * 当前页的数量
     */
    @Schema(description = "当前页的数量", example = "10")
    private int size;

    /**
     * 当前页面第一个元素在数据库中的行号
     */
    @Schema(description = "当前页面第一个元素在数据库中的行号", example = "1")
    private long startRow;

    /**
     * 当前页面最后一个元素在数据库中的行号
     */
    @Schema(description = "当前页面最后一个元素在数据库中的行号", example = "10")
    private long endRow;

    /**
     * 总页数
     */
    @Schema(description = "总页数", example = "297")
    private int pages;

    /**
     * 前一页
     */
    @Schema(description = "前一页", example = "0")
    private int prePage;

    /**
     * 下一页
     */
    @Schema(description = "下一页", example = "2")
    private int nextPage;

    /**
     * 是否为第一页
     */
    @Schema(description = "是否为第一页", example = "true")
    private boolean firstPage = false;

    /**
     * 是否为最后一页
     */
    @Schema(description = "是否为最后一页", example = "false")
    private boolean lastPage = false;

    /**
     * 是否有前一页
     */
    @Schema(description = "是否有前一页", example = "false")
    private boolean hasPreviousPage = false;

    /**
     * 是否有下一页
     */
    @Schema(description = "是否有下一页", example = "true")
    private boolean hasNextPage = false;

    /**
     * 导航页码数
     */
    @Schema(description = "导航页码数", example = "8")
    private int navigatePages;

    /**
     * 所有导航页号
     */
    @Schema(description = "所有导航页号", example = "[1,2,3,4,5,6,7,8]")
    private int[] navigatePageNums;

    /**
     * 导航条上的第一页
     */
    @Schema(description = "导航条上的第一页", example = "1")
    private int navigateFirstPage;

    /**
     * 导航条上的最后一页
     */
    @Schema(description = "导航条上的最后一页", example = "8")
    private int navigateLastPage;

    /**
     * 克隆PageInfo，但是不包含list数据集
     *
     * @param pageInfo mybatis分页对象
     * @param <V>      select的结果集的范型类型
     * @param <VR>     mapping后的类型
     * @return 克隆后的分页结果集，list数据集为<code>null</code>
     */
    public static <V, VR> PageInfoVo<VR> withoutList(PageInfo<V> pageInfo) {
        PageInfoVo<VR> pageInfoVo = new PageInfoVo<>();
        pageInfoVo.setPageNum(pageInfo.getPageNum());
        pageInfoVo.setPageSize(pageInfo.getPageSize());
        pageInfoVo.setSize(pageInfo.getSize());
        pageInfoVo.setStartRow(pageInfo.getStartRow());
        pageInfoVo.setEndRow(pageInfo.getEndRow());
        pageInfoVo.setPages(pageInfo.getPages());
        pageInfoVo.setPrePage(pageInfo.getPrePage());
        pageInfoVo.setNextPage(pageInfo.getNextPage());
        pageInfoVo.setFirstPage(pageInfo.isIsFirstPage());
        pageInfoVo.setLastPage(pageInfo.isIsLastPage());
        pageInfoVo.setHasPreviousPage(pageInfo.isHasPreviousPage());
        pageInfoVo.setHasNextPage(pageInfo.isHasNextPage());
        pageInfoVo.setNavigatePages(pageInfo.getNavigatePages());
        pageInfoVo.setNavigatePageNums(pageInfo.getNavigatepageNums());
        pageInfoVo.setNavigateFirstPage(pageInfo.getNavigateFirstPage());
        pageInfoVo.setNavigateLastPage(pageInfo.getNavigateLastPage());
        pageInfoVo.setTotal(pageInfo.getTotal());
        return pageInfoVo;
    }

    /**
     * 克隆PageInfo
     *
     * @param pageInfo mybatis分页对象
     * @param <V>      select的结果集的范型类型
     * @return 克隆后的分页结果集
     */
    public static <V> PageInfoVo<V> of(PageInfo<V> pageInfo) {
        PageInfoVo<V> resultPageInfo = withoutList(pageInfo);
        resultPageInfo.setList(pageInfo.getList());
        return resultPageInfo;
    }

}

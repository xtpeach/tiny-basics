package com.xtpeach.tiny.basics.common.util.page;

import com.github.pagehelper.*;
import com.xtpeach.tiny.basics.common.page.PageInfoVo;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * mybatis分页工具类
 *
 * @author lingGao
 */
public class PageHelperUtil {

    /**
     * 分页查询
     *
     * @param param  查询的分页参数
     * @param select 查询
     * @param <V>    select的结果集的范型类型
     * @return 分页结果集
     */
    public static <V> PageInfoVo<V> doSelect(Object param, ISelect select) {
        try {
            PageInfo<V> pageInfo = PageHelper.startPage(param).doSelectPageInfo(select);
            return PageInfoVo.of(pageInfo);
        } finally {
            PageHelper.clearPage();
        }
    }

    /**
     * 分页查询并对结果集做mapping
     *
     * @param param   查询的分页参数
     * @param select  查询
     * @param mapping mapping
     * @param <V>     select的结果集的范型类型
     * @param <VR>    mapping后的类型
     * @return mapping后的分页结果集
     */
    public static <V, VR> PageInfoVo<VR> doSelect(Object param, ISelect select, Function<V, VR> mapping) {
        try {
            PageInfo<V> pageInfo = PageHelper.startPage(param).doSelectPageInfo(select);
            PageInfoVo<VR> resultPageInfo = PageInfoVo.withoutList(pageInfo);
            List<VR> mappingList = pageInfo.getList().stream().map(mapping).collect(Collectors.toList());
            resultPageInfo.setList(mappingList);
            return resultPageInfo;
        } finally {
            PageHelper.clearPage();
        }
    }

    /**
     * 创建一个空的（总数为0）分页结果集
     *
     * @param param 查询的分页参数
     * @param <V>   结果集的范型类型
     * @return 一个空的分页结果集
     */
    public static <V> PageInfoVo<V> createEmpty(Object param) {
        try {
            Page<V> page = PageHelper.startPage(param);
            page.setTotal(0);
            return PageInfoVo.of(page.toPageInfo());
        } finally {
            PageHelper.clearPage();
        }
    }

    /**
     * 自动分页，也就是从totalData中过滤得到当前页的数据
     *
     * @param param     分页参数
     * @param totalData 总数据集
     * @param <V>       结果集的范型类型
     * @return 分页结果集
     */
    public static <V> PageInfoVo<V> autoPage(IPage param, List<V> totalData) {
        if (CollectionUtils.isEmpty(totalData)) {
            return createEmpty(param);
        }
        int pageNum = param.getPageNum();
        int pageSize = param.getPageSize();
        int total = totalData.size();
        // 总页数
        int pages = total / pageSize + (total % pageSize == 0 ? 0 : 1);
        if (pageNum > pages) {
            return PageHelperUtil.createEmpty(param);
        } else {
            int fromIndex = (pageNum - 1) * pageSize;
            int toIndex = (pageNum - 1) * pageSize + pageSize;
            if (pageNum == pages) {
                if (Math.min(total % pageSize, toIndex) != 0) {
                    toIndex = Math.min(total % pageSize, toIndex) + fromIndex;
                }
            }
            List<V> data = totalData.subList(fromIndex, toIndex);
            return PageHelperUtil.withData(param, totalData.size(), data);
        }
    }

    /**
     * 创建一个总数为total的分页结果集
     *
     * @param param 查询的分页参数
     * @param total 总数
     * @param data  结果集
     * @param <V>   结果集的范型类型
     * @return 一个总数为total的分页结果集
     */
    public static <V> PageInfoVo<V> withData(Object param, int total, List<V> data) {
        try {
            Page<V> page = PageHelper.startPage(param);
            page.setTotal(total);
            page.addAll(data);
            return PageInfoVo.of(page.toPageInfo());
        } finally {
            PageHelper.clearPage();
        }
    }

}

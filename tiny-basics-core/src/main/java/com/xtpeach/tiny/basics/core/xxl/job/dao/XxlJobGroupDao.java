package com.xtpeach.tiny.basics.core.xxl.job.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xtpeach.tiny.basics.common.module.entity.xxl.job.XxlJobGroupEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by xuxueli on 16/9/30.
 */
@Mapper
public interface XxlJobGroupDao extends BaseMapper<XxlJobGroupEntity> {

    List<XxlJobGroupEntity> findAll();

    List<XxlJobGroupEntity> findByAddressType(@Param("addressType") int addressType);

    int update(XxlJobGroupEntity xxlJobGroup);

    int remove(@Param("id") String id);

    XxlJobGroupEntity load(@Param("id") String id);

    List<XxlJobGroupEntity> pageList(@Param("offset") int offset,
                                      @Param("pagesize") int pagesize,
                                      @Param("appName") String appName,
                                      @Param("title") String title);

    int pageListCount(@Param("offset") int offset,
                             @Param("pagesize") int pagesize,
                             @Param("appName") String appName,
                             @Param("title") String title);

}

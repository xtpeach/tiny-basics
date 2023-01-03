package com.xtpeach.tiny.basics.core.xxl.job.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xtpeach.tiny.basics.common.module.entity.xxl.job.XxlJobLogGlueEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * job log for glue
 * @author xuxueli 2016-5-19 18:04:56
 */
@Mapper
public interface XxlJobLogGlueDao extends BaseMapper<XxlJobLogGlueEntity> {
	
	List<XxlJobLogGlueEntity> findByJobId(@Param("jobId") String jobId);

	int removeOld(@Param("jobId") String jobId, @Param("limit") int limit);

	int deleteByJobId(@Param("jobId") String jobId);
	
}

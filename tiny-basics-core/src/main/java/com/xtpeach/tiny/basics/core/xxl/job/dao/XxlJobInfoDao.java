package com.xtpeach.tiny.basics.core.xxl.job.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xtpeach.tiny.basics.common.module.entity.xxl.job.XxlJobInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * job info
 * @author xuxueli 2016-1-12 18:03:45
 */
@Mapper
public interface XxlJobInfoDao extends BaseMapper<XxlJobInfoEntity> {

	List<XxlJobInfoEntity> pageList(@Param("offset") int offset,
									 @Param("pagesize") int pagesize,
									 @Param("jobInfoId") String jobInfoId,
									 @Param("jobGroup") String jobGroup,
									 @Param("triggerStatus") int triggerStatus,
									 @Param("jobDesc") String jobDesc,
									 @Param("executorHandler") String executorHandler,
									 @Param("author") String author);

	int pageListCount(@Param("offset") int offset,
							 @Param("pagesize") int pagesize,
                             @Param("jobInfoId") String jobInfoId,
							 @Param("jobGroup") String jobGroup,
							 @Param("triggerStatus") int triggerStatus,
							 @Param("jobDesc") String jobDesc,
							 @Param("executorHandler") String executorHandler,
							 @Param("author") String author);
	
	XxlJobInfoEntity loadById(@Param("id") String id);
	
	int update(XxlJobInfoEntity xxlJobInfo);
	
	int delete(@Param("id") String id);

	List<XxlJobInfoEntity> getJobsByGroup(@Param("jobGroup") String jobGroup);

	int findAllCount();

	List<XxlJobInfoEntity> scheduleJobQuery(@Param("maxNextTime") long maxNextTime, @Param("pagesize") int pagesize );

	int scheduleUpdate(XxlJobInfoEntity xxlJobInfo);


}

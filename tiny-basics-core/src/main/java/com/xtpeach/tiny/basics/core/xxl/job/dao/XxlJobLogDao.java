package com.xtpeach.tiny.basics.core.xxl.job.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xtpeach.tiny.basics.common.module.entity.xxl.job.XxlJobLogEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * job log
 * @author xuxueli 2016-1-12 18:03:06
 */
@Mapper
public interface XxlJobLogDao extends BaseMapper<XxlJobLogEntity> {

	// exist jobId not use jobGroup, not exist use jobGroup
	List<XxlJobLogEntity> pageList(@Param("offset") int offset,
									@Param("pagesize") int pagesize,
									@Param("jobGroup") String jobGroup,
									@Param("jobId") String jobId,
									@Param("triggerTimeStart") Date triggerTimeStart,
									@Param("triggerTimeEnd") Date triggerTimeEnd,
									@Param("logStatus") int logStatus);

	int pageListCount(@Param("offset") int offset,
							 @Param("pagesize") int pagesize,
							 @Param("jobGroup") String jobGroup,
							 @Param("jobId") String jobId,
							 @Param("triggerTimeStart") Date triggerTimeStart,
							 @Param("triggerTimeEnd") Date triggerTimeEnd,
							 @Param("logStatus") int logStatus);
	
	XxlJobLogEntity load(@Param("id") String id);

	int updateTriggerInfo(XxlJobLogEntity xxlJobLog);

	int updateHandleInfo(XxlJobLogEntity xxlJobLog);
	
	int delete(@Param("jobId") String jobId);

	Map<String, Object> findLogReport(@Param("from") Date from,
											 @Param("to") Date to);

	List<String> findClearLogIds(@Param("jobGroup") String jobGroup,
									  @Param("jobId") String jobId,
									  @Param("clearBeforeTime") Date clearBeforeTime,
									  @Param("clearBeforeNum") int clearBeforeNum,
									  @Param("pagesize") int pagesize);

	int clearLog(@Param("logIds") List<String> logIds);

	List<String> findFailJobLogIds(@Param("pagesize") int pagesize);

	int updateAlarmStatus(@Param("logId") String logId,
								 @Param("oldAlarmStatus") int oldAlarmStatus,
								 @Param("newAlarmStatus") int newAlarmStatus);

	List<String> findLostJobIds(@Param("losedTime") Date losedTime);

}

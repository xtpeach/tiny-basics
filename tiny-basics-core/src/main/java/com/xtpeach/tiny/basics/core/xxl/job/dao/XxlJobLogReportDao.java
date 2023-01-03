package com.xtpeach.tiny.basics.core.xxl.job.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xtpeach.tiny.basics.common.module.entity.xxl.job.XxlJobLogReportEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * job log
 * @author xuxueli 2019-11-22
 */
@Mapper
public interface XxlJobLogReportDao extends BaseMapper<XxlJobLogReportEntity> {

	List<XxlJobLogReportEntity> queryLogReport(@Param("triggerDayFrom") Date triggerDayFrom,
												@Param("triggerDayTo") Date triggerDayTo);

	XxlJobLogReportEntity queryLogReportTotal();

}

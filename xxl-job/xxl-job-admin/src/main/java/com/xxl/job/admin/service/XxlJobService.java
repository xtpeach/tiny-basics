package com.xxl.job.admin.service;


import com.xtpeach.tiny.basics.common.module.entity.xxl.job.XxlJobInfoEntity;
import com.xxl.job.core.biz.model.ReturnT;

import java.util.Date;
import java.util.Map;

/**
 * core job action for xxl-job
 * 
 * @author xuxueli 2016-5-28 15:30:33
 */
public interface XxlJobService {

	/**
	 * page list
	 *
	 * @param start
	 * @param length
	 * @param jobGroup
	 * @param jobDesc
	 * @param executorHandler
	 * @param author
	 * @return
	 */
	Map<String, Object> pageList(int start, int length, String jobInfoId, String jobGroup, int triggerStatus, String jobDesc, String executorHandler, String author);

	/**
	 * add job
	 *
	 * @param jobInfo
	 * @return
	 */
	ReturnT<String> add(XxlJobInfoEntity jobInfo);

	/**
	 * update job
	 *
	 * @param jobInfo
	 * @return
	 */
	ReturnT<String> update(XxlJobInfoEntity jobInfo);

	/**
	 * remove job
	 * 	 *
	 * @param id
	 * @return
	 */
	ReturnT<String> remove(String id);

	/**
	 * start job
	 *
	 * @param id
	 * @return
	 */
	ReturnT<String> start(String id);

	/**
	 * stop job
	 *
	 * @param id
	 * @return
	 */
	ReturnT<String> stop(String id);

	/**
	 * dashboard info
	 *
	 * @return
	 */
	Map<String,Object> dashboardInfo();

	/**
	 * chart info
	 *
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	ReturnT<Map<String,Object>> chartInfo(Date startDate, Date endDate);

}

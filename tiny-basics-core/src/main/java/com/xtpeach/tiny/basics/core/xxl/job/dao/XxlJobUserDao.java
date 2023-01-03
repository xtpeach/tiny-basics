package com.xtpeach.tiny.basics.core.xxl.job.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xtpeach.tiny.basics.common.module.entity.xxl.job.XxlJobUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * @author xuxueli 2019-05-04 16:44:59
 */
@Mapper
public interface XxlJobUserDao extends BaseMapper<XxlJobUserEntity> {

	List<XxlJobUserEntity> pageList(@Param("offset") int offset,
                                     @Param("pagesize") int pagesize,
                                     @Param("username") String username,
									 @Param("role") int role);

	int pageListCount(@Param("offset") int offset,
							 @Param("pagesize") int pagesize,
							 @Param("username") String username,
							 @Param("role") int role);

	XxlJobUserEntity loadByUserName(@Param("username") String username);

	int update(XxlJobUserEntity xxlJobUser);
	
	int delete(@Param("id") String id);

}

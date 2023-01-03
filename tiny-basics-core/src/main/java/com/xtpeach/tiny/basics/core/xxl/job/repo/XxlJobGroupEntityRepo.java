package com.xtpeach.tiny.basics.core.xxl.job.repo;

import com.xtpeach.tiny.basics.common.module.entity.xxl.job.XxlJobGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface XxlJobGroupEntityRepo <T extends XxlJobGroupEntity>
        extends JpaRepository<XxlJobGroupEntity, String>, JpaSpecificationExecutor<XxlJobGroupEntity> {
}

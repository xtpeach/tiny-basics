package com.xtpeach.tiny.basics.core.xxl.job.repo;

import com.xtpeach.tiny.basics.common.module.entity.xxl.job.XxlJobUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface XxlJobUserEntityRepo<T extends XxlJobUserEntity>
        extends JpaRepository<XxlJobUserEntity, String>, JpaSpecificationExecutor<XxlJobUserEntity> {
}

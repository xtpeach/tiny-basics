package com.xtpeach.tiny.basics.core.kettle.ui.repo;

import com.xtpeach.tiny.basics.common.module.entity.kettle.ui.KettleTransformCronTaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface KettleTransformCronTaskEntityRepo<T extends KettleTransformCronTaskEntity>
        extends JpaRepository<KettleTransformCronTaskEntity, String>, JpaSpecificationExecutor<KettleTransformCronTaskEntity> {

    KettleTransformCronTaskEntity findByKtrName(String ktrName);

}

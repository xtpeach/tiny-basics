package com.xtpeach.tiny.basics.core.init.repo;

import com.xtpeach.tiny.basics.common.module.entity.init.TinyBasicsInitHisEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * TinyBaseInitHisEntity Repository
 *
 * @author xtpeach
 */
@Repository
public interface TinyBaseInitHisEntityRepo<T extends TinyBasicsInitHisEntity>
        extends JpaRepository<TinyBasicsInitHisEntity, String>, JpaSpecificationExecutor<TinyBasicsInitHisEntity> {
}

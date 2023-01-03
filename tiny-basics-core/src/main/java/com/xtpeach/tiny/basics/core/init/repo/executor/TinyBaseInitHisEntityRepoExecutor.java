package com.xtpeach.tiny.basics.core.init.repo.executor;

import com.xtpeach.tiny.basics.common.module.entity.init.TinyBaseInitHisEntity;
import com.xtpeach.tiny.basics.core.init.repo.TinyBaseInitHisEntityRepo;
import com.xtpeach.tiny.basics.core.xls.executor.XLSExecutor;
import org.joda.time.DateTime;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 初始化数据时间
 *
 * @author xtpeach
 */
@Component
public class TinyBaseInitHisEntityRepoExecutor extends XLSExecutor<TinyBaseInitHisEntity> implements CommandLineRunner {

    @Resource
    private TinyBaseInitHisEntityRepo<TinyBaseInitHisEntity> repo;

    @Override
    public void run(String... args) {
        List<TinyBaseInitHisEntity> tinyBaseInitHisEntityList = ImportDataFromXLS(TinyBaseInitHisEntity.class, "tinybasics");
        List<TinyBaseInitHisEntity> saveList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(tinyBaseInitHisEntityList)) {
            for (TinyBaseInitHisEntity entity : tinyBaseInitHisEntityList) {
                TinyBaseInitHisEntity repoEntity = repo.findById(entity.getId()).orElse(entity);
                repoEntity.setCount(repoEntity.getCount() + 1);
                repoEntity.setUpdateTime(DateTime.now().toDate());
                saveList.add(repoEntity);
            }
        }
        repo.saveAll(saveList);
    }

}

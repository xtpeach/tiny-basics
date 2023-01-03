package com.xtpeach.tiny.basics.core.xxl.job.repo.executor;

import com.xtpeach.tiny.basics.common.module.entity.xxl.job.XxlJobGroupEntity;
import com.xtpeach.tiny.basics.core.xls.executor.XLSExecutor;
import com.xtpeach.tiny.basics.core.xxl.job.repo.XxlJobGroupEntityRepo;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 初始化数据时间
 *
 * @author xtpeach
 */
@Component
@ConditionalOnProperty(value = "xxl-job.xls.init-data", havingValue = "true")
public class XxlJobGroupEntityRepoExecutor extends XLSExecutor<XxlJobGroupEntity> implements CommandLineRunner {

    @Resource
    private XxlJobGroupEntityRepo<XxlJobGroupEntity> repo;

    @Override
    public void run(String... args) {
        List<XxlJobGroupEntity> xxlJobGroupEntityList = ImportDataFromXLS(XxlJobGroupEntity.class, "xxljob");
        if (CollectionUtils.isNotEmpty(xxlJobGroupEntityList)) {
            repo.saveAll(xxlJobGroupEntityList);
        }
    }

}

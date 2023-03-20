package com.xtpeach.tiny.basics.core.kettle.ui.repo.executor;

import com.xtpeach.tiny.basics.common.module.entity.kettle.ui.KettleTransformCronTaskEntity;
import com.xtpeach.tiny.basics.core.kettle.ui.repo.KettleTransformCronTaskEntityRepo;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * 初始化数据
 *
 * @author xtpeach
 */
@Component
public class KettleTransformCronTaskEntityRepoExecutor implements CommandLineRunner {

    @Value("${ktr.file.path:/home/ktr}")
    private String ktrFilePath;

    @Resource
    private KettleTransformCronTaskEntityRepo kettleTransformCronTaskEntityRepo;

    @Override
    public void run(String... args) throws Exception {
        List<File> fileList = Arrays.asList(new File(ktrFilePath).listFiles());
        fileList.stream().forEach(file -> {
            KettleTransformCronTaskEntity kettleTransformCronTaskEntity = kettleTransformCronTaskEntityRepo.findByKtrName(file.getName());
            // 若查不到 ktr 文件则创建 ktr 记录
            if (ObjectUtils.isEmpty(kettleTransformCronTaskEntity)) {
                kettleTransformCronTaskEntity = new KettleTransformCronTaskEntity();
                kettleTransformCronTaskEntity.setKtrName(file.getName());
                kettleTransformCronTaskEntity.setKtrPath(file.getAbsolutePath());
                kettleTransformCronTaskEntity.setCreateTime(DateTime.now().toDate());
                kettleTransformCronTaskEntity.setUpdateTime(DateTime.now().toDate());
            } else {
                kettleTransformCronTaskEntity.setUpdateTime(DateTime.now().toDate());
            }
            kettleTransformCronTaskEntityRepo.save(kettleTransformCronTaskEntity);
        });
    }

}

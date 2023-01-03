package com.xtpeach.tiny.basics.core.xls.executor;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import com.xtpeach.tiny.basics.common.consts.poi.ExcelConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.io.ClassPathResource;

import javax.persistence.Table;
import java.io.InputStream;
import java.util.List;

/**
 * XLS导入数据
 *
 * @param <T>
 * @author xtpeach
 */
@Slf4j
public abstract class XLSExecutor<T> {

    /**
     * 从xls导入数据
     *
     * @param clazz
     * @return
     */
    public List<T> ImportDataFromXLS(Class clazz, String directory) {
        // 初始化List，从初始化xls文件中获取
        List<T> XLSEntityList = Lists.newArrayList();

        // 找@Table注解，找到对应的xls文件
        Table table = AnnotationUtils.findAnnotation(clazz, Table.class);

        // 从@Table注解中获取表名称，xls文件名和表同名
        String tableName = null;
        if (ObjectUtils.isNotEmpty(table)) {
            tableName = table.name();
        }

        // 如果表名称获取到了，就从xls文件中获取对应的实体数据
        if (StringUtils.isNotBlank(tableName)) {
            ClassPathResource resource = new ClassPathResource("xls/" + directory + "/" + tableName + ".xls");
            try (InputStream inputStream = resource.getInputStream()) {
                ExcelImportResult<T> XLSResult
                        = ExcelImportUtil.importExcelMore(
                        inputStream,
                        clazz,
                        ExcelConstant.importParams
                );
                XLSEntityList = XLSResult.getList();
            } catch (Exception e) {
                log.error("获取初始化数据文件失败:{}", e.getMessage());
                e.printStackTrace();
            }
        }

        // 返回从xls文件中获取的对应实体对象数据
        return XLSEntityList;
    }

}

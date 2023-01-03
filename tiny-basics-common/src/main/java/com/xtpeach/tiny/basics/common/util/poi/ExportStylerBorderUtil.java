package com.xtpeach.tiny.basics.common.util.poi;

import cn.afterturn.easypoi.excel.export.styler.ExcelExportStylerDefaultImpl;
import org.apache.poi.ss.usermodel.*;

/**
 * easy poi 边框样式
 *
 * @author xtpeach
 */
public class ExportStylerBorderUtil extends ExcelExportStylerDefaultImpl {

    /**
     * 构造函数
     *
     * @param workbook
     */
    public ExportStylerBorderUtil(Workbook workbook) {
        super(workbook);
    }

    /**
     * 头样式
     *
     * @param color
     * @return
     */
    @Override
    public CellStyle getHeaderStyle(short color) {
        CellStyle titleStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 12);
        titleStyle.setFont(font);
        titleStyle.setBorderLeft(BorderStyle.THIN);
        titleStyle.setBorderRight(BorderStyle.THIN);
        titleStyle.setBorderBottom(BorderStyle.THIN);
        titleStyle.setBorderTop(BorderStyle.THIN);
        titleStyle.setAlignment(HorizontalAlignment.CENTER);
        titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        return titleStyle;
    }

}

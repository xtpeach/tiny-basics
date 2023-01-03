//package com.xtpeach.tiny.basics.core.api.server.repo;
//
//import cn.afterturn.easypoi.excel.ExcelExportUtil;
//import com.xtpeach.tiny.basics.common.consts.poi.ExcelConstant;
//import com.xtpeach.tiny.basics.common.enums.outcome.OutcomeEnum;
//import com.xtpeach.tiny.basics.common.module.entity.init.TinyBaseInitHisEntity;
//import net.minidev.json.JSONArray;
//import org.apache.commons.compress.utils.Lists;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.ss.util.CellRangeAddress;
//import org.junit.jupiter.api.Test;
//
//import java.io.ByteArrayOutputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.*;
//import java.util.stream.Collectors;
//
///**
// * 导出 xls
// */
//
//public class ExportEntityTest {
//
//    @Test
//    public void export() {
//        List<TinyBaseInitHisEntity> tinyBaseInitHisEntityList = Lists.newArrayList();
//        TinyBaseInitHisEntity tinyBaseInitHisEntity = new TinyBaseInitHisEntity();
//        tinyBaseInitHisEntity.setNum(1);
//        tinyBaseInitHisEntity.setName("甘肃");
//        tinyBaseInitHisEntity.setCount(1);
//        tinyBaseInitHisEntity.setInitOutcome(OutcomeEnum.SUCCESS.getName());
//        tinyBaseInitHisEntityList.add(tinyBaseInitHisEntity);
//        TinyBaseInitHisEntity tinyBaseInitHisEntity2 = new TinyBaseInitHisEntity();
//        tinyBaseInitHisEntity2.setNum(2);
//        tinyBaseInitHisEntity2.setName("江苏");
//        tinyBaseInitHisEntity2.setCount(1);
//        tinyBaseInitHisEntity2.setInitOutcome(OutcomeEnum.SUCCESS.getName());
//        tinyBaseInitHisEntityList.add(tinyBaseInitHisEntity2);
//        TinyBaseInitHisEntity tinyBaseInitHisEntity3 = new TinyBaseInitHisEntity();
//        tinyBaseInitHisEntity3.setNum(3);
//        tinyBaseInitHisEntity3.setName("江苏");
//        tinyBaseInitHisEntity3.setCount(1);
//        tinyBaseInitHisEntity3.setInitOutcome(OutcomeEnum.SUCCESS.getName());
//        tinyBaseInitHisEntityList.add(tinyBaseInitHisEntity3);
//        TinyBaseInitHisEntity tinyBaseInitHisEntity4 = new TinyBaseInitHisEntity();
//        tinyBaseInitHisEntity4.setNum(4);
//        tinyBaseInitHisEntity4.setName("江苏");
//        tinyBaseInitHisEntity4.setCount(1);
//        tinyBaseInitHisEntity4.setInitOutcome(OutcomeEnum.SUCCESS.getName());
//        tinyBaseInitHisEntityList.add(tinyBaseInitHisEntity4);
//
//        TinyBaseInitHisEntity tinyBaseInitHisEntity5 = new TinyBaseInitHisEntity();
//        tinyBaseInitHisEntity5.setNum(5);
//        tinyBaseInitHisEntity5.setName("安徽");
//        tinyBaseInitHisEntity5.setCount(1);
//        tinyBaseInitHisEntity5.setInitOutcome(OutcomeEnum.SUCCESS.getName());
//        tinyBaseInitHisEntityList.add(tinyBaseInitHisEntity5);
//        TinyBaseInitHisEntity tinyBaseInitHisEntity6 = new TinyBaseInitHisEntity();
//        tinyBaseInitHisEntity6.setNum(6);
//        tinyBaseInitHisEntity6.setName("安徽");
//        tinyBaseInitHisEntity6.setCount(1);
//        tinyBaseInitHisEntity6.setInitOutcome(OutcomeEnum.SUCCESS.getName());
//        tinyBaseInitHisEntityList.add(tinyBaseInitHisEntity6);
//        TinyBaseInitHisEntity tinyBaseInitHisEntity7 = new TinyBaseInitHisEntity();
//        tinyBaseInitHisEntity7.setNum(7);
//        tinyBaseInitHisEntity7.setName("福建");
//        tinyBaseInitHisEntity7.setCount(1);
//        tinyBaseInitHisEntity7.setInitOutcome(OutcomeEnum.SUCCESS.getName());
//        tinyBaseInitHisEntityList.add(tinyBaseInitHisEntity7);
//        tinyBaseInitHisEntityList.stream().sorted((o1, o2) -> o2.getNum() - o1.getNum());
//
////        Workbook workbook = ExcelExportUtil.exportExcel(ExcelConstant.exportParams, TinyBaseInitHisEntity.class, tinyBaseInitHisEntityList);
//
//        Map<String, List<TinyBaseInitHisEntity>> listMap = tinyBaseInitHisEntityList.stream().collect(Collectors.groupingBy(TinyBaseInitHisEntity::getName));
//
//        Map<String, List<TinyBaseInitHisEntity>> result = sortMapByValue(listMap);
//        System.out.println(result);
//
//
//
////        LinkedHashMap<String, TinyBaseInitHisEntity> userMap1 = tinyBaseInitHisEntityList.stream()
////                .collect(LinkedHashMap::new, (map, item) -> map.put(item.getName(), item), Map::putAll);
////
////        LinkedHashMap<String, List<TinyBaseInitHisEntity>> alarmMap = tinyBaseInitHisEntityList.stream()
////                .collect(Collectors.groupingBy(TinyBaseInitHisEntity::getName, LinkedHashMap::new, Collectors.toList()));
////
////        System.out.println("2");
//       /* Workbook workbook = ExcelExportUtil.exportExcel(ExcelConstant.exportParams,
//                TinyBaseInitHisEntity.class, tinyBaseInitHisEntityList);
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
////        String fileName = exportDate.replace("-", "年") + "月各公司健康分数据报表.xls";
//
//        // 解析合并
//        Sheet sheet =workbook.getSheetAt(0);
//        //开始合并的行数
//        int startRow = 1;
//        //结束合并的行数
//        int endRow = 1;
//
//        // 3 4
//
//        for(String key : listMap.keySet()){
//            List<TinyBaseInitHisEntity> list = listMap.get(key);
//            if(list.size() > 1){
//                endRow += list.size()-1;
//                CellRangeAddress region = new CellRangeAddress(startRow, endRow, 2, 2);
//                sheet.addMergedRegion(region);
//                CellRangeAddress regionEnd = new CellRangeAddress(startRow, endRow, 4, 4);
//                sheet.addMergedRegion(regionEnd);
//                startRow = endRow + 1;
//            } else {
//                startRow+=1;
//            }
//            endRow = startRow;
//        }
//
//        *//*Sheet sheet =workbook.getSheetAt(0);
//        //开始合并的行数
//        Integer startRow = 2;
//        //结束合并的行数
//        Integer endRow = 3;
//        //开始合并的列数
//        Integer startCol = 2;
//        Integer endCol = 2;
//
//        //合并单元格
//        CellRangeAddress region = new CellRangeAddress(startRow, endRow, startCol, endCol);
//        sheet.addMergedRegion(region);*//*
//
//        try {
//            FileOutputStream fileOutputStream = new FileOutputStream("/users/xymar/tiny_basics_init_his.xls");
//            workbook.write(fileOutputStream);
//            workbook.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }*/
//    }
//
//    public Map<String, List<TinyBaseInitHisEntity>> sortMapByValue(Map<String, List<TinyBaseInitHisEntity>> oriMap) {
//        Map<String, List<TinyBaseInitHisEntity>> sortedMap = new LinkedHashMap<>();
//        if (oriMap != null && !oriMap.isEmpty()) {
//            List<Map.Entry<String, List<TinyBaseInitHisEntity>>> entryList = new ArrayList<>(oriMap.entrySet());
//            Collections.sort(entryList,
//                    new Comparator<Map.Entry<String, List<TinyBaseInitHisEntity>>>() {
//                        public int compare(Map.Entry<String, List<TinyBaseInitHisEntity>> entry1,
//                                           Map.Entry<String, List<TinyBaseInitHisEntity>> entry2) {
//                            int value1 = 0, value2 = 0;
//                            try {
//                                value1 = entry1.getValue().get(0).getNum();
//                                value2 = entry2.getValue().get(0).getNum();
//                            } catch (NumberFormatException e) {
//                                value1 = 0;
//                                value2 = 0;
//                            }
//                            return value1 - value2;
//                        }
//                    });
//            Iterator<Map.Entry<String, List<TinyBaseInitHisEntity>>> iter = entryList.iterator();
//            Map.Entry<String, List<TinyBaseInitHisEntity>> tmpEntry = null;
//            while (iter.hasNext()) {
//                tmpEntry = iter.next();
//                sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
//            }
//        }
//        return sortedMap;
//    }
//
//}

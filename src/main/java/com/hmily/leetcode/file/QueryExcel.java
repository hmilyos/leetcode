package com.hmily.leetcode.file;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueryExcel {

    private static  final Logger log = LoggerFactory.getLogger(QueryExcel.class);

    public static void main(String[] args) {
        // 读取Excel文件
        File file = new File("E:/allData.xlsx");
//       我用的 Excel 模板见同目录下的
        ArrayList<Map<String, Object>> datas = getDataFromExcel(file);
        for (Map data: datas) {
            log.info("res model: {}, num: {}", data.get("model"), data.get("num"));
        }

    }

    public static ArrayList<Map<String, Object>> getDataFromExcel(File file) {
        String filename = file.getName();
//        String filename = file.getOriginalFilename();
        // 判断是否为excel类型文件
        int type = -1;
        if (filename.endsWith(".xls")) {
            log.info("文件为：.XLS 格式");
            type = 1;
        } else if (filename.endsWith(".xlsx")) {
            log.info("文件为：.XLSX 格式");
            type = 2;
        } else  {
            log.info("{} 文件不是 excel 类型 ", filename);
            throw new RuntimeException("只支持.xlsx 或者 .xls");
        }
        try {
            ArrayList<Map<String, Object>> dataFromExcel = getDataFromExcel(new FileInputStream(file), type);
            return dataFromExcel;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static ArrayList<Map<String, Object>> getDataFromExcel(FileInputStream fis, int type) {
        Workbook wookbook = null;
        int flag = 0;
        try {
            if (type == 1) {
                // 2003版本的excel，用.xls结尾
                wookbook = new HSSFWorkbook(fis);// 得到工作簿
            } else {
                // 2007版本的excel，用.xlsx结尾
                wookbook = new XSSFWorkbook(fis);// 得到工作簿
            }

        } catch (IOException e) {
            log.error("fis --> wookbook error", e);
            throw new RuntimeException("服务端获取工作簿数据异常，请查看服务器日志");
        } catch (Exception e) {
            log.error("fis --> wookbook error", e);
            throw new RuntimeException("服务端获取工作簿数据异常，请查看服务器日志");
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                log.error("fis close error", e);
                throw new RuntimeException("关闭数据流异常，请查看服务器日志");
            }
        }
        // 得到一个工作表
        Sheet sheet = wookbook.getSheetAt(0);
//        wookbook.getSheet("页签two")  //也可以根据页签名称获取

        // 获得表头
        Row rowHead = sheet.getRow(0);

        // 根据不同的data放置不同的表头
        Map<Object, Integer> headMap = new HashMap<Object, Integer>();

        // 判断表头是否合格 ------------------------这里看你有多少列
        if (rowHead.getPhysicalNumberOfCells() != 2) {
            throw new RuntimeException("上传的文件表头列数与导入模板表头列数不匹配");
        }

        try {
            // ----------------这里根据你的表格有多少列

            while (flag < 2) {
                Cell cell = rowHead.getCell(flag);
                if (ExcelgetRightTypeCellUtil.getRightTypeCell(cell, Cell.CELL_TYPE_NUMERIC -1).toString().equals("型号")) {
                    headMap.put("model", flag);
                }
                if (ExcelgetRightTypeCellUtil.getRightTypeCell(cell, Cell.CELL_TYPE_NUMERIC -1).toString().equals("数量")) {
                    headMap.put("num", flag);
                }
                flag++;
            }
        } catch (Exception e) {
            log.error("getRightTypeCell error ", e);
            throw new RuntimeException("表头不合规范，请修改后重新导入");
        }
        // 获得数据的总行数
        int totalRowNum = sheet.getLastRowNum();
        if (0 == totalRowNum) {
            log.info("Excel内没有数据！");
            throw new RuntimeException("上传的Excel内没有数据！");
        }

        Cell cell_model = null, cell_num = null;
//
        // 获得所有数据
        ArrayList<Map<String, Object>> datas = new ArrayList<Map<String, Object>>(totalRowNum);
        List<String> errorInfo = new ArrayList<String>();
        for (int i = 1; i <= totalRowNum; i++) {
            // 获得第i行对象
            Row row = sheet.getRow(i);

            try {
                cell_model = row.getCell(headMap.get("model"));
                cell_num = row.getCell(headMap.get("num"));
            } catch (Exception e) {
                log.error("获取单元格错误", e);
                throw new RuntimeException("获取单元格错误，详细信息请查看服务器日志");
            }
            // 要获得属性
            String model = null;
            Double num = null;

            try {
                if (null != ExcelgetRightTypeCellUtil.getRightTypeCell(cell_model, Cell.CELL_TYPE_NUMERIC -1)) {
                    model = (String) ExcelgetRightTypeCellUtil.getRightTypeCell(cell_model, Cell.CELL_TYPE_NUMERIC -1);
                } else {
                    throw new RuntimeException("第 " + i + " 行型号名称必填！");
                }
                if (null != ExcelgetRightTypeCellUtil.getRightTypeCell(cell_num, Cell.CELL_TYPE_NUMERIC -1)) {
                    num = (Double) ExcelgetRightTypeCellUtil.getRightTypeCell(cell_num, Cell.CELL_TYPE_NUMERIC -1);
                } else {
                    errorInfo.add("第 " + i + " 行数量必填！");
                }

            } catch (NullPointerException e) {
                log.error("获取表格内的数据为空：", e);
                throw new RuntimeException(e.getMessage());
            } catch (ClassCastException e) {
                log.error("数据格式不匹配", e);
                throw new RuntimeException("数据格式不匹配!");
            } catch (Exception e) {
                log.error("获取表格内的数据 error ", e);
                throw new RuntimeException("数据格式不匹配!详细信息请查看服务器日志");
            }
            HashMap<String, Object> map = new HashMap<>(2);
            map.put("model", model);
            map.put("num", num);
            log.info("解析出 model：{}, num: {}", model, num);
            datas.add(map);
        }
        log.info(" ==> datas size: {}", datas.size());
        return datas;
    }

}

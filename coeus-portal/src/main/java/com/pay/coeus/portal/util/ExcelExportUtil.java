package com.pay.coeus.portal.util;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

  
/** 
 * 导出Excel工具类 
 *  
 */  
public class ExcelExportUtil {  
      
    private static final Logger logger = LoggerFactory.getLogger(ExcelExportUtil.class);  
      
    /** 日期格式 yyyy-MM-dd*/  
    private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
      
    /** 日期格式yyyy-MM-dd HH:mm:ss*/  
    private static final DateFormat DATE_FORMAT_YYYY_MM_DD_HH_MM_SS=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
    /** 
     * 单元格映射 
     */  
    public static class CellMap {  
        private String title;// 标题  
        private String property;// 属性  
          
        public CellMap(String title, String property) {  
            this.title = title;  
            this.property = property;  
        }  
  
        public String getTitle() {  
            return title;  
        }  
  
        public void setTitle(String title) {  
            this.title = title;  
        }  
  
        public String getProperty() {  
            return property;  
        }  
  
        public void setProperty(String property) {  
            this.property = property;  
        }  
  
    }  
  
    /** 
     * 导出Excel 
     * @param cellMapList 单元格映射列表 
     * @param dataList 数据列表 
     * @param rowAccessWindowSize 内存中缓存记录数 
     * @param out 输出流 
     * @throws Exception 
     */  
    public static void exportSXSSFExcel(String sheetName, List<CellMap> cellMapList, List<?> dataList, int rowAccessWindowSize, OutputStream out) throws Exception {  
        SXSSFWorkbook workbook = new SXSSFWorkbook(rowAccessWindowSize);  
        Sheet sheet = workbook.createSheet(sheetName);  
        Row row = null;  
        Cell cell = null;  
        if (cellMapList == null || cellMapList.size() <= 0) {  
            throw new Exception("cellMapList不能为空或小于等于0");  
        }  
        int rowIndex = 0;  
        // 标题  
        Font titleFont = workbook.createFont();  
        titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
        CellStyle titleCellStyle = workbook.createCellStyle();  
        titleCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
        titleCellStyle.setFont(titleFont);  
        row = sheet.createRow(rowIndex++);  
        int cellSize = cellMapList.size();  
        for (int i = 0; i < cellSize; i++) {  
            CellMap cellMap = cellMapList.get(i);  
            String title = cellMap.getTitle();  
            cell = row.createCell(i);  
            cell.setCellStyle(titleCellStyle);  
            cell.setCellValue(title);  
            if (title != null) {  
                sheet.setColumnWidth(i, title.getBytes().length * 2 * 172);  
            }  
        }  
        // 数据  
        CellStyle dataCellStyle = workbook.createCellStyle();  
        dataCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
        int rowSize = (dataList == null) ? 0 : dataList.size();  
        for (int i = rowIndex; i < rowSize + rowIndex; i++) {  
            Object obj = dataList.get(i - rowIndex);  
            row = sheet.createRow(i);  
            for (int j = 0; j < cellSize; j++) {  
                CellMap cellMap = cellMapList.get(j);  
                cell = row.createCell(j);  
                cell.setCellStyle(dataCellStyle);  
                String property = cellMap.getProperty();  
                if(property.equals("rowNumber") || StringUtils.isEmpty(property)){  
                    cell.setCellValue(i);  
                }else{  
                    String propertyValue = getPropertyValue(obj, property);  
                    cell.setCellValue(propertyValue);  
                    if (propertyValue != null) {  
                        int columnWidth = sheet.getColumnWidth(j);  
                        int propertyValueLength = propertyValue.getBytes().length * 2 * 172;  
                        if (columnWidth < propertyValueLength) {  
                            sheet.setColumnWidth(j, propertyValueLength);  
                        }  
                    }  
                }  
                      
            }  
        }  
        workbook.write(out);  
    }  
    /** 
     * 导出Excel 
     * @param cellMapList 单元格映射列表 
     * @param dataList 数据列表 
     * @param rowAccessWindowSize 内存中缓存记录数 
     * @param out 输出流 
     * @throws Exception 
     */  
    public static void exportSXSSFExcelFromMapData(String sheetName, List<CellMap> cellMapList, List<Map<String,String>> dataList, int rowAccessWindowSize, OutputStream out) throws Exception {  
        SXSSFWorkbook workbook = new SXSSFWorkbook(rowAccessWindowSize);  
        Sheet sheet = workbook.createSheet(sheetName);  
        Row row = null;  
        Cell cell = null;  
        if (cellMapList == null || cellMapList.size() <= 0) {  
            throw new Exception("cellMapList不能为空或小于等于0");  
        }  
        int rowIndex = 0;  
        // 标题  
        Font titleFont = workbook.createFont();  
        titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
        CellStyle titleCellStyle = workbook.createCellStyle();  
        titleCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
        titleCellStyle.setFont(titleFont);  
        row = sheet.createRow(rowIndex++);  
        int cellSize = cellMapList.size();  
        for (int i = 0; i < cellSize; i++) {  
            CellMap cellMap = cellMapList.get(i);  
            String title = cellMap.getTitle();  
            cell = row.createCell(i);  
            cell.setCellStyle(titleCellStyle);  
            cell.setCellValue(title);  
            if (title != null) {  
                sheet.setColumnWidth(i, title.getBytes().length * 2 * 172);  
            }  
        }  
        // 数据  
        CellStyle dataCellStyle = workbook.createCellStyle();  
        dataCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
        int rowSize = (dataList == null) ? 0 : dataList.size();  
        for (int i = rowIndex; i < rowSize + rowIndex; i++) {  
            Map<String,String> obj = dataList.get(i - rowIndex);  
            row = sheet.createRow(i);  
            for (int j = 0; j < cellSize; j++) {  
                CellMap cellMap = cellMapList.get(j);  
                cell = row.createCell(j);  
                cell.setCellStyle(dataCellStyle);  
                String property = cellMap.getProperty();  
                if(property.equals("rowNumber") || StringUtils.isEmpty(property)){  
                    cell.setCellValue(i);  
                }else{  
//                  String propertyValue = getPropertyValue(obj, property);  
                    String propertyValue = obj.get(property);  
                    cell.setCellValue(propertyValue);  
                    if (propertyValue != null) {  
                        int columnWidth = sheet.getColumnWidth(j);  
                        int propertyValueLength = propertyValue.getBytes().length * 2 * 172;  
                        if (columnWidth < propertyValueLength) {  
                            sheet.setColumnWidth(j, propertyValueLength);  
                        }  
                    }  
                }  
                      
            }  
        }  
        workbook.write(out);  
    }  
      
    /** 
     * 获取属性值 
     * @param obj 
     * @param property 
     * @return 
     * @throws Exception 
     */  
    private static String getPropertyValue(Object obj, String property) throws Exception {  
        if (obj instanceof Map)  
        {  
            Object val = ((Map<String,Object>)obj).get(property);  
            if (val==null)  
            {  
                return "";  
            }  
            return val.toString();  
        }  
        Object result = null;  
        String str = "";  
        Class<?> clazz = obj.getClass();  
        if (property == null || "".equals(property)) {  
            return "";  
        }  
        Method readMethod = clazz.getMethod("get" + property.substring(0, 1).toUpperCase() + property.substring(1));  
        if (readMethod != null) {  
            result = readMethod.invoke(obj);  
        }  
        if (result != null) {  
            if (result.getClass() == Date.class) {  
                str = DATE_FORMAT_YYYY_MM_DD_HH_MM_SS.format(result);  
            } else {  
                str = result.toString();  
            }  
        } else {  
            str = "";  
        }  
        return str;  
    }  
    /** 
     * 填充excel数据 
     * <功能详细描述> 
     * @param dataCellStyle 
     * @param cellSize 
     * @param sheet 
     * @param rowIndex 
     * @param workbook 
     * @param response 
     * @param excelName 
     * @param cellMapList 
     * @param dataList 
     * @return 
     * @throws Exception 
     * @see [类、类#方法、类#成员] 
     */  
    public static int fillExcel_2007_SXSSF(CellStyle dataCellStyle,int cellSize,Sheet sheet,int rowIndex,SXSSFWorkbook workbook,HttpServletResponse response,String excelName, List<CellMap> cellMapList, List<?> dataList) throws Exception{  
        Row row = null;  
        Cell cell = null;  
        int rowSize = (dataList == null) ? 0 : dataList.size();  
        for (int i = rowIndex; i < rowSize + rowIndex; i++) {  
            Object obj = dataList.get(i - rowIndex);  
            row = sheet.createRow(i);  
            for (int j = 0; j < cellSize; j++) {  
                CellMap cellMap = cellMapList.get(j);  
                cell = row.createCell(j);  
                cell.setCellStyle(dataCellStyle);  
                String property = cellMap.getProperty();  
                if(property.equals("rowNumber") || StringUtils.isEmpty(property)){  
                    cell.setCellValue(i);
                }else{  
                    String propertyValue = getPropertyValue(obj, property);  
                    cell.setCellValue(propertyValue);  
                    if (propertyValue != null) {  
                        int columnWidth = sheet.getColumnWidth(j);  
                        int propertyValueLength = propertyValue.getBytes().length * 2 * 172;  
                        if (columnWidth < propertyValueLength) {  
//                            sheet.setColumnWidth(j, propertyValueLength);  
                        }  
                    }  
                }  
            }  
        }  
        return rowSize + rowIndex;  
    }  
   
}  
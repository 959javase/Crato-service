package com.nash.product.utils;

import com.nash.product.entity.crust.CrustNode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class ExcelUtils {
    /**
     * sheet总行数
     */
    public int totalRows;
    /**
     * 每一行总单元格数
     */
    public static int totalCells;
    /**
     * 格式
     */
    public static final String OFFICE_EXCEL_2003_POSTFIX = "xls";
    /**
     * 格式
     */
    public static final String OFFICE_EXCEL_2010_POSTFIX = "xlsx";
    /**
     * 空
     */
    public static final String EMPTY = "";
    /**
     * 点
     */
    public static final String POINT = ".";
    /**
     * 日期格式
     */
    public static SimpleDateFormat sdf =   new SimpleDateFormat("yyyy/MM/dd");

    /**
     * 获得path的后缀名
     * @param path
     * @return
     */
    public static String getPostfix(String path){
        if (StringUtils.isBlank(path.trim())) {
            return EMPTY;
        }
        if (path.contains(POINT)) {
            return path.substring(path.lastIndexOf(POINT)+1,path.length());
        }
        return EMPTY;
    }

    /**
     * 单元格格式
     * @param hssfCell
     * @return
     */
    @SuppressWarnings({ "static-access", "deprecation" })
    public static String getHValue(HSSFCell hssfCell){
        if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(hssfCell.getBooleanCellValue());
        } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
            String cellValue = "";
            if (HSSFDateUtil.isCellDateFormatted(hssfCell)) {
                Date date = HSSFDateUtil.getJavaDate(hssfCell.getNumericCellValue());
                cellValue = sdf.format(date);
            } else {
                DecimalFormat df = new DecimalFormat("#.##");
                cellValue = df.format(hssfCell.getNumericCellValue());
                String strArr = cellValue.substring(cellValue.lastIndexOf(POINT)+1,cellValue.length());
                if (strArr.equals("00")) {
                    cellValue = cellValue.substring(0, cellValue.lastIndexOf(POINT));
                }
            }
            return cellValue;
        } else {
            return String.valueOf(hssfCell.getStringCellValue());
        }
    }
    /**
     * 单元格格式
     * @param xssfCell
     * @return
     */
    public static String getXValue(XSSFCell xssfCell){
        if (xssfCell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(xssfCell.getBooleanCellValue());
        } else if (xssfCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            String cellValue = "";
            if (XSSFDateUtil.isCellDateFormatted(xssfCell)) {
                Date date = XSSFDateUtil.getJavaDate(xssfCell.getNumericCellValue());
                cellValue = sdf.format(date);
            } else {
                DecimalFormat df = new DecimalFormat("#.##");
                cellValue = df.format(xssfCell.getNumericCellValue());
                String strArr = cellValue.substring(cellValue.lastIndexOf(POINT)+1,cellValue.length());
                if(strArr.equals("00")){
                    cellValue = cellValue.substring(0, cellValue.lastIndexOf(POINT));
                }
            }
            return cellValue;
        } else {
            return String.valueOf(xssfCell.getStringCellValue());
        }
    }
    /**
     * 自定义xssf日期工具类
     */
    static class XSSFDateUtil extends DateUtil {
        /**
         * 日期工具类
         * @param cal
         * @param use1904windowing
         * @return
         */
        protected static int absoluteDay(Calendar cal, boolean use1904windowing) {
            return DateUtil.absoluteDay(cal, use1904windowing);
        }
    }
    /**
     * read the Excel .xlsx,.xls
     * @param file jsp中的上传文件
     * @return
     * @throws IOException
     */
    public List<ArrayList<String>> readExcel(MultipartFile file) throws IOException {
        if(file == null || StringUtils.isBlank(file.getOriginalFilename().trim())){
            return null;
        } else {
            String postfix = getPostfix(file.getOriginalFilename());
            if(!StringUtils.isBlank(postfix)){
                if(OFFICE_EXCEL_2003_POSTFIX.equals(postfix)){
                    return readXls(file);
                }else if(OFFICE_EXCEL_2010_POSTFIX.equals(postfix)){
                    return readXlsx(file);
                }else{
                    return null;
                }
            }
        }
        return null;
    }
    /**
     * read the Excel 2010 .xlsx
     * @param file
     * @return
     * @throws IOException
     */
    @SuppressWarnings("deprecation")
    public List<ArrayList<String>> readXlsx(MultipartFile file){
        List<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
        /**
         * excel对象
         */
        XSSFWorkbook wb = null;
        /**
         * excel行列表
         */
        ArrayList<String> rowList = null;
        try {
            // 创建文档
            wb = new XSSFWorkbook(file.getInputStream());
            //读取sheet(页)
            for(int numSheet = 0; numSheet < wb.getNumberOfSheets(); numSheet++){
                XSSFSheet xssfSheet = wb.getSheetAt(numSheet);
                if(xssfSheet == null){
                    continue;
                }
                totalRows = xssfSheet.getLastRowNum();
                //读取Row,从第2行开始
                for(int rowNum = 1; rowNum <= totalRows; rowNum++){
                    XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                    if(xssfRow != null){
                        rowList = new ArrayList<String>();
                        totalCells = xssfRow.getLastCellNum();
                        //读取列，从第一列开始
                        for(int c = 0; c <= totalCells + 1; c++){
                            XSSFCell cell = xssfRow.getCell(c);
                            if(cell == null){
                                rowList.add(EMPTY);
                                continue;
                            }
                            rowList.add(getXValue(cell).trim());
                        }
                        list.add(rowList);
                    }
                }
            }
            return list;
        } catch (IOException e) {
            log.error("parse excel failed: ", e.getMessage());
        }
        return null;

    }
    /**
     * read the Excel 2003-2007 .xls
     * @param file
     * @return
     * @throws IOException
     */
    public List<ArrayList<String>> readXls(MultipartFile file){
        List<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
        // IO流读取文件
        InputStream input = null;
        HSSFWorkbook wb = null;
        ArrayList<String> rowList = null;
        try {
            input = file.getInputStream();
            // 创建文档
            wb = new HSSFWorkbook(input);
            //读取sheet(页)
            for(int numSheet=0;numSheet<wb.getNumberOfSheets();numSheet++){
                HSSFSheet hssfSheet = wb.getSheetAt(numSheet);
                if(hssfSheet == null){
                    continue;
                }
                totalRows = hssfSheet.getLastRowNum();
                //读取Row,从第二行开始
                for(int rowNum = 1;rowNum <= totalRows;rowNum++){
                    HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                    if(hssfRow!=null){
                        rowList = new ArrayList<String>();
                        totalCells = hssfRow.getLastCellNum();
                        //读取列，从第一列开始
                        for(short c=0;c<=totalCells+1;c++){
                            HSSFCell cell = hssfRow.getCell(c);
                            if(cell==null){
                                rowList.add(EMPTY);
                                continue;
                            }
                            rowList.add(getHValue(cell).trim());
                        }
                        list.add(rowList);
                    }
                }
            }
            return list;
        } catch (IOException e) {
            log.error("parse excel failed: ", e.getMessage());
        } finally{
            try {
                input.close();
            } catch (IOException e) {
                log.error("excel stream failed: ", e.getMessage());
            }
        }
        return null;
    }

    /**
     * 用户信息导出类
     * @param response 响应
     * @param fileName 文件名
     * @param columnList 每列的标题名
     * @param dataList 导出的数据
     */
    public static void uploadExcelAboutUser(HttpServletResponse response, String fileName, List<String> columnList,List<List<String>> dataList){
        //声明输出流
        OutputStream os = null;
        //设置响应头
        try {
            try {
                fileName = new String(fileName.getBytes(),"ISO8859-1");
            } catch (UnsupportedEncodingException e) {
                log.error("parse file name failed: %s", e.getMessage());
            }
            response.setContentType("application/octet-stream;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            log.error("set response header failed: ", ex.getMessage());
            return;
        }
        try {
            //获取输出流
            os = response.getOutputStream();
            //内存中保留1000条数据，以免内存溢出，其余写入硬盘
            SXSSFWorkbook wb = new SXSSFWorkbook(1000);
            //获取该工作区的第一个sheet
            Sheet sheet1 = wb.createSheet("sheet1");
            int excelRow = 0;
            //创建标题行
            Row titleRow = sheet1.createRow(excelRow++);
            for(int i = 0;i<columnList.size();i++){
                //创建该行下的每一列，并写入标题数据
                Cell cell = titleRow.createCell(i);
                cell.setCellValue(columnList.get(i));
            }
            //设置内容行
            if(dataList!=null && dataList.size()>0){
                //序号是从1开始的
                int count = 1;
                //外层for循环创建行
                for(int i = 0;i<dataList.size();i++){
                    Row dataRow = sheet1.createRow(excelRow++);
                    //内层for循环创建每行对应的列，并赋值
                    for(int j = -1;j<dataList.get(0).size();j++){//由于多了一列序号列所以内层循环从-1开始
                        Cell cell = dataRow.createCell(j+1);
                        if(j==-1){//第一列是序号列，不是在数据库中读取的数据，因此手动递增赋值
                            cell.setCellValue(count++);
                        }else{//其余列是数据列，将数据库中读取到的数据依次赋值
                            cell.setCellValue(dataList.get(i).get(j));
                        }
                    }
                }
            }
            //将整理好的excel数据写入流中
            wb.write(os);
        } catch (IOException e) {
            log.error("export crust node excel failed: %s", e.getMessage());
        } finally {
            try {
                // 关闭输出流
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                log.error("close crust node excel stream failed: %s", e.getMessage());
            }
        }
    }

    public static List<List<String>> convertCrustNode(List<CrustNode> crustNodes) {
        if (crustNodes == null || crustNodes.size() == 0) {
            return null;
        }
        List<List<String>> ret = new ArrayList<List<String>>();
        for (CrustNode node: crustNodes) {
            List<String> item = new ArrayList<String>();
            item.add(node.getAccountName());
            item.add(node.getHostIp());
            item.add(node.getSerialId());
            item.add(node.getMnemonic());
            item.add(node.getJson());
            item.add(node.getOwnerNode());
            item.add(node.getAccountType());
            item.add(node.getNodeType());
            item.add(node.getServiceType());
            ret.add(item);
        }
        return ret;
    }
}

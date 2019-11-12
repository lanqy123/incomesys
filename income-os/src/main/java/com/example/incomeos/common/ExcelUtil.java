package com.example.incomeos.common;

import com.example.incomeos.bean.InfoDto;
import org.apache.poi.hssf.usermodel.*;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * description:
 *
 * @author qingyu.lan@ucarinc.com
 * @version 1.0
 * @date: 2019-11-12 09:11:45
 **/
public class ExcelUtil {
    /**
     * Excel表格导出
     * @param response HttpServletResponse对象
     * @param excelData Excel表格的数据，封装为List<List<String>>
     * @param sheetName sheet的名字
     * @param fileName 导出Excel的文件名
     * @param columnWidth Excel表格的宽度，建议为15
     * @throws IOException 抛IO异常
     */
    public static void exportExcel(HttpServletResponse response,
                                   List<Map<String, Object>> excelData,
                                   String sheetName,
                                   String fileName,
                                   int columnWidth) throws IOException {

        //声明一个工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();

        //生成一个表格，设置表格名称
        HSSFSheet sheet = workbook.createSheet(sheetName);

        //设置表格列宽度
        sheet.setDefaultColumnWidth(columnWidth);

        String[] headers = {"姓名", "金额","备注","创建时间"};

        //写入List<List<String>>中的数据
        int rowIndex = 0;
        HSSFRow row = sheet.createRow(rowIndex++);
        for (int j = 0; j < headers.length; j++) {
            HSSFCell cell = row.createCell(j);
            //创建一个内容对象
            HSSFRichTextString text = new HSSFRichTextString(headers[j]);
            //将内容对象的文字内容写入到单元格中
            cell.setCellValue(text);

        }


        for(Map<String, Object> data : excelData){
            //创建一个row行，然后自增1
            row = sheet.createRow(rowIndex++);

            int i =0;

            //创建一个单元格
            HSSFCell cell = row.createCell(i++);


            String name = (String) data.get("name");
            String money = data.get("money").toString();
            String detail = (String) data.get("detail");
            Date createTime = (Date) data.get("createTime");
            //创建一个内容对象
            HSSFRichTextString text = new HSSFRichTextString(name);
            //将内容对象的文字内容写入到单元格中
            cell.setCellValue(text);

            cell = row.createCell(i++);


            //创建一个内容对象
            text = new HSSFRichTextString(money);
            //将内容对象的文字内容写入到单元格中
            cell.setCellValue(text);

            cell = row.createCell(i++);

            //创建一个内容对象
            text = new HSSFRichTextString(detail);
            //将内容对象的文字内容写入到单元格中
            cell.setCellValue(text);

            cell = row.createCell(i++);

            SimpleDateFormat a=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            //创建一个内容对象
            text = new HSSFRichTextString(a.format(createTime));
            //将内容对象的文字内容写入到单元格中
            cell.setCellValue(text);

            }

        //准备将Excel的输出流通过response输出到页面下载
        //八进制输出流
        response.setContentType("application/vnd.ms-excel;charset=utf-8");

        //设置导出Excel的名称
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);

        //刷新缓冲
        response.flushBuffer();

        //workbook将Excel写入到response的输出流中，供页面下载该Excel文件
        workbook.write(response.getOutputStream());

        //关闭workbook
        workbook.close();
    }

    /**
     *list<XXXDto>转list<map>
     * @param listObj
     * @return
     * @throws IllegalAccessException
     */
    public static List<Map<String, Object>> objectToMap(List<InfoDto> listObj) throws IllegalAccessException {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < listObj.size(); i++) {
            InfoDto obj = listObj.get(i);
            Map<String, Object> map = new HashMap<>();
            Class<?> clazz = obj.getClass();
            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);
                String fieldName = field.getName();
                Object value = field.get(obj);
                map.put(fieldName, value);
            }
            list.add(map);
        }
        return list;
    }
}

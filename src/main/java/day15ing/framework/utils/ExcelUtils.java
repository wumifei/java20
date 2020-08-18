package day15ing.framework.utils;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import day15ing.framework.pojo.RegisterExcelInfo;
import day15ing.framework.pojo.RegisterExcelInfo2;
import day15ing.framework.pojo.WriteBackExcel;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ExcelUtils {
/*    public static void main(String[] args) {
        List<RegisterExcelInfo> list = readExcel(0,1,RegisterExcelInfo.class);
        for (RegisterExcelInfo list1 : list) {
            System.out.println(list1);
        }
        QueryRunner runner = new QueryRunner();

    }*/
    //创建list集合WriteBackExcel对象
    public static List<WriteBackExcel> list=new ArrayList<>();


    /**
     *读excel  easypoi
     * @param startSheetIndex 从第几个sheet页开始读，0是第一个
     * @param sheetNum 读几个sheet页
     * @param clazz pojo对象类
     * @return
     */
    public static List readExcel(int startSheetIndex,int sheetNum,Class clazz) {
        try {
            FileInputStream fis = new FileInputStream(Constants.EXCEL_PATH);
            ImportParams params = new ImportParams();
            params.setStartSheetIndex(startSheetIndex);
            params.setSheetNum(sheetNum);
            List<RegisterExcelInfo2> list = ExcelImportUtil.importExcel(fis, clazz, params);

            fis.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 写数据到excel
     */
    public static void writeBackExcel(){
        FileInputStream fis=null;
        FileOutputStream fos=null;
        try {
            fis = new FileInputStream(Constants.EXCEL_PATH);
            Workbook sheets = WorkbookFactory.create(fis);
            //遍历添加到WriteBackExcel对象的list集合
            for (WriteBackExcel backExcel : list) {
                Sheet sheet = sheets.getSheetAt(backExcel.getSheetIndex());
                Row row = sheet.getRow(backExcel.getRowNum());
                Cell cell = row.createCell(backExcel.getCellNum());
                cell.setCellValue(backExcel.getContent());
            }
            fos = new FileOutputStream(Constants.EXCEL_PATH);
            sheets.write(fos);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(fis!=null){
                    fis.close();
                }
                if(fos!=null){
                    fos.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }
}

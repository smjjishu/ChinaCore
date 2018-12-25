package com.example.project.excel;

import java.io.*;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

public class ExcelHelper {

    private static final String EXCEL_XLS = "xls";
    private static final String EXCEL_XLSX = "xlsx";


    /**
     * 判断Excel的版本,获取Workbook
     */
    public static Workbook getWorkbok(InputStream in, File file) throws IOException {
        Workbook wb = null;
        if (file.getName().endsWith(EXCEL_XLS)) {     //Excel 2003
            wb = new HSSFWorkbook(in);
        } else if (file.getName().endsWith(EXCEL_XLSX)) {    // Excel 2007/2010
            wb = new XSSFWorkbook(in);
        }
        return wb;
    }

    /**
     * 判断文件是否是excel
     */
    public static void checkExcelVaild(File file) throws Exception {
        if (!file.exists()) {
            throw new Exception("文件不存在");
        }
        if (!(file.isFile() && (file.getName().endsWith(EXCEL_XLS) || file.getName().endsWith(EXCEL_XLSX)))) {
            throw new Exception("文件不是Excel");
        }
    }

    /**
     * 读取Excel测试，兼容 Excel 2003/2007/2010
     */
    public static void Read() throws Exception {
        try {
            // 同时支持Excel 2003、2007
            File excelFile = new File("H:/test.xlsx"); // 创建文件对象
            FileInputStream is = new FileInputStream(excelFile); // 文件流
            checkExcelVaild(excelFile);
            Workbook workbook = getWorkbok(is, excelFile);
            int sheetCount = workbook.getNumberOfSheets(); // Sheet的数量

            //设置当前excel中sheet的下标：0开始
            Sheet sheet = workbook.getSheetAt(0);    // 遍历第一个Sheet
            // 为跳过第一行目录设置count
            int count = 0;
            System.out.println("------------------------------------------------------------------------------------------------");
            for (Row row : sheet) {
                if (count == 0) {
                    count++;
                    continue;
                }
                String str = row.getCell(0).toString() + "    " + row.getCell(1).toString() + "    " + row.getCell(2).toString() + "    " + row.getCell(03).toString();
                System.out.println(str);
            }
            System.out.println("------------------------------------------------------------------------------------------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 写入Excel测试
     */
    public static void Write() throws Exception {
        XSSFWorkbook workbook = new XSSFWorkbook(); // 创建工作簿
        XSSFSheet sheet = workbook.createSheet("员工信息表"); // 创建一个工作表
//        XSSFCellStyle style = workbook.createCellStyle(); // 创建单元格风格对象
//        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 10)); // 合并第一行的单元格
//        style.setAlignment(HorizontalAlignment.CENTER); // 设置水平居中
//        style.setVerticalAlignment(VerticalAlignment.CENTER); // 设置垂直居中
//        XSSFFont font = workbook.createFont(); // 创建字体的对象
//        font.setFontName("黑体"); // 设置字体的样式为黑体
//        font.setFontHeightInPoints((short) 20); // 设置字体的大小
//        font.setBold(true); // 设置粗体
//        font.setItalic(true); // 设置倾斜
//        font.setColor(HSSFColor.RED.index); // 设置字体的颜色
//        font.setUnderline(FontUnderline.SINGLE); // 设置下划线
//        font.setStrikeout(false); // 设置不带下划线
//        style.setFont(font); // 将设置的字体添加到单元格样式中，显示出来
//        XSSFRow row1 = sheet.createRow(0); // 创建第一个行
//        XSSFCell cell1 = row1.createCell(0); // 创建第一行的第一列
//        cell1.setCellStyle(style); // 将上面定义的风格设置到这个单元格中，这个是必须有的，否则根本不起作用
//        cell1.setCellValue("员工信息表"); // 设置单元格的内容
        // 设置第1行的前三列的值
        XSSFRow row0 = sheet.createRow(0);
        row0.createCell(0).setCellValue("姓名");
        row0.createCell(1).setCellValue("性别");
        row0.createCell(2).setCellValue("年龄");
        for (int i = 1; i <= 10; i++) {
            XSSFRow row = sheet.createRow(i);
            row.createCell(0).setCellValue(i + ".陈加兵");
            row.createCell(1).setCellValue("男." + i);
            row.createCell(2).setCellValue(22 + i);
        }

        // 创建输出流对象
        FileOutputStream stream = new FileOutputStream(new File("H:\\test1.xlsx"));
        workbook.write(stream); // 写入文件
        workbook.close(); // 关闭
        stream.close();
    }
}





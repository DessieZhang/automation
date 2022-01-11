package com.epam.part3.task2;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.codehaus.plexus.util.StringUtils;


import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ExcelUtils {


        private static Logger logger = Logger.getLogger(ExcelUtils.class.getName()); // 日志打印类

        /**
         * 读取Excel文件内容
         * @param filePath 要读取的Excel文件所在路径
         * @param sheetName 要读取的Excel文件所在路径
         * @return 读取结果列表，读取失败时返回null
         */
        public static List<Flower> readExcel(String filePath, String sheetName) throws Exception {
            InputStream ins = new FileInputStream(filePath);
            XSSFWorkbook wb = null;
            wb = new XSSFWorkbook(ins);
            XSSFSheet sheet1 = wb.getSheet(sheetName);
            int rowCount = sheet1.getLastRowNum();
            int firstRowNum = 0;
            List<Flower> values = new ArrayList<Flower>();
            List<String> head = new ArrayList<String>();
            for (int currentRowNum = firstRowNum; currentRowNum <= rowCount; currentRowNum++) {
                XSSFRow row = sheet1.getRow(currentRowNum);
                Flower rowValue = new Flower();
                for (int i = 0; i < sheet1.getRow(0).getLastCellNum(); i++) {
                    // get column name values
                    if (currentRowNum == 0) {
                        head.add(getStrValue(row.getCell(i)));
                    } else {
                        rowValue=convertRowToData(row);
                    }
                }
                if(currentRowNum > 0) { //will add 1st row value to the list
                    values.add(rowValue);
                }
            }
            ins.close();
            wb.close();
            return values;
        }

    public static String getStrValue(XSSFCell cell) {
        String strval = null;
        if (cell==null) {
            return "";
        }
        if (cell.getCellType() == CellType.NUMERIC) {
            strval = String.valueOf((int) cell.getNumericCellValue());
        } else {
            strval = cell.getStringCellValue();
        }
        if (null == strval || "NULL".equalsIgnoreCase(strval)) {
            strval = "";
        }
        char nbsp = 0x00a0;
        return StringUtils.trim(strval.replace(nbsp, ' '));
    }


            /**
             * 提取每一行中需要的数据，构造成为一个结果数据对象
             *
             * 当该行中有单元格的数据为空或不合法时，忽略该行的数据
             *
             * @param row 行数据
             * @return 解析后的行数据对象，行数据错误时返回null
             */
            private static Flower convertRowToData (XSSFRow row){
                Flower flowers = new Flower();

                Cell cell;
                int cellNum = 0;

                // get type
                cell = row.getCell(cellNum++);
                String type = cell.getStringCellValue();
                flowers.setType(type);

                // get price
                cell = row.getCell(cellNum++);
                double price = cell.getNumericCellValue();
                flowers.setPrice(price);

                // get color
                cell = row.getCell(cellNum++);
                String color = cell.getStringCellValue();
                flowers.setColor(color);

                // get amount
                cell = row.getCell(cellNum++);
                DecimalFormat df = new DecimalFormat("0");
                Integer amount = Integer.parseInt(df.format(cell.getNumericCellValue()));
                flowers.setAmount(amount);

                return flowers;
            }

            /**
             * 把内容写入Excel文件内容
             * @param flowers 传入要写的内容，此处以一个List内容为例，先把要写的内容放到一个list中
             * @param fileName 传入要写的内容，此处以一个List内容为例，先把要写的内容放到一个list中
             * 把输出流怼到要写入的Excel上，准备往里面写数据
             */
//            public static void writeExcel (List < Flower > flowers, String fileName){
//                // 获取Excel文件
//                FileOutputStream outputStream = null;
//                try {
//                    File excelFile = new File(fileName);
//                    if (!excelFile.exists()) {
//                        logger.warning("指定的Excel文件不存在！");
//                    }
//                    outputStream = new FileOutputStream(fileName,true);
//
//                    // 获取Excel后缀名
//                    XSSFWorkbook workbook = null;
//
//                  //  workbook = new XSSFWorkbook(new FileInputStream(fileName));
//
//
//                    //创建工作表
//                    XSSFSheet xssfSheet = null;
//                    xssfSheet = workbook.getSheet("flowers");
//
//                    //创建行
//                    XSSFRow xssfRow;
//
//                    //创建列，即单元格Cell
//                    XSSFCell xssfCell;
//
//                    //把List里面的数据写到excel中
//                    for (int i = 0; i < flowers.size(); i++) {
//                        //从第一行开始写入
//                        xssfRow = xssfSheet.getRow(i);
//                        //创建每个单元格Cell，即列的数据
//                        Flower cell = flowers.get(i);
//                        xssfCell = xssfRow.getCell(3); //创建单元格
//                        xssfCell.setCellValue(cell.getAmount()); //设置单元格内容
//                    }
//
//                    //用输出流写到excel
//                    // 获取Excel工作簿
//                    workbook.write(outputStream);
//                    outputStream.flush();
//
//                    if (null != workbook) {
//                        workbook.close();
//                    }
//                    if (null != outputStream) {
//                        outputStream.close();
//                    }
//                } catch (FileNotFoundException e) {
//                    logger.warning("Error happens when writing to Excel, failed reason is: " + e.getMessage());
//                } catch (IOException e) {
//                    logger.warning("Error happens when closing Workbook or outputStream, failed reason is: " + e.getMessage());
//                }
//            }
        }

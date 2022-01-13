package com.epam.part3.task2;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ExcelHandler {
    private static Logger logger = Logger.getLogger(ExcelHandler.class.getName()); // 日志打印类

    /**
     * Read both Excel 2003 and 2007 format
     *
     * @param filePath   FileInputStream for the request Excel
     * @return WorkBook return workbook when success else throw exception
     */
    private static Workbook getWorkBook(String filePath,FileInputStream fileInputStream) throws IOException {
        Workbook workbook = null;
        if (filePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook(fileInputStream);
        } else if (filePath.endsWith("xls")) {
            workbook = new HSSFWorkbook(fileInputStream);
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file!");
        }

        return workbook;
    }


    /**
     * 读入excel文件，解析后返回
     *
     * @param filePath
     * @param sheetName
     * @throws IOException
     */
    public static List<Flower> readExcel(String filePath, String sheetName) throws IOException {
        //获得Workbook工作薄对象
        FileInputStream ins = new FileInputStream(filePath);
        Workbook workbook = getWorkBook(filePath, ins);
        //创建返回对象，把每行中的值作为一个数组，所有行作为一个集合返回
        List<Flower> flowers = new ArrayList<>();
        if (workbook != null) {
            for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); sheetNum++) {
                //获得当前sheet工作表
                Sheet sheet = workbook.getSheet(sheetName);
                if (sheet == null) {
                    logger.warning("The specified sheet doesn't exist!");
                }
                //获得当前sheet的开始行
                int firstRowNum = sheet.getFirstRowNum();
                //获得当前sheet的结束行
                int lastRowNum = sheet.getLastRowNum();
                //循环除了第一行的所有行
                Flower flower = new Flower();
                for (int rowNum = firstRowNum + 1; rowNum <= lastRowNum; rowNum++) {
                    //获得当前行
                    Row row = sheet.getRow(rowNum);
                    if (row == null) {
                        continue;
                    }
                    flower = convertRowToData(row);
                    flowers.add(flower);
                }
            }
        }
        workbook.close();
        ins.close();

        return flowers;
    }

    private static Object getCellValue(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();

            case BOOLEAN:
                return cell.getBooleanCellValue();

            case NUMERIC:
                return cell.getNumericCellValue();
        }

        return null;
    }


    /**
     * 提取每一行中需要的数据，构造成为一个结果数据对象
     * <p>
     * 当该行中有单元格的数据为空或不合法时，忽略该行的数据
     *
     * @param row 行数据
     * @return 解析后的行数据对象，行数据错误时返回null
     */
    private static Flower convertRowToData(Row row) {
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
          public static void writeExcel (List <Flower> flowers, String filePath, String sheetName)  {
              // 获取Excel文件
              //获得Workbook工作薄对象

              try {
                  FileInputStream fis = new FileInputStream(filePath);
                  Workbook wb = new XSSFWorkbook(fis);
                  Sheet sheet = wb.getSheet(sheetName);

                  //获得当前sheet的开始行
                  int firstRowNum = sheet.getFirstRowNum();
                  //获得当前sheet的结束行
                  int lastRowNum = sheet.getLastRowNum();
                  //循环除了第一行的所有行

                  for(Flower flower:flowers){
                      for (int rowNum = firstRowNum + 1; rowNum <= lastRowNum; rowNum++) {
                          Row row = sheet.getRow(rowNum);
                          //获得当前行的开始列
                          int firstCellNum = row.getFirstCellNum();
                          //获得当前行的列数
                          int lastCellNum = row.getLastCellNum();
                          if(flower.getType().equalsIgnoreCase(row.getCell(firstCellNum).getStringCellValue())){
                            Cell cell = row.getCell(lastCellNum-1);
                            cell.setCellValue(flower.getAmount());
                          }
                      }
                  }
                  FileOutputStream fos = new FileOutputStream(filePath);
                  wb.write(fos);
                  fos.close();
              } catch (FileNotFoundException e) {
                  logger.warning("Error happens when writing to Excel, failed reason is: " + e.getMessage());
              } catch (InvalidFormatException e) {
                  logger.warning("Invalid file format provided, failed reason is: " + e.getMessage());
              } catch (IOException e) {
                  logger.warning("Error happens when closing Workbook or outputStream, failed reason is: " + e.getMessage());
              }
          }
}
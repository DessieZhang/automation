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
    private static Logger logger = Logger.getLogger(ExcelHandler.class.getName());

    /**
     * Read both Excel 2003 and 2007 format
     *
     * @param filePath FileInputStream for the request Excel
     * @return WorkBook return workbook when success else throw exception
     */
    private static Workbook getWorkBook(String filePath, FileInputStream fileInputStream) throws IOException {
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
     * Read excel sheet from the specified path
     *
     * @param filePath
     * @param sheetName
     * @return flowers data
     */
    public static List<Flower> readExcel(String filePath, String sheetName) {
        //Create return object
        List<Flower> flowers = new ArrayList<>();

        try {
            FileInputStream ins = new FileInputStream(filePath);
            //Get workbook
            Workbook workbook = getWorkBook(filePath, ins);
            if (workbook != null) {
                for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); sheetNum++) {
                    //Get current sheet
                    Sheet sheet = workbook.getSheet(sheetName);
                    if (sheet == null) {
                        logger.warning("The specified sheet doesn't exist!");
                    }
                    //Get the beginning Row num of the sheet
                    int firstRowNum = sheet.getFirstRowNum();

                    //Get the end Row num of the sheet
                    int lastRowNum = sheet.getLastRowNum();

                    //Loop to get the flowers data
                    Flower flower = new Flower();
                    for (int rowNum = firstRowNum + 1; rowNum <= lastRowNum; rowNum++) {
                        //Get current row
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
        } catch (FileNotFoundException e) {
            logger.warning("Error happens when writing to Excel, failed reason is: " + e.getMessage());
        } catch (InvalidFormatException e) {
            logger.warning("Invalid file format provided, failed reason is: " + e.getMessage());
        } catch (IOException e) {
            logger.warning("Error happens when closing Workbook or outputStream, failed reason is: " + e.getMessage());
        }
        return flowers;
    }

    /**
     * Convert the data get from Excel to Flowers format
     * @param row
     * @return Flower
     */
        private static Flower convertRowToData (Row row){
            Flower flowers = new Flower();
            Cell cell;
            int cellNum = 0;

            // Get type
            cell = row.getCell(cellNum++);
            String type = cell.getStringCellValue();
            flowers.setType(type);

            //Get price
            cell = row.getCell(cellNum++);
            double price = cell.getNumericCellValue();
            flowers.setPrice(price);

            //Get color
            cell = row.getCell(cellNum++);
            String color = cell.getStringCellValue();
            flowers.setColor(color);

            //Get amount
            cell = row.getCell(cellNum++);
            DecimalFormat df = new DecimalFormat("0");
            Integer amount = Integer.parseInt(df.format(cell.getNumericCellValue()));
            flowers.setAmount(amount);

            return flowers;
        }

    /**
     * Write the latest flowers data to Excel
     * @param flowers
     * @param filePath
     * @param sheetName
     */
        public static void writeExcel (List < Flower > flowers, String filePath, String sheetName){
            try {
                FileInputStream fis = new FileInputStream(filePath);
                Workbook wb = new XSSFWorkbook(fis);
                Sheet sheet = wb.getSheet(sheetName);

                //Get the beginning row num of the sheet
                int firstRowNum = sheet.getFirstRowNum();

                //Get the last row num of the sheet
                int lastRowNum = sheet.getLastRowNum();


                for (Flower flower : flowers) {
                    //Loop all rows except for the first row to write data
                    for (int rowNum = firstRowNum + 1; rowNum <= lastRowNum; rowNum++) {
                        Row row = sheet.getRow(rowNum);
                        int firstCellNum = row.getFirstCellNum();
                        int lastCellNum = row.getLastCellNum();
                        if (flower.getType().equalsIgnoreCase(row.getCell(firstCellNum).getStringCellValue())) {
                            Cell cell = row.getCell(lastCellNum - 1);
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
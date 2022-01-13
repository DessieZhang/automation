package com.epam.part3.task2;

import com.epam.GlobalVar;

import java.util.List;
import java.util.Scanner;

public class FlowerShop {
    public static List<Flower> flowers = null;
    public static String bouquetType = null;
    public static  final int[] flowersNum = new int[5];
    public static double cost = 0;
    public static  final Scanner keyboard = new Scanner(System.in);
    Bouquet bouquet = new BouquetCalculator();
    public FlowerShop() {
    }

    public String chooseBouquetType() {
        System.out.println("What kind of flower would you like to purchase ? " +
                "Now we have Petunia, Pansy, Rose, Violet and Carnation");
        System.out.println("Bouquet Sizes are as following: ");
        System.out.println("tiny is a maximum of " + GlobalVar.MaxTiny + " flowers, it's base price is: " + GlobalVar.BasePriceTiny);
        System.out.println("medium is " + GlobalVar.MaxTiny + " to " + GlobalVar.MaxMedium + " flowers, it's base price is: " + GlobalVar.BasePriceMedium);
        System.out.println("big is " + GlobalVar.MaxMedium + " to " + GlobalVar.MaxBig + " flowers, it's base price is: " + GlobalVar.BasePriceBig);
        System.out.println("Please choose a bouquet size (tiny, medium or big): ");
        bouquetType = keyboard.next();
        return bouquetType;
    }

    public List<Flower> getFlowersFromExcel() throws Exception {
        String filePath = System.getProperty("user.dir") + "//src//test//resources//FlowerShop.xlsx";
        //Read Excel file
        flowers = ExcelHandler.readExcel(filePath,"flowers");
        return flowers;
    }

    public boolean validateOrderType(String type) throws Exception {
        flowers = getFlowersFromExcel();
        while (!bouquet.validateBouquetType(type)) {
            System.out.println("I don't know this size, please choose a bouquet size (tiny, medium or big): ");
            type = keyboard.next();
        }

        while (!bouquet.validateBouquet(type, flowersNum)) {
            for (int i = 0; i < flowersNum.length; i++) {
                System.out.println("Enter the number of " + flowers.get(i).getType() + "@ $" + flowers.get(i).getPrice() + " each: ");
                flowersNum[i] = keyboard.nextInt();
                flowersNum[i] = flowers.get(i).checkInStock(flowersNum[i]);
            }
            return true;
        }
        return true;
    }

    public void calculateBouquetCost() {
        cost = bouquet.calculateBouquetCost(bouquetType, flowers, flowersNum);
        if (cost != -1) {
            System.out.println("The cost of the bouquet with delivery is: " + cost);
            for (int i = 0; i < flowers.size(); i++) {
                flowers.get(i).setAmount(flowers.get(i).getAmount() - flowersNum[i]);
            }
        }
    }

    public void updateFlowersStockExcel() {
        String filePath = System.getProperty("user.dir") + "//src//test//resources//FlowerShop.xlsx";
        ExcelHandler.writeExcel(flowers,filePath,"flowers");

        //
    }

    public static void main(String[] args) throws Exception {
        //From DataBase using JDBC driver
        System.out.println("====================FlowerShop DataSource from Excel=======================");
        String bouquetType = null;
        FlowerShop flowerShopExcel = new FlowerShop();
        bouquetType = flowerShopExcel.chooseBouquetType();
        flowerShopExcel.validateOrderType(bouquetType);
        flowerShopExcel.calculateBouquetCost();
        flowerShopExcel.updateFlowersStockExcel();
    }
}









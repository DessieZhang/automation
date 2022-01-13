package com.epam.part4.task1;

import com.epam.db.exception.DatabaseException;
import com.epam.GlobalVar;
import com.epam.part4.task1.DB.FlowerShopDBClient;
import com.epam.part4.task1.Json.JsonHandler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class FlowerShop {
    private static Logger logger = Logger.getLogger(FlowerShop.class.getName());
    Bouquet bouquet = new BouquetCalculator();
    private static List<Flower> flowers = null;
    String bouquetType = null;
    int[] flowersNum = new int[5];
    double cost = 0;
    Scanner keyboard = new Scanner(System.in);

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

    public static List<Flower> getFlowersFromDB() throws DatabaseException {
        FlowerShopDBClient client = new FlowerShopDBClient();
        flowers = client.getFlowers();
        return flowers;
    }

    public static List<Flower> getFlowerFromJson() throws FileNotFoundException {
        JsonHandler jsonHandler = new JsonHandler();
        flowers = jsonHandler.getFlowersData();
        return flowers;
    }

    public boolean validateOrderType(String dataFrom, String type) throws FileNotFoundException, DatabaseException {
        if (dataFrom.equalsIgnoreCase("DB")) {
            flowers = getFlowersFromDB();
        } else if (dataFrom.equalsIgnoreCase("Json")) {
            flowers = getFlowerFromJson();
        } else
            System.out.println("I don't know this data source.");

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

    public void updateFlowersStockDB() throws DatabaseException {
        FlowerShopDBClient client = new FlowerShopDBClient();
        client.updateFlowersTable(flowers);
    }

    public void updateFlowersStockJson() throws IOException {
        JsonHandler jsonHandler = new JsonHandler();
        jsonHandler.updateFlowersData(flowers);
    }

    public static void main(String[] args) throws Exception {
        //From DataBase using JDBC driver
        logger.warning("====================From DataBase using JDBC driver=======================");
        String size = null;
        FlowerShop flowerShopDB = new FlowerShop();
        size = flowerShopDB.chooseBouquetType();
        flowerShopDB.validateOrderType("DB", size);
        flowerShopDB.calculateBouquetCost();
        flowerShopDB.updateFlowersStockDB();

        //From Json file
        logger.warning("====================From Json file=======================");
        FlowerShop flowerShopJson = new FlowerShop();
        size = flowerShopJson.chooseBouquetType();
        flowerShopJson.validateOrderType("Json", size);
        flowerShopJson.calculateBouquetCost();
        flowerShopJson.updateFlowersStockJson();
    }
}









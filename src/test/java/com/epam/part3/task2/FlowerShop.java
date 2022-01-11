package com.epam.part3.task2;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FlowerShop {

    public static void main(String[] args) throws Exception {

        //Create an object of File class to open xls file
        //String file = "D:\\2021Test automation mentoring program in Java\\automation\\src\\test\\resources\\FlowerShop.xlsx";
        //String file = "/src/test/resources/FlowerShop.xlsx";
        String file = "//src//test//resources//FlowerShop.xlsx";
        String filePath = System.getProperty("user.dir") + file;
        //Read Excel file
        List<Flower> flowers = ExcelUtils.readExcel(filePath,"flowers");

        double cost = 0;
        Scanner keyboard = new Scanner(System.in);
        System.out.println("What kind of flower would you like to purchase ? " +
                "Now we have Petunia, Pansy, Rose, Violet and Carnation");
        BouquetCalculator box = new BouquetCalculator();
        System.out.println("Bouquet Sizes: ");
        System.out.println("\"tiny\" is a maximum of " + BouquetCalculator.maxTiny +
                " flowers, \n\"medium\" is a maximum of " + BouquetCalculator.maxMedium +
                " flowers, \n\"big\" is a maximum of " + BouquetCalculator.maxBig + " flowers");
        System.out.println("Please choose a bouquet size (tiny, medium or big): ");
        String size = keyboard.next();
        while (!BouquetCalculator.validateBouquetSize(size)) {
            System.out.println("I don't know this size, please choose a bouquet size (tiny, medium or big): ");
            size = keyboard.next();
        }

        int[] flowersNum = new int[5];
        int inCode = 0;
        while (!BouquetCalculator.validateBouquet(size, flowersNum)) {
            if (inCode != 0) {
                System.out.println("You have chosen too many flowers for the bouquet size requested. Please re-enter the number of each flowers:");
            }
            System.out.println("Enter the number of Rose@ $" + flowers.get(0).getPrice() + " each: ");
            flowersNum[0] = keyboard.nextInt();
            flowersNum[0] = flowers.get(0).checkInStock(flowersNum[0]);
            System.out.println("Enter the number of Pansy@ $" + flowers.get(1).getPrice() + " each: ");
            flowersNum[1] = keyboard.nextInt();
            flowersNum[1] = flowers.get(1).checkInStock(flowersNum[1]);
            System.out.println("Enter the number of Petunia@ $" + flowers.get(2).getPrice() + " each: ");
            flowersNum[2] = keyboard.nextInt();
            flowersNum[2] = flowers.get(2).checkInStock(flowersNum[2]);
            System.out.println("Enter the number of Violet@ $" + flowers.get(3).getPrice() + " each:");
            flowersNum[3] = keyboard.nextInt();
            flowersNum[3] = flowers.get(3).checkInStock(flowersNum[3]);
            System.out.println("Enter the number of Carnation@ $" + flowers.get(4).getPrice() + " each:  ");
            flowersNum[4] = keyboard.nextInt();
            flowersNum[4] = flowers.get(4).checkInStock(flowersNum[4]);
            inCode += 1;
        }
        System.out.println("The cost of the bouquet with delivery is: " + BouquetCalculator.calculateCost(size, flowers, flowersNum));

        //Update each flower amount for each flower
        List<Flower>  updatedFlowers = new ArrayList<>(5);
        Flower rose = new Rose();
        rose.setAmount(rose.getAmount()-flowersNum[0]);
        Flower pansy = new Pansy();
        pansy.setAmount(pansy.getAmount()-flowersNum[1]);
        Flower petunia = new Petunia();
        petunia.setAmount(petunia.getAmount()-flowersNum[2]);
        Flower violet = new Violet();
        violet.setAmount(violet.getAmount()-flowersNum[3]);
        Flower carnation = new Carnation();
        carnation.setAmount(carnation.getAmount()-flowersNum[4]);
        updatedFlowers.add(rose);
        updatedFlowers.add(pansy);
        updatedFlowers.add(petunia);
        updatedFlowers.add(violet);
        updatedFlowers.add(carnation);

        //ExcelUtils.writeExcel(updatedFlowers,file);
    }
    }









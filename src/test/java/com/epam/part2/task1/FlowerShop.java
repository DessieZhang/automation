package com.epam.part2.task1;

import com.epam.GlobalVar;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FlowerShop {
    Bouquet bouquet = new BouquetCalculator();
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

    public boolean validateOrderType(String bouquetType, List<Flower> flowers) {
        while (!bouquet.validateBouquetType(bouquetType)) {
            System.out.println("I don't know this size, please choose a bouquet size (tiny, medium or big): ");
            bouquetType = keyboard.next();
        }

        while (!bouquet.validateBouquet(bouquetType, flowersNum)) {
            for (int i = 0; i < flowersNum.length; i++) {
                System.out.println("Enter the number of " + flowers.get(i).getType() + "@ $" + flowers.get(i).getPrice() + " each: ");
                flowersNum[i] = keyboard.nextInt();
                flowersNum[i] = flowers.get(i).checkInStock(flowersNum[i]);
            }
            return true;
        }
        return true;
    }

    public void calculateBouquetCost(List<Flower> flowers) {
        cost = bouquet.calculateBouquetCost(bouquetType, flowers, flowersNum);
        if (cost != -1) {
            System.out.println("The cost of the bouquet with delivery is: " + cost);
            }
        }

    public static void main(String[] args) throws Exception {
        System.out.println("====================Flower Shop=======================");
        List<Flower> flowers = new ArrayList<>();
        flowers.add(0,new Flower("Rose",0.5,"Red",15));
        flowers.add(1,new Flower("Pansy",2.5,"Purple",30));
        flowers.add(2,new Flower("Petunia",3.1,"Pink",40));
        flowers.add(3,new Flower("Violet",6.6,"Pulse",10));
        flowers.add(4,new Flower("Carnation",1.8,"Yellow",60));

        FlowerShop flowerShop = new FlowerShop();
        String bouquetType = flowerShop.chooseBouquetType();
        flowerShop.validateOrderType(bouquetType,flowers);
        flowerShop.calculateBouquetCost(flowers);
    }
}









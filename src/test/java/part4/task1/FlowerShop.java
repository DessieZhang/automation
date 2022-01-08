package part4.task1;

import part4.task1.DB.FlowerShopDBClient;
import part4.task1.Json.JsonDataReader;

import java.util.List;
import java.util.Scanner;

public class FlowerShop {

    public static void main(String[] args) throws Exception {

        JsonDataReader jsReader = new JsonDataReader();
        List<Flower> flowers = jsReader.getFlowersData();


        //FlowerShopDBClient client = new FlowerShopDBClient();
        //List<Flower> flowers = client.getFlowers();

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


        for(int i = 0;i<flowers.size();i++){
            flowers.get(i).setAmount(flowers.get(i).getAmount()-flowersNum[i]);
        }
        //client.updateFlowersTable(flowers);
    }
    }









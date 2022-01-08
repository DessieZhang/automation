package part4.task1;

import java.util.List;

public class BouquetCalculator {

    public static final int maxTiny = 5, maxMedium = 10, maxBig = 20;
    public static final double basePriceTiny = 10;
    public static final double basePriceMedium = 15;
    public static final double basePriceBig = 20;

    public static int flowersSum(int[] flowers){
        int sum = 0;
        for(int i : flowers){
            sum += i;
        }
        return sum;
    }

    public static boolean validateBouquet(String size, int[] flowers)
    {
        // check for valid number of flowers for tiny bouquet
        if(size.equals("tiny"))
        {
            if(flowersSum(flowers) >0 & flowersSum(flowers) <= maxTiny)
                return true;
            else return false;
        }
        // check for valid number of flowers for medium bouquet
        else if(size.equals("medium"))
        {
            if(flowersSum(flowers) >0 & flowersSum(flowers) <= maxMedium)
                return true;
            else return false;
        }
        // check for valid number of flowers for big bouquet
        else if(size.equals("big"))
        {
            if(flowersSum(flowers) >0 & flowersSum(flowers) <= maxBig)
                return true;
            else return false;
        }
        else return false;
    }

    public static boolean validateBouquetSize(String size)
    {
        if((size.equals("tiny"))||(size.equals("medium"))||(size.equals("big")))
          return true;
        else
          return false;
    }

    public static double calculateCost(String size, List<Flower> flowers, int[] orderAmount)
    {
        double cost = 0;
        if(validateBouquet(size,orderAmount))
        {
            switch (size.toLowerCase().trim()) {
                case "tiny":
                    cost = basePriceTiny + orderAmount[0] * flowers.get(0).getPrice() + orderAmount[1] * flowers.get(1).getPrice() + orderAmount[2] * flowers.get(2).getPrice() +
                            orderAmount[3] * flowers.get(3).getPrice() + orderAmount[4]* flowers.get(4).getPrice() ;
                    break;
                case "medium":
                    cost = basePriceMedium + orderAmount[0] * flowers.get(0).getPrice() + orderAmount[1] * flowers.get(1).getPrice() + orderAmount[2] * flowers.get(2).getPrice() +
                            orderAmount[3] * flowers.get(3).getPrice() + orderAmount[4]* flowers.get(4).getPrice() ;
                    break;
                case "big":
                    cost = basePriceBig + orderAmount[0] * flowers.get(0).getPrice() + orderAmount[1] * flowers.get(1).getPrice() + orderAmount[2] * flowers.get(2).getPrice() +
                            orderAmount[3] * flowers.get(3).getPrice() + orderAmount[4]* flowers.get(4).getPrice() ;
                    break;
                default:
                    cost = 0;
            }
            return cost;
        }
        else return -1;
    }
}

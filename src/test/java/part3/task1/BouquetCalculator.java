package part3.task1;

import java.util.HashMap;

public class BouquetCalculator {

    public static final int maxTiny = 5, maxMedium = 10, maxBig = 20;
    public static final double basePrice = 20;

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

    public static double calculateCost(String size,HashMap<String, Object> flowers,int[] orderAmount)
    {
        Rose rose = (Rose) flowers.get("Rose");
        Pansy pansy = (Pansy) flowers.get("Pansy");
        Petunia petunia = (Petunia) flowers.get("Petunia");
        Violet violet = (Violet) flowers.get("Violet");
        Carnation carnation = (Carnation) flowers.get("Carnation");

        if(validateBouquet(size,orderAmount))
        {
            double cost = basePrice + orderAmount[0] * rose.getPrice() + orderAmount[1] * pansy.getPrice() + orderAmount[2] * violet.getPrice() +
                    orderAmount[3] * petunia.getPrice() + orderAmount[4]* carnation.getPrice() ;
            return cost;
        }
        else return -1;
    }
}

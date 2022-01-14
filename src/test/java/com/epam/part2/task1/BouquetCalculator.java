package com.epam.part2.task1;

import java.util.List;
import com.epam.GlobalVar;

public class BouquetCalculator implements Bouquet {

    /**
     * Get the sum of order flowers
     * @param flowers
     * @return the sum amount of ordered flowers
     */
    private static int flowersSum(int[] flowers) {
        int sum = 0;
        for (int i : flowers) {
            sum += i;
        }
        return sum;
    }

    /**
     * Check if entered order bouquet is valid
     * @param type
     * @return true for valid,false for invalid
     */
    public boolean validateBouquetType(String type) {
        if ((type.equalsIgnoreCase("tiny")) || (type.equalsIgnoreCase("medium")) || (type.equalsIgnoreCase("big")))
            return true;
        else {
            return false;
        }
    }

    /**
     * Validate if the number of flowers chosen match the bouquet size requested
     * @param type
     * @param flowers
     * @return true for match, false for mismatch
     */
    public boolean validateBouquet(String type, int[] flowers) {
        if (type.equalsIgnoreCase("tiny")) {
            if (flowersSum(flowers) > 0 & flowersSum(flowers) <= GlobalVar.MaxTiny)
                return true;
            else return false;
        }
        else if (type.equalsIgnoreCase("medium")) {
            if (flowersSum(flowers) > GlobalVar.MaxTiny & flowersSum(flowers) <= GlobalVar.MaxMedium)
                return true;
            else return false;
        }
        else if (type.equalsIgnoreCase("big")) {
            if (flowersSum(flowers) > GlobalVar.MaxMedium & flowersSum(flowers) <= GlobalVar.MaxBig)
                return true;
            else return false;
        } else return false;
    }

    /**
     * Calculate the Bouquet Cost according to the selected Bouquet type
     * @param bouquetType
     * @param flowers
     * @param orderAmount
     * @return Bouquet cost
     */
    public double calculateBouquetCost(String bouquetType, List<Flower> flowers, int[] orderAmount) {
        double cost = 0;
        double flowerCost = 0;
        if (validateBouquet(bouquetType, orderAmount)) {
            for (int i = 0; i < flowers.size(); i++) {
                flowerCost += orderAmount[i] * flowers.get(i).getPrice();
            }
            switch (bouquetType.toLowerCase().trim()) {
                case "tiny":
                    cost = GlobalVar.BasePriceTiny + flowerCost;
                    break;
                case "medium":
                    cost = GlobalVar.BasePriceMedium + flowerCost;
                    break;
                case "big":
                    cost = GlobalVar.BasePriceBig + flowerCost;
                    break;
                default:
                    cost = 0;
            }
            return cost;
        } else
            System.out.println("The number of flowers you chosen does not match the bouquet size requested. Please re-enter the number of each flowers:");
        return -1;
    }
}

package com.epam.part2.task1;

import com.epam.PropertiesConfiguration;

import java.util.List;

interface Bouquet {
    public PropertiesConfiguration conf = null;

    public boolean validateBouquetType(String type);

    public boolean validateBouquet(String type, int[] flowers);

    public double calculateBouquetCost(String bouquetType, List<Flower> flowers, int[] orderAmount);

}

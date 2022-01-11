package com.epam.part4.task1;

import com.epam.PropertiesConfiguration;
import com.epam.part4.task1.Flower;

import java.util.List;

interface Bouquet {
    public PropertiesConfiguration conf = null;

    public boolean validateBouquetType(String type);

    public boolean validateBouquet(String type, int[] flowers);

    public double calculateBouquetCost(String bouquetType, List<Flower> flowers, int[] orderAmount);

}

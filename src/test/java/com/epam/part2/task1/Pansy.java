package com.epam.part2.task1;

public class Pansy extends Flower {
    public Pansy(String type, double price, String color, int amount){
        super(type,price,color,amount);
    }

    @Override
    public String toString() {
        return "Pansy";
    }

    @Override
    public int checkInStock(int orderAmount) {
        int actualBought = 0;
        if (super.getAmount() == 0) {
            System.out.println("Pansies are out of stock!");
        } else if (orderAmount <= super.getAmount()) {
            actualBought = orderAmount;
        } else if (orderAmount > super.getAmount()) {
            actualBought = super.getAmount();
            System.out.println("There are only " + super.getAmount() + " pansies in stock.");
        }
        return actualBought;
    }
}
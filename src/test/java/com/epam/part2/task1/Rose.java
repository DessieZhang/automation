package com.epam.part2.task1;

public class Rose extends Flower {

    public Rose(String type, double price, String color, int amount){
        super(type,price,color,amount);
    }

    @Override
    public String toString() {
        return "Rose";
    }

    @Override
    public int checkInStock(int amount) {
        int actualBought = 0;
        if (super.getAmount() == 0) {
            System.out.println("Roses are out of stock!");
        } else if (amount <= super.getAmount()) {
            actualBought = amount;
        } else if (amount > super.getAmount()) {
            actualBought = amount;
            System.out.println("There are only " + super.getAmount() + " roses in stock.");
        }
        return actualBought;
    }
}

package com.epam.part3.task2;

public class Petunia extends Flower {
    public Petunia() {
        super();
    }

    @Override
    public String toString() {
        return "Petunia";
    }

    @Override
    public int checkInStock(int orderAmount) {
        int actualBought = 0;
        if (super.getAmount() == 0) {
            System.out.println("Petunias are out of stock!");
        } else if (orderAmount <= super.getAmount()) {
            actualBought = orderAmount;
        } else if (orderAmount > super.getAmount()) {
            actualBought = super.getAmount();
            System.out.println("There are only " + super.getAmount() + " petunias in stock.");
        }
        return actualBought;
    }
}

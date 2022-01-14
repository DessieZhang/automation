package com.epam.part4.task1;

public class Carnation extends Flower {
    public Carnation() {
        super();
    }

    @Override
    public String toString() {
        return "Carnation";
    }

    @Override
    public int checkInStock(int orderAmount) {
        int actualBought = 0;
        if (super.getAmount() == 0) {
            System.out.println("Carnations are out of stock!");
        } else if (orderAmount <= super.getAmount()) {
            actualBought = orderAmount;
        } else if (orderAmount > super.getAmount()) {
            actualBought = super.getAmount();
            System.out.println("There are only " + super.getAmount() + " carnations in stock.");
        }
        return actualBought;
    }
}

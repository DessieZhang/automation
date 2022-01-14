package com.epam.part3.task2;

public class Pansy extends Flower {
    public Pansy() {
        super();
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

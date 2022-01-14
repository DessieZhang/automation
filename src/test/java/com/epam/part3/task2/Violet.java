package com.epam.part3.task2;

public class Violet extends Flower {
    public Violet() {
        super();
    }

    @Override
    public String toString() {
        return "Violet";
    }

    @Override
    public int checkInStock(int orderAmount) {
        int actualBought = 0;
        if (super.getAmount() == 0) {
            System.out.println("Violets are out of stock!");
        } else if (orderAmount <= super.getAmount()) {
            actualBought = orderAmount;
        } else if (orderAmount > super.getAmount()) {
            actualBought = super.getAmount();
            System.out.println("There are only " + super.getAmount() + " violets in stock.");
        }
        return actualBought;
    }
}

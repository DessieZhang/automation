package com.epam.part3.task2;

public class Rose extends Flower {

    public Rose() {
        super();
    }

    @Override
    public String toString() {
        return "Rose";
    }

    @Override
    public int checkInStock(int orderAmount) {
        int actualBought = 0;
        if (super.getAmount() == 0) {
            System.out.println("Roses are out of stock!");
        } else if (orderAmount <= super.getAmount()) {
            actualBought = orderAmount;
        } else if (orderAmount > super.getAmount()) {
            actualBought = super.getAmount();
            System.out.println("There are only " + super.getAmount() + " roses in stock.");
        }
        return actualBought;
    }
}

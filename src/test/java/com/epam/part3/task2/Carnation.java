package com.epam.part3.task2;

public class Carnation extends Flower {
    public Carnation() {
        super();
    }

    @Override
    public String toString() {
        return "Carnation";
    }

    @Override
    public int checkInStock(int amount) {
        int actualBought = 0;
        if (super.getAmount() == 0) {
            System.out.println("Carnations are out of stock!");
        } else if (amount <= super.getAmount()) {
            actualBought = amount;
        } else if (amount > super.getAmount()) {
            actualBought = amount;
            System.out.println("There are only " + super.getAmount() + " carnations in stock.");
        }
        return actualBought;
    }
}

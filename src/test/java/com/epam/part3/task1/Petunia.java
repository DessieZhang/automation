package com.epam.part3.task1;

public class Petunia extends Flower {
    public Petunia(String type, double price, String color, int amount) {
        super(type, price, color, amount);
    }

    @Override
    public String toString() {
        return "Petunia";
    }

    @Override
    public int checkInStock(int amount) {
        int actualBought = 0;
        if (super.getAmount() == 0) {
            System.out.println("Petunias are out of stock!");
        } else if (amount <= super.getAmount()) {
            actualBought = amount;
        } else if (amount > super.getAmount()) {
            actualBought = amount;
            System.out.println("There are only " + super.getAmount() + " petunias in stock.");
        }
        return actualBought;
    }
}


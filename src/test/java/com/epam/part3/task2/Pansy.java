package com.epam.part3.task2;

public class Pansy extends Flower {
    public Pansy(){
        super();
    }

    @Override
    public String toString() {
        return "Pansy";
    }

    @Override
    public int checkInStock(int amount){
        int actualBought = 0;
        if (super.getAmount() == 0) {
            System.out.println("Pansys are out of stock!");
        } else if (amount <= super.getAmount()) {
            actualBought = amount;
        } else if (amount > super.getAmount()) {
            actualBought = amount;
            System.out.println("There are only " + super.getAmount() + " pansies in stock.");
        }
        return actualBought;
    }
}

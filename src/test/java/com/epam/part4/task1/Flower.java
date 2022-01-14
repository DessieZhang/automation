package com.epam.part4.task1;

public class Flower {
    public String type;
    public double price;
    public String color;
    public int amount;

    public Flower() {
        super();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int checkInStock(int orderAmount) {
        int actualBought = 0;
        if (this.getAmount() == 0) {
            System.out.println("Roses are out of stock!");
        } else if (orderAmount <= this.getAmount()) {
            actualBought = orderAmount;
        } else if (orderAmount > this.getAmount()) {
            actualBought = this.getAmount();
            System.out.println("There are only " + this.getAmount() + " roses in stock.");
        }
        return actualBought;
    }
}


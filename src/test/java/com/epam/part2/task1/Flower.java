package com.epam.part2.task1;

public class Flower {
    private String type;
    private double price;
    private String color;
    private int amount;

    public Flower(String type,double price,String color, int amount){
        super();
        this.type = type;
        this.price = price;
        this.color = color;
        this.amount = amount;
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

package com.epam.part3.task1;

public class Flower {
    private String type;
    private double price;
    private String color;
    private int amount;

    public Flower(String type, double price, String color, int amount) {
        super();
        try{
        this.type = type;
        this.price = price;
        this.color = color;
        this.amount = amount;
        //----------------Built-in Exception 5---------
        }catch (SecurityException  e){
            e.printStackTrace();
            //----------------Built-in Exception 6---------
        } catch (NumberFormatException e){
            e.printStackTrace();
        }
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
        try{
        if (this.getAmount() < 0) {
            throw new InsufficientAmountException();
        } else if (this.getAmount() == 0) {
                System.out.println("Roses are out of stock!");
        } else if (this.getAmount() > 0 & orderAmount <= this.getAmount()) {
            actualBought = orderAmount;
        } else if (this.getAmount() > 0 & orderAmount > this.getAmount()) {
            actualBought = this.getAmount();
            System.out.println("There are only " + this.getAmount() + " roses in stock.");
            //----------Custom Exception 3--------------
        }}catch (InsufficientAmountException e){
            e.printStackTrace();
        }
        return actualBought;
    }
    public class InsufficientAmountException extends Exception {
        public InsufficientAmountException() {
            System.out.println("Flower Number doesn't Bouquet type!");
        }
    }
}
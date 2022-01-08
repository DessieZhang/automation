package part2.task1;

public class Flower {
    private double price;
    private String color;
    private int amount;

    public Flower(double price,String color, int amount){
        super();
        this.price = price;
        this.color = color;
        this.amount = amount;
    }

    public double getPrice(){
        return price;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public String getColor(){
        return color;
    }

    public void setColor(String color){this.color = color;
    }

    public int getAmount(){
        return amount;
    }

    public void setAmount(int amount){
        this.amount = amount;
    }

    public int checkInStock(int amount){
        int actualBought=0;
        if(this.amount == 0){
            System.out.println("Roses are out of stock!");
        } else if(amount <= this.amount){
            actualBought = amount;
        } else if(amount > this.amount) {
            actualBought = amount;
            System.out.println("There are only " + this.amount + " roses in stock.");
        }
        return actualBought;
    }
}

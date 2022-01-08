package part3.task2;

public class Flower {
    private String type;
    private double price;
    private String color;
    private int amount;

    public Flower(){
        super();
    }

    public String getType(){return type;}

    public  void setType(String type){this.type = type;}

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

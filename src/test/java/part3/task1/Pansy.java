package part3.task1;

public class Pansy extends Flower {
    public Pansy(double price, String color, int amount){
        super(price,color,amount);
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

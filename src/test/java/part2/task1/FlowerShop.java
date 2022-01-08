package part2.task1;

import java.util.HashMap;
import java.util.Scanner;

public class FlowerShop {
    private Manager[] managers = new Manager[2];
    private Flower[] flowers = new Flower[5];

    public Manager[] getManagers(){
        return managers;
    }

    public void setManagers(Manager[] managers){
        this.managers = managers;
    }

    public Flower[] getFlowers() {
        return flowers;
    }

    public void setFlowers(Flower[] flowers) {
        this.flowers = flowers;
    }

   @Override
   public String toString() {
        String toReturn = "";
        for (int i = 0; i < managers.length; i++) {
            toReturn = toReturn + managers[i].toString();
            toReturn = toReturn + ",";
        }
        toReturn = toReturn + "\n";
        for(int i = 0; i < flowers.length; i++) {
            toReturn = toReturn + flowers[i].toString();
            toReturn = toReturn + ",";
        }
        return  toReturn;
   }

   public static void main(String[] args) {
        double cost= 0;
       HashMap<String, Object> flowers = new HashMap<String,Object>();
       flowers.put("Rose",new Rose(0.5,"Red",5));
       flowers.put("Pansy",new Pansy(2.5,"Purple",30));
       flowers.put("Petunia",new Petunia(3.1,"Pink",40));
       flowers.put("Violet",new Violet(6.6,"Pulse",10));
       flowers.put("Carnation",new Carnation(1.8,"Yellow",60));

       Scanner keyboard = new Scanner(System.in);
       System.out.println("What kind of flower would you like to purchase ? " +
                           "Now we have Petunia, Pansy, Rose, Violet and Carnation");
       BouquetCalculator box = new BouquetCalculator();
       System.out.println("Bouquet Sizes: ");
       System.out.println("\"tiny\" is a maximum of "+BouquetCalculator.maxTiny +
               " flowers, \n\"medium\" is a maximum of "+BouquetCalculator.maxMedium +
               " flowers, \n\"big\" is a maximum of "+ BouquetCalculator.maxBig + " flowers");
       System.out.println("Please choose a bouquet size (tiny, medium or big): ");
       String size = keyboard.next();
       while (!BouquetCalculator.validateBouquetSize(size))
       {
           System.out.println("I don't know this size, please choose a bouquet size (tiny, medium or big): ");
           size = keyboard.next();
       }


       Rose rose = (Rose) flowers.get("Rose");
       Pansy pansy = (Pansy) flowers.get("Pansy");
       Petunia petunia = (Petunia) flowers.get("Petunia");
       Violet violet = (Violet) flowers.get("Violet");
       Carnation carnation = (Carnation) flowers.get("Carnation");

       int [] flowersNum = new int[5];
       int inCode = 0;
       while (!BouquetCalculator.validateBouquet(size,flowersNum))
       {
           if (inCode != 0 ){
               System.out.println("You have chosen too many flowers for the bouquet size requested. Please re-enter the number of each flowers:");
           }
           System.out.println("Enter the number of Rose@ $"+rose.getPrice()+" each: ");
           flowersNum[0] = keyboard.nextInt();
           flowersNum[0] = rose.checkInStock(flowersNum[0]);

           System.out.println("Enter the number of Pansy@ $"+pansy.getPrice()+" each: ");
           flowersNum[1] = keyboard.nextInt();
           flowersNum[1] = pansy.checkInStock(flowersNum[1]);

           System.out.println("Enter the number of Petunia@ $"+petunia.getPrice()+" each: ");
           flowersNum[2] = keyboard.nextInt();
           flowersNum[2] = petunia.checkInStock(flowersNum[2]);

           System.out.println("Enter the number of Violet@ $"+violet.getPrice()+" each:");
           flowersNum[3]=keyboard.nextInt();
           flowersNum[3]= violet.checkInStock(flowersNum[3]);

           System.out.println("Enter the number of Carnation@ $"+carnation.getPrice()+" each:  ");
           flowersNum[4]=keyboard.nextInt();
           flowersNum[4]= carnation.checkInStock(flowersNum[4]);

           inCode +=1;
       }
       System.out.println("The cost of the bouquet with delivery is: " + BouquetCalculator.calculateCost(size,flowers,flowersNum));
   }
}

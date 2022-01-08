package part2.task2;

import java.text.DecimalFormat;
import java.util.*;

public class HashSetvsTreeSet {
    private static int numbers = 0;
    private static DecimalFormat df = new DecimalFormat("#.#####");
    private static double hashSetTime, treeSetTime;
    private static HashSet<Integer> hashSet = new HashSet<Integer>();
    private static TreeSet<Integer> treeSet = new TreeSet<Integer>();
    public static void start() {
        System.out.print("Please enter a number(It is recommended bigger than 10000): ");
        Scanner scan = new Scanner(System.in);
        try {
            numbers = scan.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Please enter a number");
            System.exit(1);
        }
    }

    public static void hashSetTest(String method){
        double start = System.currentTimeMillis() * 0.001;

        for (int i = 0; i < numbers; i++) {
            switch (method.toLowerCase()){
                case "add":
                    hashSet.add(i);
                    break;
                case "search":
                    hashSet.contains(i);
                    break;
                case "delete":
                    hashSet.clear();
                    break;
                default:
                    System.out.println("No such method");
            }
        }
        hashSetTime = (System.currentTimeMillis() * 0.001) - start;
        System.out.println("HashMap takes " + df.format(hashSetTime)  + "s to " + method +" "  + numbers + " elements");
    }

    public static void treeSetTest(String method) {
        double start = System.currentTimeMillis() * 0.001;

        for (int i = 0; i < numbers; i++) {
            switch (method.toLowerCase()) {
                case "add":
                    treeSet.add(i);
                    break;
                case "search":
                    treeSet.contains(i);
                    break;
                case "delete":
                    treeSet.clear();
                    break;
                default:
                    System.out.println("No such method");
            }
        }
        treeSetTime = (System.currentTimeMillis() * 0.001) - start;
        System.out.println("TreeMap takes " + df.format(treeSetTime) + "s to " + method + " " + numbers + " elements");
    }

    public static void difference(){
        double differenceSec = 0;
        double differenceTimes = 0;

        if(hashSetTime < treeSetTime){
            differenceSec = (treeSetTime - hashSetTime);
            differenceTimes = (treeSetTime / hashSetTime);
            System.out.println("HashSet takes " + df.format(differenceSec) + "s less than TreeSet, it is " + df.format(differenceTimes) + " times faster\n");
        }else{
            differenceSec = (hashSetTime - treeSetTime);
            differenceTimes = (hashSetTime / treeSetTime);
            System.out.println("TreeSet takes " + df.format(differenceSec) + "s less than HashSet, it is " + df.format(differenceTimes) + " times faster\n");
        }
    }

    public static void main(String[] args){
        start();
        hashSetTest("add");
        treeSetTest("add");
        difference();

        hashSetTest("search");
        treeSetTest("search");
        difference();

        hashSetTest("delete");
        treeSetTest("delete");
        difference();
    }
}

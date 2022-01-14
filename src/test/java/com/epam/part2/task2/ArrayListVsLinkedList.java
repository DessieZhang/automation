package com.epam.part2.task2;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class ArrayListVsLinkedList {
    private static int numbers = 0;
    private static DecimalFormat df = new DecimalFormat("#.#####");
    private static double arrayListTime, linkedListTime;
    private static ArrayList<Integer> arrayList = new ArrayList<>();
    private static LinkedList<Integer> linkedList = new LinkedList<>();

    /**
     *Enter a number to start the compare
     */
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

    /**
     * Performance monitor for LinkedList
     * @param method add,search,delete
     */
    public static void linkedListTest(String method){
        double start = System.currentTimeMillis() * 0.001;

        for (int i = 0; i < numbers; i++) {
            switch (method.toLowerCase()){
                case "add":
                    linkedList.add(i);
                    break;
                case "search":
                    linkedList.get(i);
                    break;
                case "delete":
                    linkedList.removeAll(linkedList);
                    break;
                default:
                    System.out.println("No such method");
            }
        }
        linkedListTime = (System.currentTimeMillis() * 0.001) - start;
        System.out.println("LinkedList takes " + df.format(linkedListTime)  + "s to " + method +" "  + numbers + " elements");
    }

    /**
     * Performance monitor for LinkedList
     * @param method add,search,delete
     */
    public static void arrayListTest(String method) {
        double start = System.currentTimeMillis() * 0.001;

        for (int i = 0; i < numbers; i++) {
            switch (method.toLowerCase()) {
                case "add":
                    arrayList.add(i);
                    break;
                case "search":
                    arrayList.get(i);
                    break;
                case "delete":
                    arrayList.removeAll(arrayList);
                    break;
                default:
                    System.out.println("No such method");
            }
        }
        arrayListTime = (System.currentTimeMillis() * 0.001) - start;
        System.out.println("ArrayList takes " + df.format(arrayListTime) + "s to " + method + " " + numbers + " elements");
    }

    /**
     * Get the performance difference of ArrayList and LinkedList
     */
    public static void difference(){
        double differenceSec = 0;
        double differenceTimes = 0;

        if(arrayListTime < linkedListTime){
            differenceSec = (linkedListTime - arrayListTime);
            differenceTimes = (linkedListTime / arrayListTime);
            System.out.println("ArrayList takes " + df.format(differenceSec) + "s less than LinkedList, it is " + df.format(differenceTimes) + " times faster\n");
        }else{
            differenceSec = (arrayListTime - linkedListTime);
            differenceTimes = (arrayListTime / linkedListTime);
            System.out.println("LinkedList takes " + df.format(differenceSec) + "s less than ArrayList, it is " + df.format(differenceTimes) + " times faster\n");
        }
    }

    public static void main(String[] args){
        start();
        arrayListTest("add");
        linkedListTest("add");
        difference();

        arrayListTest("search");
        linkedListTest("search");
        difference();

        arrayListTest("delete");
        linkedListTest("delete");
        difference();
    }
}

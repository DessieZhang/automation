package com.epam.part2.task2;

import java.text.DecimalFormat;
import java.util.*;
import com.github.javafaker.Faker;

public class HashMapVsTreeMap {
        private static int numbers = 0;
        private static DecimalFormat df = new DecimalFormat("#.#####");
        private static double hashMapTime, treeMapTime;
        private static HashMap<Integer,String> hashMap = new HashMap<Integer,String>();
        private static TreeMap<Integer,String> treeMap = new TreeMap<Integer,String>();
        private static Faker faker = new Faker();
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

        public static void hashMapTest(String method){
            double start = System.currentTimeMillis() * 0.001;

            for (int i = 0; i < numbers; i++) {
                switch (method.toLowerCase()){
                    case "add":
                        hashMap.put(i,faker.code().toString());
                        break;
                    case "search":
                        hashMap.get(i);
                        break;
                    case "delete":
                        hashMap.clear();
                        break;
                    default:
                        System.out.println("No such method");
                }
            }
            hashMapTime = (System.currentTimeMillis() * 0.001) - start;
            System.out.println("HashMap takes " + df.format(hashMapTime)  + "s to " + method +" "  + numbers + " elements");
        }

        public static void treeMapTest(String method) {
            double start = System.currentTimeMillis() * 0.001;

            for (int i = 0; i < numbers; i++) {
                switch (method.toLowerCase()) {
                    case "add":
                        treeMap.put(i,faker.code().toString());
                        break;
                    case "search":
                        treeMap.get(i);
                        break;
                    case "delete":
                        treeMap.clear();
                        break;
                    default:
                        System.out.println("No such method");
                }
            }
            treeMapTime = (System.currentTimeMillis() * 0.001) - start;
            System.out.println("TreeMap takes " + df.format(treeMapTime) + "s to " + method + " " + numbers + " elements");
        }

        public static void difference(){
            double differenceSec = 0;
            double differenceTimes = 0;

            if(hashMapTime < treeMapTime){
                differenceSec = (treeMapTime - hashMapTime);
                differenceTimes = (treeMapTime / hashMapTime);
                System.out.println("HashMap takes " + df.format(differenceSec) + "s less than TreeMap, it is " + df.format(differenceTimes) + " times faster\n");
            }else{
                differenceSec = (hashMapTime - treeMapTime);
                differenceTimes = (hashMapTime / treeMapTime);
                System.out.println("TreeMap takes " + df.format(differenceSec) + "s less than HashMap, it is " + df.format(differenceTimes) + " times faster\n");
            }
        }

        public static void main(String[] args){
            start();
            hashMapTest("add");
            treeMapTest("add");
            difference();

            hashMapTest("search");
            treeMapTest("search");
            difference();

            hashMapTest("delete");
            treeMapTest("delete");
            difference();
        }
    }

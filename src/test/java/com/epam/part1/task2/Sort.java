package com.epam.part1.task2;

public class Sort {
    /**
     * Sort the array of string according to lengths
     *
     * @param s string array request for sort
     */
    public static void sort(String[] s) {
        for (int i = 1; i < s.length; i++) {
            String temp = s[i];

            // Insert s[j] at its correct position
            int j = i - 1;
            while (j >= 0 && temp.length() < s[j].length()) {
                s[j + 1] = s[j];
                j--;
            }
            s[j + 1] = temp;
        }
    }

    /**
     * Print the sorted array of string
     *
     * @param str string array request for print
     */
    static void printArrayString(String str[]) {
        for (int i = 0; i < str.length; i++)
            System.out.print(str[i] + " ");
    }

    public static void main(String args[]) {
        String[] arr = {"China", "I", "from", "am"};

        // Perform sort
        sort(arr);

        // Print result
        printArrayString(arr);
    }
}
package com.epam.part1.task1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Calculator {
    /**
     * Provides addition, subtraction, multiplication and division operations
     *
     * @param operator + - * /
     * @param num1     double num1
     * @param num2     double num2
     * @return result
     */
    public static double operator(char operator, double num1, double num2) {
        double result = 0;
        switch (operator) {
            // performs addition between numbers
            case '+':
                result = num1 + num2;
                System.out.println(num1 + " + " + num2 + " = " + result);
                break;

            // performs subtraction between numbers
            case '-':
                result = num1 - num2;
                System.out.println(num1 + " - " + num2 + " = " + result);
                break;

            // performs multiplication between numbers
            case '*':
                result = num1 * num2;
                System.out.println(num1 + " * " + num2 + " = " + result);
                break;

            // performs division between numbers
            case '/':
                result = num1 / num2;
                System.out.println(num1 + " / " + num2 + " = " + result);
                break;

            default:
                System.out.println("Invalid operator!");
        }
        return result;
    }

    public static void main(String[] args) {
        char operator;
        double number1;
        double number2;

        Scanner input = new Scanner(System.in);

        // ask user to enter operator
        System.out.println("Please choose an operator: +, -, *, or /");
        operator = input.next().charAt(0);

        // ask user to enter numbers
        System.out.println("Enter the first number");
        number1 = input.nextDouble();

        System.out.println("Enter the second number");
        number2 = input.nextDouble();

        //calculate the result
        operator(operator, number1, number2);
    }
}

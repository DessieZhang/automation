package com.epam.part1.task1;

import java.util.Scanner;

public class Calculator {
    interface MathOperation {
        double operation(double a, double b);
    }

    MathOperation addition = (double a, double b) -> a + b;

    MathOperation subtraction = (double a, double b) -> a - b;

    MathOperation multiplication = (double a, double b) -> a * b;

    MathOperation division = (a, b) -> a / b;

    /**
     * Provides addition, subtraction, multiplication and division operations
     *
     * @param a             number1
     * @param b             number2
     * @param mathOperation addition, subtraction, multiplication or division
     * @return result
     */
    private double operate(double a, double b, MathOperation mathOperation) {
        return mathOperation.operation(a, b);
    }

    public static void main(String[] args) {
        char operator;
        double number1;
        double number2;
        double result;
        Scanner input = new Scanner(System.in);
        Calculator c = new Calculator();

        // ask user to enter operator
        System.out.println("Please choose an operator: +, -, *, or /");
        operator = input.next().charAt(0);

        // ask user to enter numbers
        System.out.println("Enter the first number");
        number1 = input.nextDouble();

        System.out.println("Enter the second number");
        number2 = input.nextDouble();
        switch (operator) {
            // performs addition between numbers
            case '+':
                result = c.operate(number1, number2, c.addition);
                System.out.println(number1 + " + " + number2 + " = " + result);
                break;

            // performs subtraction between numbers
            case '-':
                result = c.operate(number1, number2, c.division);
                System.out.println(number1 + " - " + number2 + " = " + result);
                break;

            // performs multiplication between numbers
            case '*':
                result = c.operate(number1, number2, c.multiplication);
                System.out.println(number1 + " * " + number2 + " = " + result);
                break;

            // performs division between numbers
            case '/':
                while ((int) number2 == 0) {
                    System.out.println("The divisor cannot be 0! Please re-enter a valid number:");
                    number2 = input.nextDouble();
                }
                result = c.operate(number1, number2, c.division);
                System.out.println(number1 + " / " + number2 + " = " + result);
                break;
            default:
                System.out.println("Invalid operator!");
        }
    }
}

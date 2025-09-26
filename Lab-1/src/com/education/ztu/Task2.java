package com.education.ztu;
import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть перше число: ");
        int num1 = scanner.nextInt();
        System.out.print("Введіть друге число ");
        int num2 = scanner.nextInt();

        int sum = num1 + num2;
        System.out.println("Сума чисел: " + num1 + " + " + num2 + " = " + sum);
        scanner.close();
    }
}

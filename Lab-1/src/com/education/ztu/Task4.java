package com.education.ztu;
import java.util.Scanner;

public class Task4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter First Number : ");
        int firstNumber = scanner.nextInt();
        System.out.print("Enter Second Number : ");
        int secondNumber = scanner.nextInt();
        int gcd = findGCD(firstNumber,secondNumber);
        System.out.println("Найбільший спільний дільник чисел " + firstNumber + " і " + secondNumber + " = " + gcd);
    }

    public static int findGCD(int x, int y){
     while( y!= 0){
         int temp = y;
         y = x % y;
         x = temp;
     }
     return x;
    }
}

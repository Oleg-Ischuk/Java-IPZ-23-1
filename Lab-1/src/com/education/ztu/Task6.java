package com.education.ztu;
import java.util.Scanner;

public class Task6 {
    public static long fibonacci(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        return fibonacci(n - 1) + fibonacci (n - 2);
    }

    public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Ввведіть кількість масиву: ");
    int n = scanner.nextInt();
    long[] fibArray = new long[n];
    for (int i = 0; i < n; i++) {
        fibArray[i] = fibonacci(i + 1);
    }
    long[] reverseFibArray = new long[n];
    for (int i = 0; i < n; i++) {
        reverseFibArray[i] = fibArray[n - 1 -i];
    }
    System.out.print("Фібоначчі: ");
    for (long num : fibArray) {
        System.out.print(num + " ");
    }
    System.out.println();
    System.out.print("Зворотна послідовність Фібоначчі: ");
    for (long num : reverseFibArray) {
        System.out.print(num + " ");
    }
    System.out.println();

    scanner.close();
    }
}

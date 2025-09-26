package com.education.ztu;
import java.util.Scanner;

public class Task5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть ціле число: ");
        while (!scanner.hasNextInt()){
            System.out.print("Некоректне введення. Введіть ціле числло: ");
            scanner.next();
        }
        int number = scanner.nextInt();
        while(number <= 0){
            System.out.print("Число має бути позитивним. Спробуйте ще раз: ");
            while(!scanner.hasNextInt()){
                System.out.print("Некоректне введення. Ввведіть ціле число: ");
                scanner.next();
            }
            number = scanner.nextInt();
        }
        int sum = 0;
        int temp = number;
        while (temp > 0){
            sum += temp % 10;
            temp /= 10;
        }
        System.out.println("Сума цифр числа " + number + " = " + sum);
        scanner.close();
    }
}

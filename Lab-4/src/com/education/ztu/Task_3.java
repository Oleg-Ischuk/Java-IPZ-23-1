package com.education.ztu;

import java.util.Scanner;

public class Task_3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("========================================");
        System.out.println("ЗАВДАННЯ 3: StringBuilder");
        System.out.println("========================================");
        System.out.println();

        System.out.print("Введіть перше число: ");
        int a = scanner.nextInt();

        System.out.print("Введіть друге число: ");
        int b = scanner.nextInt();
        System.out.println();

        System.out.println("1. Створити рядки з операціями (append):");
        StringBuilder sb1 = new StringBuilder();
        sb1.append(a).append(" + ").append(b).append(" = ").append(a + b);
        System.out.println("   " + sb1);

        StringBuilder sb2 = new StringBuilder();
        sb2.append(a).append(" - ").append(b).append(" = ").append(a - b);
        System.out.println("   " + sb2);

        StringBuilder sb3 = new StringBuilder();
        sb3.append(a).append(" * ").append(b).append(" = ").append(a * b);
        System.out.println("   " + sb3);
        System.out.println();

        System.out.println("2. Замінити '=' на 'рівно' (insert + deleteCharAt):");
        StringBuilder sb4 = new StringBuilder();
        sb4.append(a).append(" + ").append(b).append(" = ").append(a + b);
        int index = sb4.indexOf("=");
        sb4.deleteCharAt(index);
        sb4.insert(index, "рівно");
        System.out.println("   " + sb4);
        System.out.println();

        System.out.println("3. Замінити '=' на 'рівно' (replace):");
        StringBuilder sb5 = new StringBuilder();
        sb5.append(a).append(" + ").append(b).append(" = ").append(a + b);
        int startIndex = sb5.indexOf("=");
        sb5.replace(startIndex, startIndex + 1, "рівно");
        System.out.println("   " + sb5);
        System.out.println();

        System.out.println("4. Змінити послідовність на протилежну (reverse):");
        StringBuilder sb6 = new StringBuilder();
        sb6.append(a).append(" + ").append(b).append(" = ").append(a + b);
        System.out.println("   Оригінал: " + sb6);
        sb6.reverse();
        System.out.println("   Реверс:   " + sb6);
        System.out.println();

        System.out.println("5. Визначити довжину та capacity:");
        StringBuilder sb7 = new StringBuilder();
        sb7.append(a).append(" + ").append(b).append(" = ").append(a + b);
        System.out.println("   Рядок: " + sb7);
        System.out.println("   Довжина (length): " + sb7.length());
        System.out.println("   Ємність (capacity): " + sb7.capacity());

        scanner.close();
    }
}
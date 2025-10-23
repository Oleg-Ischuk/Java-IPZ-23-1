package com.education.ztu;

public class Task_2 {
    public static void main(String[] args) {
        processString("I learn Java!!!");
    }

    public static void processString(String str) {
        System.out.println("========================================");
        System.out.println("ЗАВДАННЯ 2: StringPractise");
        System.out.println("========================================");
        System.out.println();
        System.out.println("Оригінальний рядок: " + str);
        System.out.println();

        System.out.println("1. Останній символ рядка:");
        System.out.println("   " + str.charAt(str.length() - 1));
        System.out.println();

        System.out.println("2. Чи закінчується рядок на '!!!':");
        System.out.println("   " + str.endsWith("!!!"));
        System.out.println();

        System.out.println("3. Чи починається рядок з 'I learn ':");
        System.out.println("   " + str.startsWith("I learn "));
        System.out.println();

        System.out.println("4. Чи містить рядок 'Java':");
        System.out.println("   " + str.contains("Java"));
        System.out.println();

        System.out.println("5. Позиція підрядка 'Java':");
        System.out.println("   " + str.indexOf("Java"));
        System.out.println();

        System.out.println("6. Замінити всі 'а' на 'о':");
        System.out.println("   " + str.replace('a', 'o'));
        System.out.println();

        System.out.println("7. Перетворити на верхній регістр:");
        System.out.println("   " + str.toUpperCase());
        System.out.println();

        System.out.println("8. Перетворити на нижній регістр:");
        System.out.println("   " + str.toLowerCase());
        System.out.println();

        System.out.println("9. Вирізати рядок 'Java':");
        int start = str.indexOf("Java");
        System.out.println("   " + str.substring(start, start + "Java".length()));
    }
}
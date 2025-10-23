package com.education.ztu;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Scanner;

public class Task_6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("============================================================");
        System.out.println("              ЗАВДАННЯ 6: Робота з датами");
        System.out.println("============================================================");
        System.out.println();

        System.out.println("Введіть дату та час початку лабораторної:");
        System.out.print("Рік: ");
        int year = scanner.nextInt();
        System.out.print("Місяць (1-12): ");
        int month = scanner.nextInt();
        System.out.print("День: ");
        int day = scanner.nextInt();
        System.out.print("Година (0-23): ");
        int hour = scanner.nextInt();
        System.out.print("Хвилина (0-59): ");
        int minute = scanner.nextInt();
        System.out.print("Секунда (0-59): ");
        int second = scanner.nextInt();
        System.out.println();

        LocalDateTime labStart = LocalDateTime.of(year, month, day, hour, minute, second);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

        System.out.println("============================================================");
        System.out.println("      1. Інформація про дату початку лабораторної");
        System.out.println("============================================================");
        System.out.printf("%-30s %s%n", "Повна дата та час:", labStart.format(formatter));
        System.out.println();

        System.out.printf("%-30s %s (%s)%n", "День тижня:", labStart.getDayOfWeek(), getDayOfWeekUkrainian(labStart.getDayOfWeek()));
        System.out.printf("%-30s %d%n", "День у році:", labStart.getDayOfYear());
        System.out.printf("%-30s %s (%s)%n", "Місяць:", labStart.getMonth(), getMonthUkrainian(labStart.getMonthValue()));
        System.out.printf("%-30s %d%n", "Рік:", labStart.getYear());
        System.out.printf("%-30s %02d%n", "Година:", labStart.getHour());
        System.out.printf("%-30s %02d%n", "Хвилина:", labStart.getMinute());
        System.out.printf("%-30s %02d%n", "Секунда:", labStart.getSecond());
        System.out.println();

        System.out.println("============================================================");
        System.out.println("            2. Перевірка високосного року");
        System.out.println("============================================================");
        boolean isLeapYear = Year.isLeap(labStart.getYear());
        System.out.printf("Рік %d %s%n", labStart.getYear(), (isLeapYear ? "є високосним" : "НЕ є високосним"));
        System.out.println();

        System.out.println("============================================================");
        System.out.println("                   3. Поточний час");
        System.out.println("============================================================");
        LocalDateTime currentTime = LocalDateTime.now();
        System.out.printf("%-30s %s%n", "Поточна дата та час:", currentTime.format(formatter));
        System.out.println();

        System.out.println("============================================================");
        System.out.println("                 4. Порівняння дат");
        System.out.println("============================================================");
        System.out.printf("%-30s %s%n", "Дата початку лабораторної:", labStart.format(formatter));
        System.out.printf("%-30s %s%n", "Поточна дата та час:", currentTime.format(formatter));
        System.out.println();

        if (currentTime.isAfter(labStart)) {
            System.out.println("Поточний час ПІЗНІШЕ за час початку лабораторної");
            Duration duration = Duration.between(labStart, currentTime);
            long hours = duration.toHours();
            long minutes = duration.toMinutes() % 60;
            System.out.printf("Минуло часу: %d годин %d хвилин%n", hours, minutes);
        } else if (currentTime.isBefore(labStart)) {
            System.out.println("Поточний час РАНІШЕ за час початку лабораторної");
        } else {
            System.out.println("Час початку лабораторної СПІВПАДАЄ з поточним часом");
        }
        System.out.println();

        System.out.println("============================================================");
        System.out.println("           5. Зміна елементів дати та часу");
        System.out.println("============================================================");
        System.out.printf("%-30s %s%n", "Оригінальна дата:", labStart.format(formatter));
        System.out.println();

        LocalDateTime modifiedDate = labStart;

        modifiedDate = modifiedDate.plusDays(7);
        System.out.printf("%-30s %s%n", "Додано 7 днів:", modifiedDate.format(formatter));

        modifiedDate = modifiedDate.plusMonths(2);
        System.out.printf("%-30s %s%n", "Додано 2 місяці:", modifiedDate.format(formatter));

        modifiedDate = modifiedDate.minusYears(1);
        System.out.printf("%-30s %s%n", "Віднято 1 рік:", modifiedDate.format(formatter));

        modifiedDate = modifiedDate.plusHours(3);
        System.out.printf("%-30s %s%n", "Додано 3 години:", modifiedDate.format(formatter));

        modifiedDate = modifiedDate.minusMinutes(15);
        System.out.printf("%-30s %s%n", "Віднято 15 хвилин:", modifiedDate.format(formatter));

        modifiedDate = modifiedDate.withYear(2025);
        System.out.printf("%-30s %s%n", "Змінено рік на 2025:", modifiedDate.format(formatter));

        modifiedDate = modifiedDate.withMonth(12);
        System.out.printf("%-30s %s%n", "Змінено місяць на 12:", modifiedDate.format(formatter));

        modifiedDate = modifiedDate.withDayOfMonth(25);
        System.out.printf("%-30s %s%n", "Змінено день на 25:", modifiedDate.format(formatter));

        modifiedDate = modifiedDate.withHour(18);
        System.out.printf("%-30s %s%n", "Змінено годину на 18:", modifiedDate.format(formatter));

        modifiedDate = modifiedDate.withMinute(30);
        System.out.printf("%-30s %s%n", "Змінено хвилину на 30:", modifiedDate.format(formatter));

        modifiedDate = modifiedDate.withSecond(45);
        System.out.printf("%-30s %s%n", "Змінено секунду на 45:", modifiedDate.format(formatter));
        System.out.println();

        System.out.printf("%-30s %s%n", "Фінальна змінена дата:", modifiedDate.format(formatter));
        System.out.println("============================================================");

        scanner.close();
    }

    public static String getDayOfWeekUkrainian(DayOfWeek day) {
        switch (day) {
            case MONDAY: return "Понеділок";
            case TUESDAY: return "Вівторок";
            case WEDNESDAY: return "Середа";
            case THURSDAY: return "Четвер";
            case FRIDAY: return "П'ятниця";
            case SATURDAY: return "Субота";
            case SUNDAY: return "Неділя";
            default: return "";
        }
    }

    public static String getMonthUkrainian(int month) {
        switch (month) {
            case 1: return "Січень";
            case 2: return "Лютий";
            case 3: return "Березень";
            case 4: return "Квітень";
            case 5: return "Травень";
            case 6: return "Червень";
            case 7: return "Липень";
            case 8: return "Серпень";
            case 9: return "Вересень";
            case 10: return "Жовтень";
            case 11: return "Листопад";
            case 12: return "Грудень";
            default: return "";
        }
    }
}
package com.education.ztu;

import java.util.Formatter;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Task_4 {
    public static void main(String[] args) {
        Formatter formatter = new Formatter();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        String currentDate = dateFormat.format(new Date());

        formatter.format("Дата та час покупки: %s\n", currentDate);
        formatter.format("=========================================================\n");
        formatter.format("%-4s %-16s %-20s %s\n", "№", "Товар", "Категорія", "Ціна");
        formatter.format("=========================================================\n");

        formatter.format("%-4s %-16s %-18s %,10.2f ₴\n", "1.", "Джинси", "Жіночий одяг", 1500.78);
        formatter.format("%-4s %-16s %-18s %,10.2f ₴\n", "2.", "Спідниця", "Жіночий одяг", 1000.56);
        formatter.format("%-4s %-16s %-18s %,10.2f ₴\n", "3.", "Краватка", "Чоловічий одяг", 500.78);
        formatter.format("%-4s %-16s %-18s %,10.2f ₴\n", "4.", "Сорочка", "Чоловічий одяг", 850.00);
        formatter.format("%-4s %-16s %-18s %,10.2f ₴\n", "5.", "Светр", "Жіночий одяг", 1200.50);
        formatter.format("%-4s %-16s %-18s %,10.2f ₴\n", "6.", "Куртка", "Чоловічий одяг", 2500.00);
        formatter.format("%-4s %-16s %-18s %,10.2f ₴\n", "7.", "Туфлі", "Жіноче взуття", 1800.99);
        formatter.format("%-4s %-16s %-18s %,10.2f ₴\n", "8.", "Ремінь", "Аксесуари", 450.25);
        formatter.format("%-4s %-16s %-18s %,10.2f ₴\n", "9.", "Шарф", "Аксесуари", 300.00);
        formatter.format("%-4s %-16s %-18s %,10.2f ₴\n", "10.", "Рукавички", "Аксесуари", 250.00);

        double total = 1500.78 + 1000.56 + 500.78 + 850.00 + 1200.50 + 2500.00 + 1800.99 + 450.25 + 300.00 + 250.00;

        formatter.format("=========================================================\n");
        formatter.format("%-45s %,10.2f ₴\n", "Разом:", total);

        System.out.println(formatter);

        formatter.close();
    }
}
package com.education.ztu;

import java.io.*;
import java.util.Formatter;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Task_3 {
    public static void main(String[] args) {
        String filePath = "directory_for_files/purchase_report.txt";
        FileWriter writer = null;
        FileReader reader = null;

        try {
            writer = new FileWriter(filePath);

            Formatter formatter = new Formatter(Locale.US);
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

            writer.write(formatter.toString());
            formatter.close();
            System.out.println("Звіт успішно записано у файл: " + filePath);

        } catch (IOException e) {
            System.out.println("Помилка при записі у файл: " + e.getMessage());
        } finally {
            try {
                if (writer != null) writer.close();
            } catch (IOException e) {
                System.out.println("Помилка при закритті FileWriter: " + e.getMessage());
            }
        }

        try {
            reader = new FileReader(filePath);
            int ch;
            System.out.println("\n=== Вміст файлу ===");
            while ((ch = reader.read()) != -1) {
                System.out.print((char) ch);
            }
        } catch (IOException e) {
            System.out.println("Помилка при читанні файлу: " + e.getMessage());
        } finally {
            try {
                if (reader != null) reader.close();
            } catch (IOException e) {
                System.out.println("Помилка при закритті FileReader: " + e.getMessage());
            }
        }
    }
}

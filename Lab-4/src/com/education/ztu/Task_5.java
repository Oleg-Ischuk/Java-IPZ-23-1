package com.education.ztu;

import java.io.File;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Task_5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n=================================================");
            System.out.println("Оберіть мову / Choose language / Sprache wählen:");
            System.out.println("=================================================");
            System.out.println("1 - Українська");
            System.out.println("2 - English");
            System.out.println("3 - Deutsch");
            System.out.println("0 - Вихід / Exit / Beenden");
            System.out.println("=================================================");
            System.out.print("Ваш вибір: ");

            int choice = scanner.nextInt();

            if (choice == 0) {
                exit = true;
                System.out.println("\nДякуємо! / Thank you! / Danke!");
                continue;
            }

            Locale locale;

            switch (choice) {
                case 1:
                    locale = new Locale("uk", "UA");
                    break;
                case 2:
                    locale = new Locale("en", "US");
                    break;
                case 3:
                    locale = new Locale("de", "DE");
                    break;
                default:
                    System.out.println("\nНевірний вибір! Спробуйте ще раз.");
                    System.out.println("Invalid choice! Try again.");
                    System.out.println("Ungültige Auswahl! Versuchen Sie es erneut.");
                    continue;
            }

            try {
                printReceipt(locale);
            } catch (Exception e) {
                System.out.println("\nПомилка: Не знайдено файл ресурсів для обраної мови!");
                System.out.println("Error: Resource file not found for selected language!");
                System.out.println("Fehler: Ressourcendatei für die ausgewählte Sprache nicht gefunden!");
                System.out.println("\nПеревірте наявність файлів:");
                System.out.println("- src/resources/data_uk_UA.properties");
                System.out.println("- src/resources/data_en_US.properties");
                System.out.println("- src/resources/data_de_DE.properties");
                System.out.println("\nДеталі помилки: " + e.getMessage());
            }

            System.out.println("\nНатисніть Enter для продовження...");
            System.out.println("Press Enter to continue...");
            System.out.println("Drücken Sie die Eingabetaste, um fortzufahren...");
            scanner.nextLine();
            scanner.nextLine();
        }

        scanner.close();
    }

    public static void printReceipt(Locale locale) {
        ResourceBundle bundle = ResourceBundle.getBundle("com.education.ztu.data", locale);
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

        Formatter formatter = new Formatter();

        String currentDate = dateFormat.format(new Date());

        formatter.format("\n%s: %s\n", bundle.getString("receipt.date"), currentDate);
        formatter.format("=======================================================\n");
        formatter.format("%-4s %-16s %-22s %s\n",
                bundle.getString("receipt.number"),
                bundle.getString("receipt.product"),
                bundle.getString("receipt.category"),
                bundle.getString("receipt.price"));
        formatter.format("=======================================================\n");

        double total = 0.0;

        for (int i = 1; i <= 10; i++) {
            String productName = bundle.getString("product" + i + ".name");
            String productCategory = bundle.getString("product" + i + ".category");
            double productPrice = Double.parseDouble(bundle.getString("product" + i + ".price"));

            formatter.format("%-4s %-16s %-22s %s\n",
                    i + ".",
                    productName,
                    productCategory,
                    currencyFormat.format(productPrice));

            total += productPrice;
        }

        formatter.format("=======================================================\n");
        formatter.format("%-45s %s\n", bundle.getString("receipt.total"), currencyFormat.format(total));

        System.out.println(formatter);
        formatter.close();
    }
}
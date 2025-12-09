package com.education.ztu;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║   Лабораторна робота №11 - JDBC        ║");
        System.out.println("║   Робота з базою даних через Java      ║");
        System.out.println("╚════════════════════════════════════════╝");

        while (!exit) {
            try {
                System.out.println("\n┌───────────────────────────────────────┐");
                System.out.println("│         ГОЛОВНЕ МЕНЮ                  │");
                System.out.println("├───────────────────────────────────────┤");
                System.out.println("│ 1. Statement: вставка 10 товарів      │");
                System.out.println("│ 2. PreparedStatement: вставка 5 +     │");
                System.out.println("│    вибірка + видалення                │");
                System.out.println("│ 3. Transaction: транзакція +          │");
                System.out.println("│    savepoint                          │");
                System.out.println("│ 4. DAO: демонстрація CRUD операцій    │");
                System.out.println("│ 5. Показати всі товари                │");
                System.out.println("│ 0. Вихід                              │");
                System.out.println("└───────────────────────────────────────┘");
                System.out.print("Виберіть пункт меню: ");

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        StatementExample.run();
                        break;
                    case 2:
                        PreparedStatementExample.run();
                        break;
                    case 3:
                        TransactionExample.run();
                        break;
                    case 4:
                        TestDAO.run();
                        break;
                    case 5:
                        ProductDAO.showAllProducts();
                        break;
                    case 0:
                        exit = true;
                        System.out.println("\n╔════════════════════════════════════╗");
                        System.out.println("║  Дякуємо за використання програми! ║");
                        System.out.println("╚════════════════════════════════════╝");
                        DatabaseConnection.closeConnection();
                        break;
                    default:
                        System.out.println("\n❌ Невірний вибір. Спробуйте ще раз.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\n❌ Помилка! Введіть число.");
                scanner.nextLine();
            } catch (Exception e) {
                System.err.println("\n❌ Непередбачена помилка: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
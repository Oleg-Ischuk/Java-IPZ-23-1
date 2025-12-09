package com.education.ztu;

public class TestDAO {
    public static void run() {
        ProductDAO dao = new ProductDAO();

        System.out.println("\n==== Демонстрація роботи DAO ====");

        // 1. INSERT
        System.out.println("\n1. Додавання нового товару:");
        dao.insert(new Product(0, "Test Item", 300, 5));

        // 2. SELECT ALL
        System.out.println("\n2. Всі товари в базі:");
        for (Product p : dao.getAll()) {
            System.out.println(p);
        }

        // 3. SELECT BY ID
        System.out.println("\n3. Отримання товару з ID=1:");
        Product p = dao.getById(1);
        if (p != null) {
            System.out.println("Знайдено: " + p);

            // 4. UPDATE
            System.out.println("\n4. Оновлення ціни товару з ID=1 на 999:");
            p.price = 999;
            dao.update(p);

            Product updated = dao.getById(1);
            if (updated != null) {
                System.out.println("Після оновлення: " + updated);
            }
        } else {
            System.out.println("Товар з ID=1 не знайдено.");
        }

        // 5. DELETE
        System.out.println("\n5. Видалення товару з ID=1:");
        dao.delete(1);

        System.out.println("\n6. Перевірка - всі товари після видалення:");
        for (Product product : dao.getAll()) {
            System.out.println(product);
        }
    }
}
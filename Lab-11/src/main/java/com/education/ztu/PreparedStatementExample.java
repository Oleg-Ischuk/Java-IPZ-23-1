package com.education.ztu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PreparedStatementExample {

    public static void run() {
        Connection conn = null;
        PreparedStatement psInsert = null;
        PreparedStatement psSelect = null;
        PreparedStatement psDelete = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnection.getConnection();

            System.out.println("\n==== Додавання 5 товарів через PreparedStatement (batch) ====");

            psInsert = conn.prepareStatement(
                    "INSERT INTO products(name, price, quantity, category_id) VALUES (?, ?, ?, ?)"
            );

            for (int i = 1; i <= 5; i++) {
                psInsert.setString(1, "Extra Product " + i);
                psInsert.setDouble(2, 99.99 + i);
                psInsert.setInt(3, 10 + i);
                psInsert.setObject(4, null);
                psInsert.addBatch();
            }

            int[] results = psInsert.executeBatch();
            System.out.println("Додано " + results.length + " товарів.");

            System.out.println("\n==== Товари без категорії (category_id IS NULL) ====");
            psSelect = conn.prepareStatement("SELECT * FROM products WHERE category_id IS NULL");
            rs = psSelect.executeQuery();

            int count = 0;
            while (rs.next()) {
                System.out.printf("ID: %d | Назва: %s | Ціна: %.2f%n",
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price")
                );
                count++;
            }
            System.out.println("Знайдено товарів: " + count);

            System.out.println("\n==== Видалення всіх товарів ====");
            psDelete = conn.prepareStatement("DELETE FROM products");
            int deleted = psDelete.executeUpdate();
            System.out.println("Видалено записів: " + deleted);

        } catch (SQLException e) {
            System.err.println("Помилка при роботі з PreparedStatement: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (psInsert != null) psInsert.close();
                if (psSelect != null) psSelect.close();
                if (psDelete != null) psDelete.close();
            } catch (SQLException e) {
                System.err.println("Помилка при закритті ресурсів: " + e.getMessage());
            }
        }
    }
}
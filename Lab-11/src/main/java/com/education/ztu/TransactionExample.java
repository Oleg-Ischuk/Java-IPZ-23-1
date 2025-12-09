package com.education.ztu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Savepoint;
import java.sql.SQLException;

public class TransactionExample {

    public static void run() {
        Connection conn = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        PreparedStatement psSelect = null;
        ResultSet rs = null;
        Savepoint sp = null;

        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);

            System.out.println("\n==== Робота з транзакціями та Savepoint ====");

            try {
                // Перший INSERT (коректний)
                ps1 = conn.prepareStatement(
                        "INSERT INTO products(name, price, quantity) VALUES ('Good Product', 100, 5)"
                );
                ps1.executeUpdate();
                System.out.println("Перший товар додано успішно.");

                sp = conn.setSavepoint("after_first");
                System.out.println("Створено Savepoint 'after_first'.");

                // Другий INSERT (з помилкою)
                ps2 = conn.prepareStatement(
                        "INSER INTO products(name) VALUES ('Broken')"
                );
                ps2.executeUpdate();
                System.out.println("Другий товар додано успішно.");

                conn.commit();
                System.out.println("Транзакція завершена успішно.");

            } catch (SQLException ex) {
                System.out.println("\n❌ Помилка при додаванні другого товару: " + ex.getMessage());

                if (sp != null) {
                    System.out.println("Відкат до savepoint 'after_first'...");
                    conn.rollback(sp);
                    conn.commit();
                    System.out.println("Збережено тільки перший товар.");
                } else {
                    conn.rollback();
                    System.out.println("Повний відкат транзакції.");
                }
            }

            System.out.println("\n==== Товари після транзакції ====");
            psSelect = conn.prepareStatement("SELECT * FROM products");
            rs = psSelect.executeQuery();

            int count = 0;
            while (rs.next()) {
                System.out.printf("ID: %d | Назва: %s | Ціна: %.2f | Кількість: %d%n",
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("quantity")
                );
                count++;
            }
            System.out.println("Всього товарів: " + count);

        } catch (SQLException e) {
            System.err.println("Критична помилка транзакції: " + e.getMessage());
            try {
                if (conn != null) {
                    conn.rollback();
                    System.out.println("Виконано повний відкат транзакції.");
                }
            } catch (SQLException ex) {
                System.err.println("Помилка при відкаті: " + ex.getMessage());
            }
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps1 != null) ps1.close();
                if (ps2 != null) ps2.close();
                if (psSelect != null) psSelect.close();
                if (conn != null) conn.setAutoCommit(true);
            } catch (SQLException e) {
                System.err.println("Помилка при закритті ресурсів: " + e.getMessage());
            }
        }
    }
}
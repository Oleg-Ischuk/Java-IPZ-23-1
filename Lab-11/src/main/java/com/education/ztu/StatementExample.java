package com.education.ztu;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StatementExample {

    public static void run() {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnection.getConnection();
            st = conn.createStatement();

            System.out.println("\n==== Додавання 10 товарів через Statement (batch) ====");

            st.addBatch("INSERT INTO products(name, price, quantity) VALUES('Phone A', 500, 10)");
            st.addBatch("INSERT INTO products(name, price, quantity) VALUES('Phone B', 600, 20)");
            st.addBatch("INSERT INTO products(name, price, quantity) VALUES('TV Samsung', 1800, 5)");
            st.addBatch("INSERT INTO products(name, price, quantity) VALUES('Laptop ASUS', 2400, 7)");
            st.addBatch("INSERT INTO products(name, price, quantity) VALUES('Laptop Acer', 1900, 8)");
            st.addBatch("INSERT INTO products(name, price, quantity) VALUES('Tablet Lenovo', 800, 15)");
            st.addBatch("INSERT INTO products(name, price, quantity) VALUES('Headphones Sony', 150, 30)");
            st.addBatch("INSERT INTO products(name, price, quantity) VALUES('Watch Xiaomi', 120, 40)");
            st.addBatch("INSERT INTO products(name, price, quantity) VALUES('Monitor LG', 700, 12)");
            st.addBatch("INSERT INTO products(name, price, quantity) VALUES('Keyboard HP', 90, 25)");

            int[] results = st.executeBatch();
            System.out.println("Додано " + results.length + " товарів.");

            rs = st.executeQuery("SELECT * FROM products");
            System.out.println("\n==== Список всіх товарів ====");
            while (rs.next()) {
                System.out.printf("ID: %d | Назва: %s | Ціна: %.2f | Кількість: %d%n",
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("quantity")
                );
            }

        } catch (SQLException e) {
            System.err.println("Помилка при роботі з Statement: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
            } catch (SQLException e) {
                System.err.println("Помилка при закритті ресурсів: " + e.getMessage());
            }
        }
    }
}
package com.education.ztu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DatabaseConnection {
    private static Connection connection;

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                ResourceBundle rb = ResourceBundle.getBundle("db");
                String url = rb.getString("db.url");
                String user = rb.getString("db.user");
                String pass = rb.getString("db.password");
                String driver = rb.getString("db.driver");

                Class.forName(driver);
                connection = DriverManager.getConnection(url, user, pass);
                System.out.println("З'єднання з базою даних встановлено.");
            }
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC драйвер не знайдено: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Помилка підключення до бази даних: " + e.getMessage());
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("З'єднання з базою даних закрито.");
            }
        } catch (SQLException e) {
            System.err.println("Помилка при закритті з'єднання: " + e.getMessage());
        }
    }
}
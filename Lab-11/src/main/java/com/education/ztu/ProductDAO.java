package com.education.ztu;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO extends AbstractDAO<Product> {

    @Override
    public void insert(Product p) {
        String sql = "INSERT INTO products(name, price, quantity) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.name);
            ps.setDouble(2, p.price);
            ps.setInt(3, p.quantity);
            ps.executeUpdate();
            System.out.println("Товар додано успішно!");

        } catch (SQLException e) {
            System.err.println("Помилка при додаванні товару: " + e.getMessage());
        }
    }

    @Override
    public Product getById(int id) {
        String sql = "SELECT * FROM products WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Product(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getDouble("price"),
                            rs.getInt("quantity")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Помилка при отриманні товару: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Product> getAll() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM products";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("quantity")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Помилка при отриманні списку товарів: " + e.getMessage());
        }
        return list;
    }

    @Override
    public void update(Product p) {
        String sql = "UPDATE products SET name=?, price=?, quantity=? WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.name);
            ps.setDouble(2, p.price);
            ps.setInt(3, p.quantity);
            ps.setInt(4, p.id);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Товар оновлено успішно!");
            } else {
                System.out.println("Товар з ID " + p.id + " не знайдено.");
            }

        } catch (SQLException e) {
            System.err.println("Помилка при оновленні товару: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM products WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Товар видалено успішно!");
            } else {
                System.out.println("Товар з ID " + id + " не знайдено.");
            }

        } catch (SQLException e) {
            System.err.println("Помилка при видаленні товару: " + e.getMessage());
        }
    }

    public static void showAllProducts() {
        ProductDAO dao = new ProductDAO();
        List<Product> products = dao.getAll();

        if (products.isEmpty()) {
            System.out.println("\nТовари відсутні.");
        } else {
            System.out.println("\n=== Всі товари ===");
            for (Product p : products) {
                System.out.println(p);
            }
        }
    }
}
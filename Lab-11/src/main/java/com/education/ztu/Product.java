package com.education.ztu;

public class Product {
    public int id;
    public String name;
    public double price;
    public int quantity;

    public Product() {}

    public Product(int id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return String.format("ID: %d | Назва: %s | Ціна: %.2f | Кількість: %d",
                id, name, price, quantity);
    }
}
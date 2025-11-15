package com.education.ztu;

public class ProductData {
    public static Product[] getProducts() {
        return new Product[]{
                new Product("Ноутбук", "Lenovo", 1200, 5),
                new Product("Смартфон", "Samsung", 900, 10),
                new Product("Монітор", "LG", 800, 3),
                new Product("Клавіатура", "Logitech", 100, 20),
                new Product("Миша", "Logitech", 50, 25),
                new Product("Планшет", "Apple", 1500, 2),
                new Product("Навушники", "Sony", 200, 15)
        };
    }
}

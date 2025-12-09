package com.education.ztu;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Task_6 {
    public static void main(String[] args) {
        Map<String, Product> productMap = new HashMap<>();

        productMap.put("iPhone 14", new Product("Apple", "iPhone 14", 2023, 999.99));
        productMap.put("Galaxy S23", new Product("Samsung", "Galaxy S23", 2023, 899.99));
        productMap.put("Mi 12", new Product("Xiaomi", "Mi 12", 2022, 499.99));

        System.out.println("Отримати продукт за ключем 'iPhone 14': " + productMap.get("iPhone 14"));
        System.out.println("Чи містить ключ 'Galaxy S23'? " + productMap.containsKey("Galaxy S23"));
        System.out.println("Чи містить значення Samsung Galaxy S23? " +
                productMap.containsValue(new Product("Samsung", "Galaxy S23", 2023, 899.99)));

        productMap.putIfAbsent("XPS 15", new Product("Dell", "XPS 15", 2023, 1499.99));
        System.out.println("\nПісля putIfAbsent:");
        productMap.forEach((k, v) -> System.out.println(k + " -> " + v));

        Map<String, Product> newProducts = new HashMap<>();
        newProducts.put("Pixel 7", new Product("Google", "Pixel 7", 2023, 799.99));
        newProducts.put("Spectre x360", new Product("HP", "Spectre x360", 2022, 1299.99));
        productMap.putAll(newProducts);

        System.out.println("\nПісля putAll:");
        productMap.forEach((k, v) -> System.out.println(k + " -> " + v));

        productMap.remove("Mi 12");
        System.out.println("\nПісля remove('Mi 12'):");
        productMap.forEach((k, v) -> System.out.println(k + " -> " + v));

        System.out.println("\nКлючі: " + productMap.keySet());
        System.out.println("Значення: " + productMap.values());
        System.out.println("Розмір Map: " + productMap.size());

        Set<Map.Entry<String, Product>> entries = productMap.entrySet();
        System.out.println("\nРобота з entrySet:");
        for (Map.Entry<String, Product> entry : entries) {
            System.out.println("Ключ: " + entry.getKey() + ", Значення: " + entry.getValue());
            entry.setValue(new Product("UpdatedBrand", entry.getValue().getModel(),
                    entry.getValue().getYear(), entry.getValue().getPrice()));
        }

        System.out.println("\nПісля оновлення значень через entrySet:");
        productMap.forEach((k, v) -> System.out.println(k + " -> " + v));

        productMap.clear();
        System.out.println("\nПісля clear, чи порожня Map? " + productMap.isEmpty());
    }
}

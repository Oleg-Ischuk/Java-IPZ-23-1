package com.education.ztu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Task_7 {
    public static void main(String[] args) {
        List<Product> products = new ArrayList<>(Arrays.asList(
                new Product("Apple", "iPhone 14", 2023, 999.99),
                new Product("Samsung", "Galaxy S23", 2023, 899.99),
                new Product("Xiaomi", "Mi 12", 2022, 499.99),
                new Product("Dell", "XPS 15", 2023, 1499.99),
                new Product("HP", "Spectre x360", 2022, 1299.99)
        ));

        Collections.sort(products);
        System.out.println("Відсортовані продукти:");
        products.forEach(System.out::println);

        Product searchProduct = new Product("", "", 0, 899.99);
        int index = Collections.binarySearch(products, searchProduct);
        System.out.println("\nІндекс продукту з ціною 899.99: " + index);

        Collections.reverse(products);
        System.out.println("\nПісля reverse:");
        products.forEach(System.out::println);

        Collections.shuffle(products);
        System.out.println("\nПісля shuffle:");
        products.forEach(System.out::println);

        Collections.fill(products, new Product("FilledBrand", "FilledModel", 2025, 1000.0));
        System.out.println("\nПісля fill:");
        products.forEach(System.out::println);

        List<Product> products2 = new ArrayList<>(Arrays.asList(
                new Product("A", "A1", 2020, 1),
                new Product("B", "B1", 2021, 2),
                new Product("C", "C1", 2022, 3),
                new Product("D", "D1", 2023, 4),
                new Product("E", "E1", 2024, 5)
        ));

        System.out.println("\nМаксимальний продукт: " + Collections.max(products2));
        System.out.println("Мінімальний продукт: " + Collections.min(products2));

        List<Product> copyList = new ArrayList<>(Arrays.asList(
                new Product("X", "X1", 0, 0.0),
                new Product("Y", "Y1", 0, 0.0),
                new Product("Z", "Z1", 0, 0.0),
                new Product("W", "W1", 0, 0.0),
                new Product("V", "V1", 0, 0.0)
        ));
        Collections.copy(copyList, products2);
        System.out.println("\nПісля copy:");
        copyList.forEach(System.out::println);

        Collections.rotate(products2, 2);
        System.out.println("\nПісля rotate на 2 позиції:");
        products2.forEach(System.out::println);

        List<Product> checkedList = Collections.checkedList(new ArrayList<>(), Product.class);
        checkedList.add(new Product("Checked", "List", 2025, 123.45));
        System.out.println("\ncheckedCollection:");
        checkedList.forEach(System.out::println);

        Product freqProduct = new Product("C", "C1", 2022, 3);
        int frequency = Collections.frequency(products2, freqProduct);
        System.out.println("\nЧастота продукту C1: " + frequency);
    }
}

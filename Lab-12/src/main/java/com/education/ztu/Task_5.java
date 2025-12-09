package com.education.ztu;

import java.util.NavigableSet;
import java.util.TreeSet;

public class Task_5 {
    public static void main(String[] args) {
        NavigableSet<Product> products = new TreeSet<>();

        products.add(new Product("Apple", "iPhone 14", 2023, 999.99));
        products.add(new Product("Samsung", "Galaxy S23", 2023, 899.99));
        products.add(new Product("Xiaomi", "Mi 12", 2022, 499.99));
        products.add(new Product("Dell", "XPS 15", 2023, 1499.99));
        products.add(new Product("HP", "Spectre x360", 2022, 1299.99));
        products.add(new Product("Google", "Pixel 7", 2023, 799.99));

        System.out.println("Всі продукти у TreeSet:");
        products.forEach(System.out::println);

        System.out.println("\nПерший продукт (first): " + products.first());
        System.out.println("Останній продукт (last): " + products.last());

        System.out.println("\nheadSet(899.99):");
        products.headSet(new Product("", "", 0, 899.99)).forEach(System.out::println);

        System.out.println("\nsubSet(799.99, 1300.0):");
        products.subSet(new Product("", "", 0, 799.99), new Product("", "", 0, 1300.0))
                .forEach(System.out::println);

        System.out.println("\ntailSet(1000.0):");
        products.tailSet(new Product("", "", 0, 1000.0)).forEach(System.out::println);

        System.out.println("\nceiling(900.0): " + products.ceiling(new Product("", "", 0, 900.0)));
        System.out.println("floor(900.0): " + products.floor(new Product("", "", 0, 900.0)));
        System.out.println("higher(900.0): " + products.higher(new Product("", "", 0, 900.0)));
        System.out.println("lower(900.0): " + products.lower(new Product("", "", 0, 900.0)));

        Product firstPolled = products.pollFirst();
        Product lastPolled = products.pollLast();
        System.out.println("\nПісля pollFirst та pollLast:");
        System.out.println("Перший видалений: " + firstPolled);
        System.out.println("Останній видалений: " + lastPolled);
        products.forEach(System.out::println);

        System.out.println("\nВідсортований у зворотньому порядку (descendingSet):");
        products.descendingSet().forEach(System.out::println);
    }
}

package com.education.ztu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Task_3 {
    public static void main(String[] args) {
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product("Apple", "iPhone 14", 2023, 999.99));
        products.add(new Product("Samsung", "Galaxy S23", 2023, 899.99));
        products.add(new Product("Xiaomi", "Mi 12", 2022, 499.99));

        ArrayList<Product> newProducts = new ArrayList<>();
        newProducts.add(new Product("Dell", "XPS 15", 2023, 1499.99));
        newProducts.add(new Product("HP", "Spectre x360", 2022, 1299.99));
        products.addAll(newProducts);

        System.out.println("Продукт на індексі 1: " + products.get(1));

        Product p = new Product("Apple", "iPhone 14", 2023, 999.99);
        System.out.println("Індекс першого Apple iPhone 14: " + products.indexOf(p));
        System.out.println("Індекс останнього Apple iPhone 14: " + products.lastIndexOf(p));

        Iterator<Product> iterator = products.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        ListIterator<Product> listIterator = products.listIterator();
        while (listIterator.hasNext()) {
            System.out.println(listIterator.next());
        }

        products.remove(p);
        System.out.println("\nПісля видалення Apple iPhone 14:");
        products.forEach(System.out::println);

        products.set(0, new Product("Google", "Pixel 7", 2023, 799.99));
        System.out.println("\nПісля заміни першого елемента:");
        products.forEach(System.out::println);

        Collections.sort(products);
        System.out.println("\nВідсортовано за ціною:");
        products.forEach(System.out::println);

        List<Product> subList = products.subList(1, 3);
        System.out.println("\nПідсписок елементів з 1 по 2:");
        subList.forEach(System.out::println);

        System.out.println("\nЧи містить список HP Spectre x360? " +
                products.contains(new Product("HP", "Spectre x360", 2022, 1299.99)));

        System.out.println("Чи порожній список? " + products.isEmpty());
        System.out.println("Розмір списку: " + products.size());

        Object[] array = products.toArray();
        System.out.println("\nМасив об'єктів:");
        for (Object obj : array) {
            System.out.println(obj);
        }

        ArrayList<Product> keepProducts = new ArrayList<>();
        keepProducts.add(new Product("Dell", "XPS 15", 2023, 1499.99));
        products.retainAll(keepProducts);
        System.out.println("\nПісля retainAll:");
        products.forEach(System.out::println);

        products.clear();
        System.out.println("\nПісля clear, чи порожній список? " + products.isEmpty());
    }
}

package com.education.ztu;

import java.util.*;
import java.util.stream.*;
import java.util.stream.Collectors;

public class Task_4 {
    public static void main(String[] args) {
        Product[] products = ProductData.getProducts();

        System.out.println("1) Всі бренди:");
        Arrays.stream(products)
                .map(Product::getBrand)
                .distinct()
                .forEach(System.out::println);

        System.out.println("\n2) 2 товари з ціною менше 1000:");
        Arrays.stream(products)
                .filter(p -> p.getPrice() < 1000)
                .limit(2)
                .forEach(System.out::println);

        int totalCount = Arrays.stream(products)
                .map(Product::getCount)
                .reduce(0, Integer::sum);
        System.out.println("\n3) Сума всіх товарів на складі: " + totalCount);

        System.out.println("\n4) Товари згруповані по бренду:");
        Map<String, List<Product>> groupedByBrand = Arrays.stream(products)
                .collect(Collectors.groupingBy(Product::getBrand));
        groupedByBrand.forEach((brand, list) -> System.out.println(brand + ": " + list));

        System.out.println("\n5) Товари відсортовані за ціною:");
        Product[] sortedByPrice = Arrays.stream(products)
                .sorted(Comparator.comparingDouble(Product::getPrice))
                .toArray(Product[]::new);
        Arrays.stream(sortedByPrice).forEach(System.out::println);

        System.out.println("\n6) Товари з кількістю більше 10:");
        Arrays.stream(products)
                .filter(p -> p.getCount() > 10)
                .map(Product::getName)
                .forEach(System.out::println);
    }
}

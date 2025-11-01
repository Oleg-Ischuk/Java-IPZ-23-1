package com.education.ztu;

import java.util.ArrayDeque;
import java.util.Deque;

public class Task_4 {
    public static void main(String[] args) {
        Deque<Product> queue = new ArrayDeque<>();

        queue.push(new Product("Apple", "iPhone 14", 2023, 999.99));
        queue.push(new Product("Samsung", "Galaxy S23", 2023, 899.99));
        queue.offerLast(new Product("Xiaomi", "Mi 12", 2022, 499.99));
        queue.offerLast(new Product("Dell", "XPS 15", 2023, 1499.99));

        System.out.println("Черга після додавання елементів:");
        queue.forEach(System.out::println);

        System.out.println("\nПерший елемент (getFirst): " + queue.getFirst());
        System.out.println("Останній елемент (peekLast): " + queue.peekLast());

        Product popped = queue.pop();
        System.out.println("\nЕлемент, видалений з початку (pop): " + popped);

        Product removedLast = queue.removeLast();
        System.out.println("Елемент, видалений з кінця (removeLast): " + removedLast);

        Product polledLast = queue.pollLast();
        System.out.println("Елемент, отриманий і видалений з кінця (pollLast): " + polledLast);

        System.out.println("\nСтан черги після операцій:");
        queue.forEach(System.out::println);

        queue.offerLast(new Product("HP", "Spectre x360", 2022, 1299.99));
        queue.push(new Product("Google", "Pixel 7", 2023, 799.99));

        System.out.println("\nЧерга після додавання ще двох елементів:");
        queue.forEach(System.out::println);

        System.out.println("\nПерший елемент (peek): " + queue.peek());
        System.out.println("Чи порожня черга? " + queue.isEmpty());
        System.out.println("Розмір черги: " + queue.size());

        queue.clear();
        System.out.println("\nПісля clear, чи порожня черга? " + queue.isEmpty());
    }
}

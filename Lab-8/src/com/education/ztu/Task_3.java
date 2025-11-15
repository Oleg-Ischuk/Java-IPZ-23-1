package com.education.ztu;

import java.util.function.Predicate;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.function.Function;

public class Task_3 {
    public static void main(String[] args) {
        // a) Predicate для перевірки, чи рядок можна перетворити на число
        Predicate<String> isNumber = s -> {
            try {
                Double.parseDouble(s);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        };

        Predicate<String> notEmpty = s -> !s.isEmpty();
        String testString = "123.45";
        System.out.println("a) Рядок \"" + testString + "\" можна привести до числа: "
                + isNumber.and(notEmpty).test(testString));

        // b) Consumer для виводу повідомлень про початок та кінець пари
        Consumer<String> startLesson = s -> System.out.println("Пара почалася о 8:30");
        Consumer<String> endLesson = s -> System.out.println("Пара закінчилася о 9:50");
        Consumer<String> lesson = startLesson.andThen(endLesson);
        lesson.accept("");

        // c) Supplier для виведення речення з літерами у верхньому регістрі
        Supplier<String> upperCaseSupplier = () -> "це речення у верхньому регістрі".toUpperCase();
        System.out.println(upperCaseSupplier.get());

        // d) Function для обчислення добутку чисел з рядка
        Function<String, Integer> multiplyNumbers = s -> {
            String[] parts = s.split("\\s+");
            int result = 1;
            for (String part : parts) {
                result *= Integer.parseInt(part);
            }
            return result;
        };
        String numbers = "2 3 4";
        System.out.println("Добуток чисел \"" + numbers + "\": " + multiplyNumbers.apply(numbers));
    }
}

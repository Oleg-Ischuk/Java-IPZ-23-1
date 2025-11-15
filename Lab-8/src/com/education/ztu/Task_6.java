package com.education.ztu;

import java.util.*;
import java.util.stream.*;

public class Task_6 {
    public static void main(String[] args) {
        Integer[] numbers = {5, 12, 7, 3, 18, 9};

        Optional<Integer> max = Arrays.stream(numbers)
                .max(Integer::compareTo);

        String result = max.map(String::valueOf)
                .orElse("Числа відсутні");

        System.out.println("Максимальне значення: " + result);

        // З порожнім масивом
        Integer[] emptyArray = {};
        Optional<Integer> maxEmpty = Arrays.stream(emptyArray)
                .max(Integer::compareTo);
        String resultEmpty = maxEmpty.map(String::valueOf)
                .orElse("Числа відсутні");
        System.out.println("Максимальне значення порожнього масиву: " + resultEmpty);
    }
}

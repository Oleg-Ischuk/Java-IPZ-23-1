package com.education.ztu;

// Перерахування LocationTask3
enum LocationTask3 {
    КИЇВ,
    ЛЬВІВ,
    ОДЕСА,
    ХАРКІВ,
    ДНІПРО
}

// Клас з операціями
class Operation {
    public static double addition(double... numbers) {
        double sum = 0;
        for (double n : numbers) sum += n;
        return sum;
    }

    public static double subtraction(double... numbers) {
        if (numbers.length == 0) return 0;
        double result = numbers[0];
        for (int i = 1; i < numbers.length; i++) result -= numbers[i];
        return result;
    }

    public static double multiplication(double... numbers) {
        double result = 1;
        for (double n : numbers) result *= n;
        return result;
    }

    public static double division(double... numbers) {
        if (numbers.length == 0) return 0;
        double result = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] != 0) result /= numbers[i];
            else {
                System.out.println("Ділення на нуль неможливе!");
                return 0;
            }
        }
        return result;
    }

    public static double average(double... numbers) {
        if (numbers.length == 0) return 0;
        return addition(numbers) / numbers.length;
    }

    public static double maximum(double... numbers) {
        if (numbers.length == 0) return Double.NaN;
        double max = numbers[0];
        for (double n : numbers) if (n > max) max = n;
        return max;
    }

    public static double minimum(double... numbers) {
        if (numbers.length == 0) return Double.NaN;
        double min = numbers[0];
        for (double n : numbers) if (n < min) min = n;
        return min;
    }
}

// Клас Task3 для демонстрації
public class Task3 {
    public static void main(String[] args) {
        double[] nums = {10, 5, 2};

        double sum = Operation.addition(nums);
        System.out.println("Сума чисел: " + String.format("%.2f", sum));

        double diff = Operation.subtraction(nums);
        System.out.println("Різниця чисел: " + String.format("%.2f", diff));

        double prod = Operation.multiplication(nums);
        System.out.println("Добуток чисел: " + String.format("%.2f", prod));

        double div = Operation.division(nums);
        System.out.println("Частка чисел: " + String.format("%.2f", div));

        double avg = Operation.average(nums);
        System.out.println("Середнє значення: " + String.format("%.2f", avg));

        double max = Operation.maximum(nums);
        System.out.println("Максимальне значення: " + String.format("%.2f", max));

        double min = Operation.minimum(nums);
        System.out.println("Мінімальне значення: " + String.format("%.2f", min));


        System.out.println("\nСписок локацій:");
        for (LocationTask3 loc : LocationTask3.values()) {
            System.out.println("- " + loc);
        }
    }
}

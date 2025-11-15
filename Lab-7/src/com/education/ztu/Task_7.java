package com.education.ztu;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.ArrayList;
import java.util.List;

public class Task_7 {

    private static int[] generateArray(int size) {
        Random random = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(1000); // числа від 0 до 999
        }
        return array;
    }

    private static long sumDigitsSingleThread(int[] array) {
        long sum = 0;
        for (int num : array) {
            while (num > 0) {
                sum += num % 10;
                num /= 10;
            }
        }
        return sum;
    }

    private static long sumDigitsMultiThread(int[] array, int numThreads) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        List<Future<Long>> futures = new ArrayList<>();
        int chunkSize = array.length / numThreads;

        for (int i = 0; i < numThreads; i++) {
            final int start = i * chunkSize;
            final int end = (i == numThreads - 1) ? array.length : start + chunkSize;

            Callable<Long> task = () -> {
                long sum = 0;
                for (int j = start; j < end; j++) {
                    int num = array[j];
                    while (num > 0) {
                        sum += num % 10;
                        num /= 10;
                    }
                }
                return sum;
            };
            futures.add(executor.submit(task));
        }

        long totalSum = 0;
        for (Future<Long> future : futures) {
            totalSum += future.get();
        }

        executor.shutdown();
        return totalSum;
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int size = 1_000_000;
        int[] array = generateArray(size);

        long startSingle = System.currentTimeMillis();
        long sumSingle = sumDigitsSingleThread(array);
        long endSingle = System.currentTimeMillis();
        System.out.println("Однопотокова сума цифр: " + sumSingle);
        System.out.println("Час виконання однопотокового: " + (endSingle - startSingle) + " мс");

        long startMulti = System.currentTimeMillis();
        long sumMulti = sumDigitsMultiThread(array, 5);
        long endMulti = System.currentTimeMillis();
        System.out.println("Багатопотокова сума цифр: " + sumMulti);
        System.out.println("Час виконання багатопотокового: " + (endMulti - startMulti) + " мс");
    }
}

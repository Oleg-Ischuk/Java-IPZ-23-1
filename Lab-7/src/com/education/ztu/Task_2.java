package com.education.ztu;

class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("Потік " + getName() + " запущений (RUNNING).");
        for (int i = 1; i <= 100; i++) {
            System.out.println(i + ": Я люблю програмувати!!!");
        }
        System.out.println("Потік " + getName() + " завершив роботу (TERMINATED).");
    }
}

public class Task_2 {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        System.out.println("Створено потік " + myThread.getName() + " (NEW)");

        System.out.println("Початковий пріоритет: " + myThread.getPriority());
        System.out.println("Чи живий? " + myThread.isAlive());
        System.out.println("Чи демон? " + myThread.isDaemon());

        myThread.setName("MyCustomThread");
        myThread.setPriority(Thread.MAX_PRIORITY);
        System.out.println("Оновлене ім'я: " + myThread.getName());
        System.out.println("Оновлений пріоритет: " + myThread.getPriority());

        myThread.start();

        try {
            myThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread mainThread = Thread.currentThread();
        System.out.println("Головний потік: " + mainThread.getName());
        System.out.println("Пріоритет головного потоку: " + mainThread.getPriority());
    }
}

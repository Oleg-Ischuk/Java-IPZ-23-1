package com.education.ztu;

import java.util.Scanner;

class SharedData {
    String data;
    boolean available = false;
}

class Reader extends Thread {
    private final SharedData shared;
    private final Scanner scanner;

    public Reader(SharedData shared) {
        this.shared = shared;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void run() {
        while (true) {
            synchronized (shared) {
                while (shared.available) {
                    try {
                        shared.wait();
                    } catch (InterruptedException e) {
                        return;
                    }
                }
                System.out.print("Введіть текст (exit для виходу): ");
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("exit")) {
                    System.exit(0);
                }
                shared.data = input;
                shared.available = true;
                shared.notify();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}

class Printer extends Thread {
    private final SharedData shared;

    public Printer(SharedData shared) {
        this.shared = shared;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (shared) {
                while (!shared.available) {
                    try {
                        shared.wait();
                    } catch (InterruptedException e) {
                        return;
                    }
                }
                System.out.println("Вивід: " + shared.data);
                shared.available = false;
                shared.notify();
            }
        }
    }
}

public class Task_6 {
    public static void main(String[] args) {
        SharedData shared = new SharedData();
        Reader reader = new Reader(shared);
        Printer printer = new Printer(shared);

        reader.start();
        printer.start();
    }
}

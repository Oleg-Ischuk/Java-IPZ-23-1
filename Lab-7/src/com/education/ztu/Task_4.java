package com.education.ztu;

class ArithmeticRunnable implements Runnable {
    private static int result = 1;

    private static synchronized void printNext() {
        System.out.print(result + " ");
        result++;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (ArithmeticRunnable.class) {
                if (result > 100) break;
                printNext();
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}

public class Task_4 {
    public static void main(String[] args) {
        Thread t1 = new Thread(new ArithmeticRunnable(), "Thread-1");
        Thread t2 = new Thread(new ArithmeticRunnable(), "Thread-2");
        Thread t3 = new Thread(new ArithmeticRunnable(), "Thread-3");

        t1.start();
        t2.start();
        t3.start();
    }
}

package com.education.ztu;

class ArithmeticRunnableSync implements Runnable {
    private static int result = 1;

    @Override
    public void run() {
        while (true) {
            synchronized(ArithmeticRunnableSync.class) {
                if (result > 100) break;
                System.out.print(result + " ");
                result++;
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}

public class Task_5 {
    public static void main(String[] args) {
        Thread t1 = new Thread(new ArithmeticRunnableSync(), "Arithmetic-1");
        Thread t2 = new Thread(new ArithmeticRunnableSync(), "Arithmetic-2");
        Thread t3 = new Thread(new ArithmeticRunnableSync(), "Arithmetic-3");

        t1.start();
        t2.start();
        t3.start();
    }
}

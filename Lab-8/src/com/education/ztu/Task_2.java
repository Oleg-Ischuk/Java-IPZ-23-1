package com.education.ztu;


public class Task_2 {
    public static void main(String[] args) {
        String message = "Виконання лямбда-виразу Printable: Hello, ZTU!";
        Printable printable = () -> System.out.println(message);
        printable.print();
    }
}

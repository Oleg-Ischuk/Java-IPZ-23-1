package com.education.ztu;

public class Task3 {
    public static void main(String[] args) {
        if (args.length == 0){
            System.out.println("Аргументи не передані");
        }
        System.out.println(String.join(" ", args));
    }
}

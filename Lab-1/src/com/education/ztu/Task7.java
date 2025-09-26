package com.education.ztu;

public class Task7 {
    public static void main(String[] args) {
        // Створюємо масив символів латинського алфавіту від 'A' до 'Z'
        char[] alphabet = new char[26];
        for (int i = 0; i < 26; i++) {
            alphabet[i] = (char) ('A' + i);
        }
        for (char c : alphabet) {
            System.out.println(c + " ==> " + (int) c);
        }
    }
}

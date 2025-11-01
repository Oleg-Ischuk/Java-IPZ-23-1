package com.education.ztu;

public class Task_2 {

    public static boolean checkCredentials(String login, String password, String confirmPassword) {
        try {
            if (!login.matches("[a-zA-Z0-9_]+") || login.length() >= 20) {
                throw new WrongLoginException("Login must contain only letters, digits, underscore and be less than 20 characters.");
            }

            if (!password.matches("[a-zA-Z0-9_]+") || password.length() >= 20 || !password.equals(confirmPassword)) {
                throw new WrongPasswordException("Password must contain only letters, digits, underscore, be less than 20 characters, and match confirmation.");
            }

            return true;

        } catch (WrongLoginException | WrongPasswordException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        // Тести
        System.out.println(checkCredentials("user_1", "pass123", "pass123")); // true
        System.out.println(checkCredentials("user!2", "pass123", "pass123")); // false
        System.out.println(checkCredentials("user_3", "pass123", "pass124")); // false
    }
}

package com.education.ztu;

import com.education.ztu.game.*;

import java.io.*;

public class Task_2 {
    public static void main(String[] args) {
        System.out.println("=== ЗАВДАННЯ 2: Серіалізація та Десеріалізація ===\n");

        Schoolar schoolar = new Schoolar("Ivan", 13);
        Student student = new Student("Mykola", 20);
        Employee employee = new Employee("Andriy", 28);

        Team<Student> studentTeam = new Team<>("Vpered");
        studentTeam.addNewParticipant(student);
        studentTeam.addNewParticipant(new Student("Viktoria", 21));

        System.out.println("\n--- Об'єкти ДО серіалізації ---");
        System.out.println("Schoolar: " + schoolar);
        System.out.println("Student: " + student);
        System.out.println("Employee: " + employee);
        System.out.println("Team: " + studentTeam);
        System.out.println("Student tempInfo (до серіалізації): " + student.getTempInfo());

        serializeObjects(schoolar, student, employee, studentTeam);

        System.out.println("\n--- Об'єкти ПІСЛЯ десеріалізації ---");
        Schoolar deserializedSchoolar = (Schoolar) deserializeObject("schoolar.ser");
        Student deserializedStudent = (Student) deserializeObject("student.ser");
        Employee deserializedEmployee = (Employee) deserializeObject("employee.ser");
        Team<Student> deserializedTeam = (Team<Student>) deserializeObject("team.ser");

        if (deserializedSchoolar != null) {
            System.out.println("Schoolar: " + deserializedSchoolar);
        }
        if (deserializedStudent != null) {
            System.out.println("Student: " + deserializedStudent);
            System.out.println("Student tempInfo: " + deserializedStudent.getTempInfo() + " (було виключено з серіалізації!)");
        }
        if (deserializedEmployee != null) {
            System.out.println("Employee: " + deserializedEmployee);
        }
        if (deserializedTeam != null) {
            System.out.println("Team: " + deserializedTeam);
        }

        System.out.println("\n--- Аналіз результатів ---");
        System.out.println("✓ Поля з модифікатором 'transient' НЕ були серіалізовані");
        System.out.println("✓ Після десеріалізації transient поля мають значення за замовчуванням:");
        System.out.println("  - tempInfo (String) = null");
        System.out.println("✓ Всі інші поля були успішно серіалізовані та десеріалізовані");
    }

    private static void serializeObjects(Schoolar schoolar, Student student,
                                         Employee employee, Team<Student> team) {
        System.out.println("\n--- Серіалізація об'єктів ---");

        serializeObject(schoolar, "schoolar.ser");
        serializeObject(student, "student.ser");
        serializeObject(employee, "employee.ser");
        serializeObject(team, "team.ser");

        System.out.println("Всі об'єкти успішно серіалізовані!");
    }


    private static void serializeObject(Object obj, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(filename))) {
            oos.writeObject(obj);
            System.out.println("✓ Серіалізовано: " + filename);
        } catch (IOException e) {
            System.err.println("Помилка при серіалізації " + filename + ": " + e.getMessage());
        }
    }


    private static Object deserializeObject(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(filename))) {
            Object obj = ois.readObject();
            System.out.println("✓ Десеріалізовано: " + filename);
            return obj;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Помилка при десеріалізації " + filename + ": " + e.getMessage());
            return null;
        }
    }
}
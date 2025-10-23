package com.education.ztu;

import com.education.ztu.game.*;
import java.util.*;

public class Main2 {
    public static void main(String[] args) {
        System.out.println("=== TASK 4: Comparison and sorting ===\n");

        Team<Participant> team = new Team<>("Mixed Team");

        Schoolar schoolar1 = new Schoolar("Dmytro", 14);
        Schoolar schoolar2 = new Schoolar("Anna", 13);
        Schoolar schoolar3 = new Schoolar("Boris", 15);

        Student student1 = new Student("Zoryana", 20);
        Student student2 = new Student("Anton", 22);
        Student student3 = new Student("Mykola", 19);

        Employee employee1 = new Employee("Viktor", 28);
        Employee employee2 = new Employee("Olena", 25);
        Employee employee3 = new Employee("Ihor", 30);

        List<Participant> participants = new ArrayList<>();
        participants.add(schoolar1);
        participants.add(student1);
        participants.add(employee1);
        participants.add(schoolar2);
        participants.add(student2);
        participants.add(employee2);
        participants.add(schoolar3);
        participants.add(student3);
        participants.add(employee3);

        System.out.println("=== Original list of participants ===");
        for (Participant p : participants) {
            System.out.println(p);
        }

        System.out.println("\n=== Sorting using Comparable (by name) ===");
        List<Participant> sortedByName = new ArrayList<>(participants);
        Collections.sort(sortedByName);
        for (Participant p : sortedByName) {
            System.out.println(p);
        }

        System.out.println("\n=== Sorting using Comparator (by age) ===");
        Comparator<Participant> ageComparator = new Comparator<Participant>() {
            @Override
            public int compare(Participant p1, Participant p2) {
                return Integer.compare(p1.getAge(), p2.getAge());
            }
        };

        List<Participant> sortedByAge = new ArrayList<>(participants);
        Collections.sort(sortedByAge, ageComparator);
        for (Participant p : sortedByAge) {
            System.out.println(p);
        }

        System.out.println("\n=== Sorting with priority: name, then age (Java 8) ===");
        Comparator<Participant> nameAndAgeComparator = Comparator
                .comparing(Participant::getName)
                .thenComparing(Participant::getAge);

        List<Participant> sortedByNameAndAge = new ArrayList<>(participants);
        Collections.sort(sortedByNameAndAge, nameAndAgeComparator);
        for (Participant p : sortedByNameAndAge) {
            System.out.println(p);
        }

        System.out.println("\n=== Demonstration with participants with same names ===");
        List<Participant> sameNameParticipants = new ArrayList<>();
        sameNameParticipants.add(new Schoolar("Ivan", 14));
        sameNameParticipants.add(new Student("Ivan", 20));
        sameNameParticipants.add(new Employee("Ivan", 28));
        sameNameParticipants.add(new Schoolar("Ivan", 12));

        System.out.println("Before sorting:");
        for (Participant p : sameNameParticipants) {
            System.out.println(p);
        }

        Collections.sort(sameNameParticipants, nameAndAgeComparator);
        System.out.println("\nAfter sorting (by name, then by age):");
        for (Participant p : sameNameParticipants) {
            System.out.println(p);
        }

        System.out.println("\n=== Other sorting options (Java 8) ===");

        System.out.println("\nSorting by age (descending):");
        List<Participant> sortedByAgeDesc = new ArrayList<>(participants);
        sortedByAgeDesc.sort(Comparator.comparing(Participant::getAge).reversed());
        for (Participant p : sortedByAgeDesc) {
            System.out.println(p);
        }

        System.out.println("\nSorting by name (reversed), then by age:");
        List<Participant> sortedComplex = new ArrayList<>(participants);
        sortedComplex.sort(Comparator.comparing(Participant::getName).reversed()
                .thenComparing(Participant::getAge));
        for (Participant p : sortedComplex) {
            System.out.println(p);
        }

        System.out.println("\n=== Using Stream API for sorting ===");
        System.out.println("Top 5 youngest participants:");
        participants.stream()
                .sorted(Comparator.comparing(Participant::getAge))
                .limit(5)
                .forEach(System.out::println);

        System.out.println("\n=== All comparison methods successfully demonstrated! ===");
    }
}
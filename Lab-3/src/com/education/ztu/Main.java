package com.education.ztu;

import com.education.ztu.game.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== TASK 3: Cloning and Object methods ===\n");

        Schoolar schoolar1 = new Schoolar("Ivan", 13);
        Schoolar schoolar2 = new Schoolar("Mariya", 15);
        Student student1 = new Student("Mykola", 20);

        System.out.println("=== Demonstration of toString() ===");
        System.out.println("Original schoolar1: " + schoolar1);
        System.out.println("Original student1: " + student1);

        System.out.println("\n=== Demonstration of clone() for Participant ===");
        Schoolar clonedSchoolar = (Schoolar) schoolar1.clone();
        System.out.println("Cloned schoolar: " + clonedSchoolar);
        System.out.println("Original and clone are different objects: " + (schoolar1 != clonedSchoolar));
        System.out.println("But have same values: " + schoolar1.equals(clonedSchoolar));

        clonedSchoolar.setName("Ivan_Clone");
        clonedSchoolar.setAge(14);
        System.out.println("After modification:");
        System.out.println("Original: " + schoolar1);
        System.out.println("Clone: " + clonedSchoolar);

        System.out.println("\n=== Demonstration of equals() ===");
        Schoolar schoolar3 = new Schoolar("Ivan", 13);
        System.out.println("schoolar1: " + schoolar1);
        System.out.println("schoolar3: " + schoolar3);
        System.out.println("schoolar1.equals(schoolar3): " + schoolar1.equals(schoolar3));
        System.out.println("schoolar1 == schoolar3: " + (schoolar1 == schoolar3));
        System.out.println("schoolar1.equals(clonedSchoolar): " + schoolar1.equals(clonedSchoolar));

        System.out.println("\n=== Demonstration of hashCode() ===");
        System.out.println("schoolar1 hashCode: " + schoolar1.hashCode());
        System.out.println("schoolar3 hashCode: " + schoolar3.hashCode());
        System.out.println("clonedSchoolar hashCode: " + clonedSchoolar.hashCode());
        System.out.println("Equal objects have same hashCode: " +
                (schoolar1.equals(schoolar3) && schoolar1.hashCode() == schoolar3.hashCode()));

        System.out.println("\n=== Creating team ===");
        Team<Schoolar> originalTeam = new Team<>("Young Dragons");
        originalTeam.addNewParticipant(schoolar1);
        originalTeam.addNewParticipant(schoolar2);
        System.out.println("Original team: " + originalTeam);

        System.out.println("\n=== Deep cloning via copy constructor ===");
        Team<Schoolar> copiedTeam = new Team<>(originalTeam);
        copiedTeam.setName("Young Dragons Copy");
        System.out.println("Copied team: " + copiedTeam);

        System.out.println("\n=== Checking copy independence ===");
        copiedTeam.getParticipants().get(0).setName("Modified_Ivan");
        System.out.println("After modifying copied team participant:");
        System.out.println("Original team first participant: " +
                originalTeam.getParticipants().get(0).getName());
        System.out.println("Copied team first participant: " +
                copiedTeam.getParticipants().get(0).getName());
        System.out.println("Deep clone successful - objects are independent!");

        System.out.println("\n=== Deep cloning via static method ===");
        Team<Student> studentTeam = new Team<>("Smart Students");
        studentTeam.addNewParticipant(student1);
        studentTeam.addNewParticipant(new Student("Viktoria", 21));
        System.out.println("Original student team: " + studentTeam);

        Team<Student> clonedStudentTeam = Team.deepClone(studentTeam);
        clonedStudentTeam.setName("Smart Students Clone");
        System.out.println("Cloned student team: " + clonedStudentTeam);

        clonedStudentTeam.getParticipants().get(0).setAge(25);
        System.out.println("\nAfter modifying cloned team:");
        System.out.println("Original team first participant age: " +
                studentTeam.getParticipants().get(0).getAge());
        System.out.println("Cloned team first participant age: " +
                clonedStudentTeam.getParticipants().get(0).getAge());

        System.out.println("\n=== equals() and hashCode() for Team ===");
        Team<Schoolar> team1 = new Team<>("Test Team");
        team1.addNewParticipant(new Schoolar("Test", 10));

        Team<Schoolar> team2 = new Team<>("Test Team");
        team2.addNewParticipant(new Schoolar("Test", 10));

        System.out.println("team1: " + team1);
        System.out.println("team2: " + team2);
        System.out.println("team1.equals(team2): " + team1.equals(team2));
        System.out.println("team1.hashCode(): " + team1.hashCode());
        System.out.println("team2.hashCode(): " + team2.hashCode());

        System.out.println("\n=== All methods successfully demonstrated! ===");
    }
}
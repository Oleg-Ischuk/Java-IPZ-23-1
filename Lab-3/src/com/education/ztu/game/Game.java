package com.education.ztu.game;

public class Game {
    public static void main(String[] args) {
        Schoolar schoolar1 = new Schoolar("Ivan", 13);
        Schoolar schoolar2 = new Schoolar("Mariya", 15);
        Schoolar schoolar3 = new Schoolar("Sergey", 12);
        Schoolar schoolar4 = new Schoolar("Olga", 14);

        Student student1 = new Student("Mykola", 20);
        Student student2 = new Student("Viktoria", 21);
        Student student3 = new Student("Andriy", 22);
        Student student4 = new Student("Kateryna", 19);

        Employee employee1 = new Employee("Andriy", 28);
        Employee employee2 = new Employee("Oksana", 25);
        Employee employee3 = new Employee("Petro", 30);
        Employee employee4 = new Employee("Natalia", 27);

        System.out.println("=== Creating Schoolar Teams ===");
        Team<Schoolar> schoolarTeam1 = new Team<>("Dragon");
        schoolarTeam1.addNewParticipant(schoolar1);
        schoolarTeam1.addNewParticipant(schoolar2);

        Team<Schoolar> schoolarTeam2 = new Team<>("Rozumnyky");
        schoolarTeam2.addNewParticipant(schoolar3);
        schoolarTeam2.addNewParticipant(schoolar4);

        System.out.println("\n=== Creating Student Teams ===");
        Team<Student> studentTeam1 = new Team<>("Vpered");
        studentTeam1.addNewParticipant(student1);
        studentTeam1.addNewParticipant(student2);

        Team<Student> studentTeam2 = new Team<>("Intellect");
        studentTeam2.addNewParticipant(student3);
        studentTeam2.addNewParticipant(student4);

        System.out.println("\n=== Creating Employee Teams ===");
        Team<Employee> employeeTeam1 = new Team<>("Robotyagi");
        employeeTeam1.addNewParticipant(employee1);
        employeeTeam1.addNewParticipant(employee2);

        Team<Employee> employeeTeam2 = new Team<>("Professionals");
        employeeTeam2.addNewParticipant(employee3);
        employeeTeam2.addNewParticipant(employee4);

        System.out.println("\n=== Games ===");
        System.out.println("Schoolar teams playing:");
        schoolarTeam1.playWith(schoolarTeam2);

        System.out.println("\nStudent teams playing:");
        studentTeam1.playWith(studentTeam2);

        System.out.println("\nEmployee teams playing:");
        employeeTeam1.playWith(employeeTeam2);

        System.out.println("\n=== Demonstration of constraints (commented in code) ===");
        System.out.println("- Cannot add participant from another league to team");
        System.out.println("- Teams of different leagues cannot play with each other");
        System.out.println("- All constraints are checked at compile time thanks to generics!");
    }
}
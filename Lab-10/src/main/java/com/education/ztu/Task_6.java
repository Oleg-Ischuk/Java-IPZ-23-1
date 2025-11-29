package com.education.ztu;

import com.education.ztu.game.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Task_6: JSON Parser - Serialization and Deserialization
 * Demonstrates conversion of game entities (Participant types and Teams)
 * to JSON format and back to Java objects using Gson library.
 *
 * @author ZTU Student
 * @version 1.0
 * @since 2024
 */
public class Task_6 {

    private static final Logger logger = LoggerFactory.getLogger(Task_6.class);

    public static void main(String[] args) {
        logger.info("Starting Task_6: JSON Parser");
        System.out.println("=== Task 6: JSON Parser - Java to JSON and JSON to Java ===\n");

        System.out.println("--- 1. Schoolar to JSON and back ---");
        Schoolar schoolar = new Schoolar("Ivan", 13);
        System.out.println("Original Schoolar: " + schoolar);

        String schoolarJson = JsonParser.toJson(schoolar);
        System.out.println("JSON:\n" + schoolarJson);

        Schoolar schoolarDeserialized = JsonParser.fromJson(schoolarJson, Schoolar.class);
        System.out.println("Deserialized Schoolar: " + schoolarDeserialized);

        System.out.println("\n--- 2. Student to JSON and back ---");
        Student student = new Student("Mykola", 20);
        System.out.println("Original Student: " + student);

        String studentJson = JsonParser.toJson(student);
        System.out.println("JSON:\n" + studentJson);

        Student studentDeserialized = JsonParser.fromJson(studentJson, Student.class);
        System.out.println("Deserialized Student: " + studentDeserialized);

        System.out.println("\n--- 3. Employee to JSON and back ---");
        Employee employee = new Employee("Andriy", 28);
        System.out.println("Original Employee: " + employee);

        String employeeJson = JsonParser.toJson(employee);
        System.out.println("JSON:\n" + employeeJson);

        Employee employeeDeserialized = JsonParser.fromJson(employeeJson, Employee.class);
        System.out.println("Deserialized Employee: " + employeeDeserialized);

        System.out.println("\n--- 4. Team<Schoolar> to JSON and back ---");
        Team<Schoolar> schoolarTeam = new Team<>("Dragon");
        schoolarTeam.addNewParticipant(new Schoolar("Ivan", 13));
        schoolarTeam.addNewParticipant(new Schoolar("Mariya", 15));
        System.out.println("Original Team: " + schoolarTeam);

        String schoolarTeamJson = JsonParser.toJson(schoolarTeam);
        System.out.println("JSON:\n" + schoolarTeamJson);

        Team<Schoolar> schoolarTeamDeserialized = JsonParser.fromJson(schoolarTeamJson, Team.class);
        System.out.println("Deserialized Team: " + schoolarTeamDeserialized);

        System.out.println("\n--- 5. Team<Student> to JSON and back ---");
        Team<Student> studentTeam = new Team<>("Vpered");
        studentTeam.addNewParticipant(new Student("Mykola", 20));
        studentTeam.addNewParticipant(new Student("Viktoria", 21));
        System.out.println("Original Team: " + studentTeam);

        String studentTeamJson = JsonParser.toJson(studentTeam);
        System.out.println("JSON:\n" + studentTeamJson);

        Team<Student> studentTeamDeserialized = JsonParser.fromJson(studentTeamJson, Team.class);
        System.out.println("Deserialized Team: " + studentTeamDeserialized);

        System.out.println("\n--- 6. Team<Employee> to JSON and back ---");
        Team<Employee> employeeTeam = new Team<>("Robotyagi");
        employeeTeam.addNewParticipant(new Employee("Andriy", 28));
        employeeTeam.addNewParticipant(new Employee("Oksana", 25));
        System.out.println("Original Team: " + employeeTeam);

        String employeeTeamJson = JsonParser.toJson(employeeTeam);
        System.out.println("JSON:\n" + employeeTeamJson);

        Team<Employee> employeeTeamDeserialized = JsonParser.fromJson(employeeTeamJson, Team.class);
        System.out.println("Deserialized Team: " + employeeTeamDeserialized);

        System.out.println("\n--- 7. Save and Load from JSON file ---");
        Student fileStudent = new Student("John", 22);
        String filePath = "student.json";

        JsonFileOperations.saveToJsonFile(fileStudent, filePath);
        Student loadedStudent = JsonFileOperations.loadFromJsonFile(filePath, Student.class);
        System.out.println("Loaded Student: " + loadedStudent);

        logger.info("Task_6: JSON Parser completed successfully");
    }
}
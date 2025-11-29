package com.education.ztu;

import com.education.ztu.game.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Task_3 {
    private static final Logger logger = LoggerFactory.getLogger(Task_3.class);

    public static void main(String[] args) {
        logger.info("=== ЗАВДАННЯ 3: Демонстрація логування ===");
        logger.trace("Starting application with TRACE level logging");
        logger.trace("JVM Version: {}", System.getProperty("java.version"));
        logger.debug("Creating participants for demonstration");

        try {
            demonstrateParticipantCreation();
            demonstrateTeamCreation();
            demonstrateGameplay();
            demonstrateErrorHandling();
            logger.info("Application completed successfully");

        } catch (Exception e) {
            logger.error("Critical error occurred during execution", e);
        }

        logger.trace("Application finished");
    }

    private static void demonstrateParticipantCreation() {
        logger.info("--- Demonstration 1: Creating Participants ---");
        logger.debug("Creating schoolar participants");

        Schoolar schoolar1 = new Schoolar("Ivan", 13);
        logger.trace("Schoolar1 created: {}", schoolar1);

        Schoolar schoolar2 = new Schoolar("Mariya", 15);
        logger.trace("Schoolar2 created: {}", schoolar2);

        logger.debug("Creating student participants");
        Student student1 = new Student("Mykola", 20);
        Student student2 = new Student("Viktoria", 21);

        logger.debug("Creating employee participants");
        Employee employee1 = new Employee("Andriy", 28);
        Employee employee2 = new Employee("Oksana", 25);

        logger.info("Successfully created {} participants", 6);
    }

    private static void demonstrateTeamCreation() {
        logger.info("--- Demonstration 2: Creating Teams ---");

        logger.debug("Creating schoolar team");
        Team<Schoolar> schoolarTeam = new Team<>("Dragon");

        Schoolar schoolar1 = new Schoolar("Ivan", 13);
        Schoolar schoolar2 = new Schoolar("Mariya", 15);

        schoolarTeam.addNewParticipant(schoolar1);
        schoolarTeam.addNewParticipant(schoolar2);

        logger.info("Schoolar team '{}' created with {} members",
                schoolarTeam.getName(), schoolarTeam.getParticipants().size());

        logger.debug("Creating student team");
        Team<Student> studentTeam = new Team<>("Vpered");

        Student student1 = new Student("Mykola", 20);
        Student student2 = new Student("Viktoria", 21);

        studentTeam.addNewParticipant(student1);
        studentTeam.addNewParticipant(student2);

        logger.info("Student team '{}' created with {} members",
                studentTeam.getName(), studentTeam.getParticipants().size());
    }

    private static void demonstrateGameplay() {
        logger.info("--- Demonstration 3: Team Gameplay ---");

        Team<Student> team1 = new Team<>("Alpha");
        Team<Student> team2 = new Team<>("Beta");

        team1.addNewParticipant(new Student("Alex", 22));
        team1.addNewParticipant(new Student("Maria", 21));

        team2.addNewParticipant(new Student("John", 23));
        team2.addNewParticipant(new Student("Kate", 20));

        logger.debug("Both teams are ready for the game");
        logger.info("Starting game between '{}' and '{}'", team1.getName(), team2.getName());

        team1.playWith(team2);

        logger.info("Game completed successfully");
    }

    private static void demonstrateErrorHandling() {
        logger.info("--- Demonstration 4: Error Handling and Different Log Levels ---");

        logger.warn("Attempting to create participant with unusual age");

        try {
            Schoolar youngSchoolar = new Schoolar("TestChild", 5);
            logger.debug("Young schoolar created: age={}", youngSchoolar.getAge());

            logger.warn("Attempting to set negative age - this should cause an error");
            youngSchoolar.setAge(-5);

        } catch (IllegalArgumentException e) {
            logger.error("Failed to set age: {}", e.getMessage());
            logger.debug("Exception details", e);
        }

        logger.warn("Testing null handling in Team");
        try {
            Team<Student> team = new Team<>("TestTeam");
            logger.debug("Attempting to add null participant");
            team.addNewParticipant(null);
        } catch (IllegalArgumentException e) {
            logger.error("Cannot add null participant: {}", e.getMessage());
        }

        logger.info("Error handling demonstration completed");

        logger.error("This would be FATAL level in Log4j - critical system failure simulation");

        logger.trace("TRACE: Most detailed information for debugging flow");
        logger.debug("DEBUG: Detailed information for diagnosing problems");
        logger.info("INFO: General informational messages");
        logger.warn("WARN: Warning messages about potential problems");
        logger.error("ERROR: Error messages about failures");

        logger.info("All logging levels demonstrated successfully");
    }
}
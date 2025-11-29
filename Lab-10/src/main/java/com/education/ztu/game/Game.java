package com.education.ztu.game;

/**
 * Demonstration class for the game system with type-safe team management.
 *
 * <p>This class serves as the main entry point and example usage of the game framework.
 * It demonstrates how to:
 * <ul>
 *   <li>Create participants of different types (Schoolar, Student, Employee)</li>
 *   <li>Form teams with type-safe generics</li>
 *   <li>Simulate games between teams</li>
 * </ul>
 * </p>
 *
 * <h2>Program Structure:</h2>
 * <p>The program creates three leagues:
 * <ul>
 *   <li><b>Schoolar League:</b> Two teams with schoolar participants
 *     <ul>
 *       <li>Dragon team (Ivan, Mariya)</li>
 *       <li>Rozumnyky team (Sergey, Olga)</li>
 *     </ul>
 *   </li>
 *   <li><b>Student League:</b> Two teams with student participants
 *     <ul>
 *       <li>Vpered team (Mykola, Viktoria)</li>
 *       <li>Intellect team (Andriy, Kateryna)</li>
 *     </ul>
 *   </li>
 *   <li><b>Employee League:</b> Two teams with employee participants
 *     <ul>
 *       <li>Robotyagi team (Andriy, Oksana)</li>
 *       <li>Professionals team (Petro, Natalia)</li>
 *     </ul>
 *   </li>
 * </ul>
 * </p>
 *
 * <h2>Type Safety Guarantees:</h2>
 * <p>Thanks to Java Generics, the following constraints are enforced at compile time:
 * <ul>
 *   <li>Cannot add a Student to a {@code Team<Schoolar>}</li>
 *   <li>Cannot add an Employee to a {@code Team<Student>}</li>
 *   <li>Cannot mix different participant types in the same team</li>
 *   <li>Teams of different types cannot play against each other</li>
 * </ul>
 * These constraints prevent logical errors that would only be caught at runtime
 * in a non-generic implementation.
 * </p>
 *
 * <h2>Game Simulation:</h2>
 * <p>Each team plays one game:
 * <ul>
 *   <li>Schoolar teams: Dragon vs Rozumnyky</li>
 *   <li>Student teams: Vpered vs Intellect</li>
 *   <li>Employee teams: Robotyagi vs Professionals</li>
 * </ul>
 * The winner of each game is determined randomly.
 * </p>
 *
 * <h2>Execution:</h2>
 * <p>Run this class as a Java application to see the game demonstration:
 * <pre>
 * java com.education.ztu.game.Game
 * </pre>
 * </p>
 *
 * <h2>Output:</h2>
 * <p>The program outputs:
 * <ul>
 *   <li>Creation messages for each team and participant</li>
 *   <li>Game results for each match</li>
 *   <li>Notifications about type safety guarantees</li>
 * </ul>
 * </p>
 *
 * @author ZTU Student
 * @version 1.0
 * @since 2024
 *
 * @see Participant
 * @see Schoolar
 * @see Student
 * @see Employee
 * @see Team
 */
public class Game {

    /**
     * Main method to run the game demonstration.
     *
     * <p>This method:
     * <ul>
     *   <li>Creates 12 participants (4 of each type)</li>
     *   <li>Organizes them into 6 teams (2 of each type)</li>
     *   <li>Simulates 3 games (one for each participant type)</li>
     *   <li>Displays team creation messages and game results</li>
     * </ul>
     * </p>
     *
     * @param args command-line arguments (not used)
     */
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

        /*
         * The following lines would cause COMPILE-TIME errors:
         *
         * // Error: Cannot add Student to Team<Schoolar>
         * schoolarTeam1.addNewParticipant(student1);
         *
         * // Error: Cannot add Employee to Team<Student>
         * studentTeam1.addNewParticipant(employee1);
         *
         * // Error: Cannot play Team<Schoolar> vs Team<Student>
         * schoolarTeam1.playWith(studentTeam1);
         */
    }
}
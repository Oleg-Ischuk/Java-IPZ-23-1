package com.education.ztu.game;

/**
 * Represents an employee/working professional participant in the game system.
 *
 * <p>This class extends the abstract {@link Participant} class and specializes it for
 * working professionals and employees, typically ages 25 and above. Employee participants
 * form teams of their own type and compete with other employee teams.</p>
 *
 * <h2>Characteristics:</h2>
 * <ul>
 *   <li><b>Age Range:</b> Typically 25+ years old (working professionals)</li>
 *   <li><b>Team Composition:</b> Teams can only contain other Employee participants</li>
 *   <li><b>Serialization:</b> Fully serializable with custom serialVersionUID</li>
 * </ul>
 *
 * <h2>Type Safety:</h2>
 * <p>Through Java Generics in the {@link Team} class, a {@code Team<Employee>} can only
 * contain Employee participants. This prevents mixing different participant types in
 * the same team and is enforced at compile time. For example, you cannot add a Student
 * or Schoolar to an Employee team.</p>
 *
 * <h2>Usage Example:</h2>
 * <pre>
 * // Create individual employee participants
 * Employee andriy = new Employee("Andriy", 28);
 * Employee oksana = new Employee("Oksana", 25);
 *
 * // Create a team for employees
 * Team&lt;Employee&gt; robotyagiTeam = new Team&lt;&gt;("Robotyagi");
 *
 * // Add employees to the team
 * robotyagiTeam.addNewParticipant(andriy);
 * robotyagiTeam.addNewParticipant(oksana);
 *
 * // Create another team and play a game
 * Team&lt;Employee&gt; professionalsTeam = new Team&lt;&gt;("Professionals");
 * robotyagiTeam.playWith(professionalsTeam);
 * </pre>
 *
 * <h2>Inherited Features:</h2>
 * <p>Employee inherits all functionality from Participant including:
 * <ul>
 *   <li>Name and age management ({@link #getName()}, {@link #getAge()})</li>
 *   <li>Cloning support ({@link #clone()})</li>
 *   <li>Serialization support</li>
 *   <li>Natural ordering by name ({@link #compareTo(Participant)})</li>
 *   <li>Equality and hashing ({@link #equals(Object)}, {@link #hashCode()})</li>
 * </ul>
 * </p>
 *
 * <h2>Differences from Other Participant Types:</h2>
 * <p>
 * While Schoolar represents school-age students and Student represents university
 * students, Employee represents working professionals with their own experience
 * level and professional characteristics.
 * </p>
 *
 * @author ZTU Student
 * @version 1.0
 * @since 2024
 *
 * @see Participant
 * @see Schoolar
 * @see Student
 * @see Team
 * @see Game
 */
public class Employee extends Participant {

    /**
     * Serial version UID for serialization.
     * <p>This unique identifier ensures that Employee objects can be safely
     * serialized and deserialized across different versions of the class.</p>
     */
    private static final long serialVersionUID = 3333333333333333333L;

    /**
     * Constructs a new Employee participant with the specified name and age.
     *
     * <p>This constructor delegates to the parent {@link Participant} class
     * constructor, which initializes the name, age, and temporary information.</p>
     *
     * @param name the name of the employee (e.g., "Andriy", "Oksana")
     * @param age the age of the employee in years (typically 25+)
     *
     * @throws NullPointerException if name is null
     *
     * @see Participant#Participant(String, int)
     */
    public Employee(String name, int age) {
        super(name, age);
    }

    /**
     * Returns a string representation of this Employee participant.
     *
     * <p>The string includes the employee's name, age, and temporary information.
     * Format: {@code Employee{name='...', age=..., tempInfo='...'}}</p>
     *
     * <p>This method overrides the parent {@link Participant#toString()} to provide
     * an Employee-specific string representation.</p>
     *
     * @return a string representation containing {@code Employee{name, age, tempInfo}}
     */
    @Override
    public String toString() {
        return "Employee{" +
                "name='" + getName() + '\'' +
                ", age=" + getAge() +
                ", tempInfo='" + getTempInfo() + '\'' +
                '}';
    }
}
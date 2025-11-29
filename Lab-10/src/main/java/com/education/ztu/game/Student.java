package com.education.ztu.game;

/**
 * Represents a university/college student participant in the game system.
 *
 * <p>This class extends the abstract {@link Participant} class and specializes it for
 * university and college students, typically ages 18-25. Student participants form
 * their own teams and compete with other student teams.</p>
 *
 * <h2>Characteristics:</h2>
 * <ul>
 *   <li><b>Age Range:</b> Typically 18-25 years old</li>
 *   <li><b>Team Composition:</b> Teams can only contain other Student participants</li>
 *   <li><b>Serialization:</b> Fully serializable with custom serialVersionUID</li>
 * </ul>
 *
 * <h2>Type Safety:</h2>
 * <p>Through Java Generics in the {@link Team} class, a {@code Team<Student>} can only
 * contain Student participants. This prevents mixing different participant types in
 * the same team and is enforced at compile time. For example, you cannot add a Schoolar
 * or Employee to a Student team.</p>
 *
 * <h2>Usage Example:</h2>
 * <pre>
 * // Create individual student participants
 * Student mykola = new Student("Mykola", 20);
 * Student viktoria = new Student("Viktoria", 21);
 *
 * // Create a team for students
 * Team&lt;Student&gt; vperedTeam = new Team&lt;&gt;("Vpered");
 *
 * // Add students to the team
 * vperedTeam.addNewParticipant(mykola);
 * vperedTeam.addNewParticipant(viktoria);
 *
 * // Create another team and play a game
 * Team&lt;Student&gt; intellectTeam = new Team&lt;&gt;("Intellect");
 * vperedTeam.playWith(intellectTeam);
 * </pre>
 *
 * <h2>Inherited Features:</h2>
 * <p>Student inherits all functionality from Participant including:
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
 * While Schoolar represents younger students and Employee represents working professionals,
 * Student represents university/college students, a distinct group with their own
 * developmental characteristics and competing interests.
 * </p>
 *
 * @author ZTU Student
 * @version 1.0
 * @since 2024
 *
 * @see Participant
 * @see Schoolar
 * @see Employee
 * @see Team
 * @see Game
 */
public class Student extends Participant {

    /**
     * Serial version UID for serialization.
     * <p>This unique identifier ensures that Student objects can be safely
     * serialized and deserialized across different versions of the class.</p>
     */
    private static final long serialVersionUID = 2222222222222222222L;

    /**
     * Constructs a new Student participant with the specified name and age.
     *
     * <p>This constructor delegates to the parent {@link Participant} class
     * constructor, which initializes the name, age, and temporary information.</p>
     *
     * @param name the name of the student (e.g., "Mykola", "Viktoria")
     * @param age the age of the student in years (typically 18-25)
     *
     * @throws NullPointerException if name is null
     *
     * @see Participant#Participant(String, int)
     */
    public Student(String name, int age) {
        super(name, age);
    }

    /**
     * Returns a string representation of this Student participant.
     *
     * <p>The string includes the student's name, age, and temporary information.
     * Format: {@code Student{name='...', age=..., tempInfo='...'}}</p>
     *
     * <p>This method overrides the parent {@link Participant#toString()} to provide
     * a Student-specific string representation.</p>
     *
     * @return a string representation containing {@code Student{name, age, tempInfo}}
     */
    @Override
    public String toString() {
        return "Student{" +
                "name='" + getName() + '\'' +
                ", age=" + getAge() +
                ", tempInfo='" + getTempInfo() + '\'' +
                '}';
    }
}
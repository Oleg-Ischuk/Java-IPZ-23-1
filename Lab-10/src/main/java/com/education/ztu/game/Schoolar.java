package com.education.ztu.game;

/**
 * Represents a school-age student participant in the game system.
 *
 * <p>This class extends the abstract {@link Participant} class and specializes it for
 * school-age participants, typically ages 6-17. Schoolar (School) participants form
 * teams of their own type and compete with other schoolar teams.</p>
 *
 * <h2>Characteristics:</h2>
 * <ul>
 *   <li><b>Age Range:</b> Typically 6-17 years old</li>
 *   <li><b>Team Composition:</b> Teams can only contain other Schoolar participants</li>
 *   <li><b>Serialization:</b> Fully serializable with custom serialVersionUID</li>
 * </ul>
 *
 * <h2>Type Safety:</h2>
 * <p>Through Java Generics in the {@link Team} class, a {@code Team<Schoolar>} can only
 * contain Schoolar participants. This prevents mixing different participant types in
 * the same team and is enforced at compile time.</p>
 *
 * <h2>Usage Example:</h2>
 * <pre>
 * // Create individual schoolar participants
 * Schoolar ivan = new Schoolar("Ivan", 13);
 * Schoolar mariya = new Schoolar("Mariya", 15);
 *
 * // Create a team for schoolars
 * Team&lt;Schoolar&gt; dragonTeam = new Team&lt;&gt;("Dragon");
 *
 * // Add schoolars to the team
 * dragonTeam.addNewParticipant(ivan);
 * dragonTeam.addNewParticipant(mariya);
 *
 * // Create another team and play
 * Team&lt;Schoolar&gt; wizardTeam = new Team&lt;&gt;("Wizards");
 * dragonTeam.playWith(wizardTeam);
 * </pre>
 *
 * <h2>Inherited Features:</h2>
 * <p>Schoolar inherits all functionality from Participant including:
 * <ul>
 *   <li>Name and age management ({@link #getName()}, {@link #getAge()})</li>
 *   <li>Cloning support ({@link #clone()})</li>
 *   <li>Serialization support</li>
 *   <li>Natural ordering by name ({@link #compareTo(Participant)})</li>
 *   <li>Equality and hashing ({@link #equals(Object)}, {@link #hashCode()})</li>
 * </ul>
 * </p>
 *
 * @author ZTU Student
 * @version 1.0
 * @since 2024
 *
 * @see Participant
 * @see Student
 * @see Employee
 * @see Team
 * @see Game
 */
public class Schoolar extends Participant {

    /**
     * Serial version UID for serialization.
     * <p>This unique identifier ensures that Schoolar objects can be safely
     * serialized and deserialized across different versions of the class.</p>
     */
    private static final long serialVersionUID = 1111111111111111111L;

    /**
     * Constructs a new Schoolar participant with the specified name and age.
     *
     * <p>This constructor delegates to the parent {@link Participant} class
     * constructor, which initializes the name, age, and temporary information.</p>
     *
     * @param name the name of the schoolar (e.g., "Ivan", "Mariya")
     * @param age the age of the schoolar in years (typically 6-17)
     *
     * @throws NullPointerException if name is null
     *
     * @see Participant#Participant(String, int)
     */
    public Schoolar(String name, int age) {
        super(name, age);
    }

    /**
     * Returns a string representation of this Schoolar participant.
     *
     * <p>The string includes the schoolar's name, age, and temporary information.
     * Format: {@code Schoolar{name='...', age=..., tempInfo='...'}}</p>
     *
     * <p>This method overrides the parent {@link Participant#toString()} to provide
     * a Schoolar-specific string representation.</p>
     *
     * @return a string representation containing {@code Schoolar{name, age, tempInfo}}
     */
    @Override
    public String toString() {
        return "Schoolar{" +
                "name='" + getName() + '\'' +
                ", age=" + getAge() +
                ", tempInfo='" + getTempInfo() + '\'' +
                '}';
    }
}
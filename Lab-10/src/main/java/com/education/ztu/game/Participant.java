package com.education.ztu.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Objects;

/**
 * Abstract base class representing a participant in the game system.
 *
 * <p>This class serves as the foundation for all participant types in the game,
 * implementing essential interfaces for cloning, comparison, and serialization.
 * All participant types (Schoolar, Student, Employee) must extend this class.</p>
 *
 * <h2>Key Features:</h2>
 * <ul>
 *   <li><b>Serializable:</b> Participants can be serialized to files for persistence</li>
 *   <li><b>Cloneable:</b> Deep copy support for creating participant duplicates</li>
 *   <li><b>Comparable:</b> Participants can be sorted alphabetically by name</li>
 *   <li><b>Logging:</b> Full SLF4J logging support for debugging</li>
 * </ul>
 *
 * <h2>Field Details:</h2>
 * <ul>
 *   <li><b>name:</b> Participant's full name</li>
 *   <li><b>age:</b> Participant's age (must be non-negative)</li>
 *   <li><b>tempInfo:</b> Transient field that holds temporary data (not serialized)</li>
 * </ul>
 *
 * <h2>Usage Example:</h2>
 * <pre>
 * // Note: Cannot instantiate abstract class directly
 * Student student = new Student("John Doe", 20);
 * String name = student.getName();
 * int age = student.getAge();
 * student.setAge(21);
 *
 * // Cloning
 * Student clone = (Student) student.clone();
 *
 * // Comparison
 * int comparison = student.compareTo(new Student("Jane", 19));
 * </pre>
 *
 * @author ZTU Student
 * @version 1.0
 * @since 2024
 * @see Schoolar
 * @see Student
 * @see Employee
 * @see Team
 */
public abstract class Participant implements Cloneable, Comparable<Participant>, Serializable {

    /**
     * Serial version UID for serialization compatibility.
     * Used to maintain compatibility when serialized classes are updated.
     */
    private static final long serialVersionUID = 1234567890123456789L;

    /**
     * Logger instance for this class.
     * Used for logging participant lifecycle events and state changes.
     */
    private static final Logger logger = LoggerFactory.getLogger(Participant.class);

    /**
     * The full name of the participant.
     * This field is serialized and persists across serialization/deserialization cycles.
     */
    private String name;

    /**
     * The age of the participant in years.
     * This field is serialized and persists across serialization/deserialization cycles.
     * Must be a non-negative integer.
     */
    private int age;

    /**
     * Temporary information about the participant.
     * <p>This field is marked as {@code transient} and will NOT be serialized.
     * After deserialization, this field will be null. This is useful for storing
     * runtime-only information that should not be persisted.</p>
     */
    private transient String tempInfo;

    /**
     * Constructs a new Participant with the specified name and age.
     *
     * <p>This constructor initializes all fields and sets up temporary information.
     * The {@code tempInfo} field is automatically populated with a descriptive message.</p>
     *
     * @param name the name of the participant (cannot be null)
     * @param age the age of the participant (should be non-negative)
     * @throws NullPointerException if name is null
     *
     * @see #tempInfo
     */
    public Participant(String name, int age) {
        logger.debug("Creating participant: name={}, age={}", name, age);
        this.name = name;
        this.age = age;
        this.tempInfo = "Temporary data for " + name;
        logger.trace("Participant created successfully with tempInfo: {}", tempInfo);
    }

    /**
     * Returns the name of the participant.
     *
     * @return the participant's name as a {@code String}
     *
     * @see #setName(String)
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the age of the participant.
     *
     * @return the participant's age in years as an {@code int}
     *
     * @see #setAge(int)
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the name of the participant.
     *
     * <p>This method updates the participant's name and logs the change.</p>
     *
     * @param name the new name for the participant (should not be null)
     *
     * @see #getName()
     */
    public void setName(String name) {
        logger.debug("Changing name from '{}' to '{}'", this.name, name);
        this.name = name;
    }

    /**
     * Sets the age of the participant.
     *
     * <p>This method validates that the age is non-negative before setting it.
     * If a negative age is provided, an exception is thrown and logged.</p>
     *
     * @param age the new age for the participant in years
     * @throws IllegalArgumentException if age is negative (less than 0)
     *
     * @see #getAge()
     */
    public void setAge(int age) {
        if (age < 0) {
            logger.warn("Attempt to set negative age: {}", age);
            throw new IllegalArgumentException("Age cannot be negative");
        }
        logger.debug("Changing age from {} to {}", this.age, age);
        this.age = age;
    }

    /**
     * Returns the temporary information about the participant.
     *
     * <p><b>Important Note:</b> This field is marked as {@code transient} and will be
     * {@code null} after deserialization. This is by design - temporary data is not
     * meant to be persisted.</p>
     *
     * @return the temporary information string, or {@code null} if not set or after deserialization
     *
     * @see #setTempInfo(String)
     */
    public String getTempInfo() {
        return tempInfo;
    }

    /**
     * Sets the temporary information for the participant.
     *
     * <p>Note that this field is transient and will not be serialized.
     * This is useful for storing runtime-only information.</p>
     *
     * @param tempInfo the temporary information to set (can be null)
     *
     * @see #getTempInfo()
     */
    public void setTempInfo(String tempInfo) {
        this.tempInfo = tempInfo;
    }

    /**
     * Creates and returns a deep copy (clone) of this participant.
     *
     * <p>This method uses the Object clone mechanism to create a shallow copy of the
     * participant. Since {@code String} and {@code int} are immutable, this effectively
     * creates a deep copy.</p>
     *
     * @return a clone of this participant with identical field values
     * @throws RuntimeException if cloning is not supported by the subclass
     *
     * @see java.lang.Object#clone()
     */
    @Override
    public Participant clone() {
        try {
            logger.trace("Cloning participant: {}", name);
            Participant cloned = (Participant) super.clone();
            logger.trace("Participant cloned successfully");
            return cloned;
        } catch (CloneNotSupportedException e) {
            logger.error("Clone not supported for participant: {}", name, e);
            throw new RuntimeException("Clone not supported", e);
        }
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * <p>Two participants are considered equal if and only if:
     * <ul>
     *   <li>They are the same object in memory, OR</li>
     *   <li>They are both Participant instances with identical name AND age</li>
     * </ul>
     * Note: The {@code tempInfo} field is NOT considered in equality comparison.</p>
     *
     * @param o the reference object with which to compare
     * @return {@code true} if this object is equal to the argument; {@code false} otherwise
     *
     * @see #hashCode()
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participant that = (Participant) o;
        return age == that.age && Objects.equals(name, that.name);
    }

    /**
     * Returns a hash code value for the participant.
     *
     * <p>The hash code is calculated based on the {@code name} and {@code age} fields.
     * Participants with equal name and age will have the same hash code.</p>
     *
     * @return a hash code value for this participant
     *
     * @see #equals(Object)
     * @see java.util.Objects#hash(Object...)
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    /**
     * Returns a string representation of the participant.
     *
     * <p>The string includes the participant's name, age, and temporary info.
     * Format: {@code Participant{name='...', age=..., tempInfo='...'}}</p>
     *
     * @return a string representation containing participant's data
     */
    @Override
    public String toString() {
        return "Participant{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", tempInfo='" + tempInfo + '\'' +
                '}';
    }

    /**
     * Compares this participant with the specified participant for order.
     *
     * <p>Participants are ordered alphabetically by their names using natural
     * {@code String} comparison. This allows participants to be sorted in
     * lexicographic order.</p>
     *
     * @param other the participant to be compared (must not be null)
     * @return a negative integer if this participant's name is less than the other's;
     *         zero if the names are equal;
     *         a positive integer if this participant's name is greater than the other's
     *
     * @throws NullPointerException if the other participant is null
     *
     * @see java.lang.String#compareTo(String)
     */
    @Override
    public int compareTo(Participant other) {
        logger.trace("Comparing {} with {}", this.name, other.name);
        return this.name.compareTo(other.name);
    }
}
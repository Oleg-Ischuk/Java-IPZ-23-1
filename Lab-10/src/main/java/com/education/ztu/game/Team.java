package com.education.ztu.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * Represents a team of participants in the game system.
 *
 * <p>This generic class provides a type-safe container for participants using Java Generics.
 * A team can hold multiple participants of the same type (e.g., all Students, all Employees),
 * ensuring compile-time type safety. Teams can compete against other teams of the same type.</p>
 *
 * <h2>Generic Type Safety:</h2>
 * <p>The Team class is generic with type parameter {@code <T extends Participant>}.
 * This ensures:
 * <ul>
 *   <li>A {@code Team<Schoolar>} can only contain Schoolar participants</li>
 *   <li>A {@code Team<Student>} can only contain Student participants</li>
 *   <li>A {@code Team<Employee>} can only contain Employee participants</li>
 *   <li>These constraints are enforced at compile time, not runtime</li>
 * </ul>
 * </p>
 *
 * <h2>Key Features:</h2>
 * <ul>
 *   <li><b>Serializable:</b> Teams can be serialized and persisted</li>
 *   <li><b>Transient Fields:</b> Rating and wins are not serialized</li>
 *   <li><b>Deep Cloning:</b> Create independent copies of teams</li>
 *   <li><b>Game Simulation:</b> Teams can play against each other</li>
 * </ul>
 *
 * <h2>Transient Fields:</h2>
 * <p>The {@code rating} and {@code wins} fields are marked as {@code transient} and
 * will not be serialized. This means these game statistics are reset when a team is
 * deserialized. This is intentional for separating persistent data (participants)
 * from runtime data (statistics).</p>
 *
 * <h2>Usage Example:</h2>
 * <pre>
 * // Create participants
 * Student student1 = new Student("John", 20);
 * Student student2 = new Student("Jane", 21);
 *
 * // Create a team
 * Team&lt;Student&gt; team = new Team&lt;&gt;("Eagles");
 *
 * // Add participants to the team
 * team.addNewParticipant(student1);
 * team.addNewParticipant(student2);
 *
 * // Create another team
 * Team&lt;Student&gt; otherTeam = new Team&lt;&gt;("Hawks");
 * otherTeam.addNewParticipant(new Student("Mike", 22));
 *
 * // Teams play against each other
 * team.playWith(otherTeam);
 *
 * // Clone a team
 * Team&lt;Student&gt; teamClone = Team.deepClone(team);
 * </pre>
 *
 * <h2>Constructor Details:</h2>
 * <ul>
 *   <li><b>Team(String):</b> Creates a new team with a name</li>
 *   <li><b>Team(Team<T>):</b> Creates a deep copy of another team</li>
 * </ul>
 *
 * @param <T> the type of participants in this team, must extend {@link Participant}
 *
 * @author ZTU Student
 * @version 1.0
 * @since 2024
 *
 * @see Participant
 * @see Schoolar
 * @see Student
 * @see Employee
 * @see Game
 */
public class Team<T extends Participant> implements Serializable {

    /**
     * Serial version UID for serialization.
     * <p>This unique identifier ensures that Team objects can be safely
     * serialized and deserialized across different versions of the class.</p>
     */
    private static final long serialVersionUID = 987654321098764321L;

    /**
     * Logger instance for this class.
     * Used for logging team lifecycle events, participant additions, and game results.
     */
    private static final Logger logger = LoggerFactory.getLogger(Team.class);

    /**
     * The name of the team.
     * <p>This field is serialized and persists across serialization/deserialization cycles.
     * Examples: "Dragons", "Vpered", "Robotyagi"</p>
     */
    private String name;

    /**
     * The list of participants in this team.
     * <p>This field is serialized and persists across serialization/deserialization cycles.
     * All participants must be of type {@code T}, enforced by Java Generics.</p>
     */
    private List<T> participants = new ArrayList<>();

    /**
     * The current rating of the team.
     * <p>This field is marked as {@code transient} and will NOT be serialized.
     * Initial rating is 1000. The rating is updated through gameplay.</p>
     */
    private transient int rating;

    /**
     * The number of games won by this team.
     * <p>This field is marked as {@code transient} and will NOT be serialized.
     * Initial wins count is 0. The wins counter is incremented with each victory.</p>
     */
    private transient int wins;

    /**
     * Constructs a new Team with the specified name.
     *
     * <p>This constructor initializes the team with:
     * <ul>
     *   <li>The provided team name</li>
     *   <li>An empty list of participants</li>
     *   <li>An initial rating of 1000</li>
     *   <li>An initial wins count of 0</li>
     * </ul>
     * </p>
     *
     * @param name the name of the team (e.g., "Dragons", "Vpered")
     *
     * @see #getName()
     */
    public Team(String name) {
        logger.info("Creating team: {}", name);
        this.name = name;
        this.rating = 1000;
        this.wins = 0;
        logger.debug("Team '{}' created with initial rating: {}, wins: {}", name, rating, wins);
    }

    /**
     * Constructs a new Team as a deep copy of another team.
     *
     * <p>This copy constructor creates a completely independent team with:
     * <ul>
     *   <li>The same name as the original</li>
     *   <li>Deep copies of all participants (clones)</li>
     *   <li>The same rating as the original</li>
     *   <li>The same wins count as the original</li>
     * </ul>
     * The original team and the copy are completely independent. Modifying one
     * does not affect the other.</p>
     *
     * @param other the team to copy (must not be null)
     *
     * @throws NullPointerException if other is null
     *
     * @see #deepClone(Team)
     */
    public Team(Team<T> other) {
        logger.debug("Creating deep copy of team: {}", other.name);
        this.name = other.name;
        this.participants = new ArrayList<>();
        for (T participant : other.participants) {
            this.participants.add((T) participant.clone());
        }
        this.rating = other.rating;
        this.wins = other.wins;
        logger.trace("Team copy created with {} participants", participants.size());
    }

    /**
     * Creates a deep copy of a team using the copy constructor.
     *
     * <p>This static factory method provides an alternative way to clone a team.
     * It is equivalent to calling {@code new Team<>(original)} but is more explicit
     * about the intent to create a deep clone.</p>
     *
     * @param original the team to clone (must not be null)
     * @param <T> the type of participants in the team
     * @return a new Team with the same data but independent from the original
     *
     * @throws NullPointerException if original is null
     *
     * @see #Team(Team)
     */
    public static <T extends Participant> Team<T> deepClone(Team<T> original) {
        logger.debug("Deep cloning team using static method");
        return new Team<>(original);
    }

    /**
     * Adds a new participant to the team.
     *
     * <p>This method adds the specified participant to the team's participant list
     * and logs the action. The participant is added to the end of the list.</p>
     *
     * @param participant the participant to add (must not be null)
     * @throws IllegalArgumentException if participant is null
     *
     * @see #getParticipants()
     * @see #Team#playWith(Team)
     */
    public void addNewParticipant(T participant) {
        if (participant == null) {
            logger.error("Attempt to add null participant to team '{}'", name);
            throw new IllegalArgumentException("Participant cannot be null");
        }

        logger.info("Adding participant '{}' to team '{}'", participant.getName(), name);
        participants.add(participant);
        System.out.println("To the team " + name + " was added participant " + participant.getName());
        logger.debug("Team '{}' now has {} participants", name, participants.size());
    }

    /**
     * Simulates a game between this team and another team of the same type.
     *
     * <p>This method:
     * <ul>
     *   <li>Randomly selects a winner between the two teams</li>
     *   <li>Increments the wins counter for the winning team</li>
     *   <li>Logs the game result and winner information</li>
     *   <li>Prints the result to the console</li>
     * </ul>
     * The outcome is determined purely by chance using {@link java.util.Random}.</p>
     *
     * @param team the opposing team to play against (must not be null)
     * @throws IllegalArgumentException if team is null
     *
     * @see #getName()
     * @see #wins
     */
    public void playWith(Team<T> team) {
        if (team == null) {
            logger.error("Attempt to play with null team");
            throw new IllegalArgumentException("Team cannot be null");
        }

        logger.info("Game started: '{}' vs '{}'", this.name, team.name);

        String winnerName;
        Random random = new Random();
        int i = random.nextInt(2);

        if (i == 0) {
            winnerName = this.name;
            this.wins++;
            logger.debug("Team '{}' won! Total wins: {}", this.name, this.wins);
        } else {
            winnerName = team.name;
            team.wins++;
            logger.debug("Team '{}' won! Total wins: {}", team.name, team.wins);
        }

        System.out.println("The team " + winnerName + " is winner!");
        logger.info("Game finished. Winner: {}", winnerName);
    }

    /**
     * Returns the name of the team.
     *
     * @return the team's name as a {@code String}
     *
     * @see #setName(String)
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the list of all participants in the team.
     *
     * <p>The returned list is the actual list used internally, not a copy.
     * Modifications to the returned list will affect the team.</p>
     *
     * @return a {@code List} of participants of type {@code T}
     *
     * @see #setParticipants(List)
     * @see #addNewParticipant(Participant)
     */
    public List<T> getParticipants() {
        return participants;
    }

    /**
     * Sets the name of the team.
     *
     * <p>This method updates the team's name and logs the change.</p>
     *
     * @param name the new name for the team
     *
     * @see #getName()
     */
    public void setName(String name) {
        logger.debug("Renaming team from '{}' to '{}'", this.name, name);
        this.name = name;
    }

    /**
     * Sets the list of participants for the team.
     *
     * <p>This method replaces the entire participants list with a new one.
     * If the provided list is null, it creates an empty list instead.</p>
     *
     * @param participants the new list of participants (can be null, which becomes empty list)
     *
     * @see #getParticipants()
     */
    public void setParticipants(List<T> participants) {
        if (participants == null) {
            logger.warn("Attempt to set null participants list");
            this.participants = new ArrayList<>();
        } else {
            logger.debug("Setting participants list with {} members", participants.size());
            this.participants = participants;
        }
    }

    /**
     * Returns a string representation of the team.
     *
     * <p>The string includes the team's name and the list of all participants.
     * Format: {@code Team{name='...', participants=[...]}}</p>
     *
     * @return a string representation containing team name and participants
     */
    @Override
    public String toString() {
        return "Team{" +
                "name='" + name + '\'' +
                ", participants=" + participants +
                '}';
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * <p>Two teams are considered equal if and only if:
     * <ul>
     *   <li>They are the same object in memory, OR</li>
     *   <li>They are both Team instances with identical name AND identical participants list</li>
     * </ul>
     * Note: The {@code rating} and {@code wins} fields are NOT considered in equality comparison.</p>
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
        Team<?> team = (Team<?>) o;
        return Objects.equals(name, team.name) && Objects.equals(participants, team.participants);
    }

    /**
     * Returns a hash code value for the team.
     *
     * <p>The hash code is calculated based on the {@code name} and {@code participants} fields.
     * Teams with equal name and identical participants will have the same hash code.</p>
     *
     * @return a hash code value for this team
     *
     * @see #equals(Object)
     * @see java.util.Objects#hash(Object...)
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, participants);
    }
}
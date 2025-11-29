package com.education.ztu.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Utility class for file-based JSON serialization and deserialization.
 *
 * <p>This class provides convenient methods to persist Java objects to JSON files
 * and load them back from disk. It serves as a high-level wrapper around the
 * {@link JsonParser} class, handling file I/O operations automatically.</p>
 *
 * <h2>Key Features:</h2>
 * <ul>
 *   <li><b>Object to File:</b> Serialize objects to JSON files</li>
 *   <li><b>File to Object:</b> Deserialize JSON files to typed Java objects</li>
 *   <li><b>Automatic Conversion:</b> Uses {@link JsonParser} for JSON conversion</li>
 *   <li><b>Error Handling:</b> Comprehensive exception handling and logging</li>
 *   <li><b>File I/O:</b> Uses modern NIO methods (Files class) for efficiency</li>
 * </ul>
 *
 * <h2>Supported Types:</h2>
 * <p>Any type supported by {@link JsonParser} can be saved and loaded:
 * <ul>
 *   <li>All Participant types: Schoolar, Student, Employee</li>
 *   <li>Team classes with generic type parameters</li>
 *   <li>Custom objects with Gson support</li>
 * </ul>
 * </p>
 *
 * <h2>Usage Examples:</h2>
 * <pre>
 * // Save a student to a JSON file
 * Student student = new Student("Alice", 20);
 * JsonFileOperations.saveToJsonFile(student, "student.json");
 * // Creates file with JSON content:
 * // {
 * //   "name": "Alice",
 * //   "age": 20,
 * //   "tempInfo": "Temporary data for Alice"
 * // }
 *
 * // Load student back from file
 * Student loadedStudent = JsonFileOperations.loadFromJsonFile(
 *     "student.json", Student.class
 * );
 * System.out.println(loadedStudent.getName()); // Output: Alice
 *
 * // Save a team to a file
 * Team<Student> team = new Team<>("Eagles");
 * team.addNewParticipant(new Student("Bob", 21));
 * JsonFileOperations.saveToJsonFile(team, "team.json");
 *
 * // Load team back from file
 * Team<Student> loadedTeam = JsonFileOperations.loadFromJsonFile(
 *     "team.json", Team.class
 * );
 * System.out.println(loadedTeam.getName()); // Output: Eagles
 * </pre>
 *
 * <h2>File Encoding:</h2>
 * <p>All files are read and written using UTF-8 encoding by default.</p>
 *
 * <h2>Error Handling:</h2>
 * <p>Both methods wrap {@link IOException} in {@link RuntimeException} to provide
 * an unchecked exception that can be handled at a higher level. All errors are
 * logged with full details before being thrown.</p>
 *
 * <h2>Performance Notes:</h2>
 * <ul>
 *   <li>Uses {@link java.nio.file.Files} for efficient file I/O</li>
 *   <li>Suitable for small to medium-sized files</li>
 *   <li>For very large files, consider streaming approaches</li>
 * </ul>
 *
 * @author ZTU Student
 * @version 1.0
 * @since 2024
 *
 * @see JsonParser
 * @see Participant
 * @see Team
 * @see java.nio.file.Files
 */
public class JsonFileOperations {

    /**
     * Logger instance for this class.
     * Used for logging file save/load operations and errors.
     */
    private static final Logger logger = LoggerFactory.getLogger(JsonFileOperations.class);

    /**
     * Saves an object as JSON to a file on disk.
     *
     * <p>This method:
     * <ul>
     *   <li>Converts the object to JSON using {@link JsonParser#toJson(Object)}</li>
     *   <li>Writes the JSON string to a file at the specified path</li>
     *   <li>Creates the file if it doesn't exist</li>
     *   <li>Overwrites the file if it already exists</li>
     *   <li>Logs the operation at info and debug levels</li>
     * </ul>
     * </p>
     *
     * <h2>Example:</h2>
     * <pre>
     * Student student = new Student("Charlie", 22);
     * JsonFileOperations.saveToJsonFile(student, "data/students/charlie.json");
     * </pre>
     *
     * <h2>Exception Behavior:</h2>
     * <p>If the save operation fails (e.g., file permissions, parent directory doesn't exist),
     * an error is logged and a {@link RuntimeException} is thrown.</p>
     *
     * @param object the object to save to a JSON file (must not be null)
     * @param filePath the file path where the JSON will be saved (must not be null)
     *                 Can be relative (e.g., "file.json") or absolute path
     * @throws RuntimeException if an I/O error occurs during file writing
     * @throws NullPointerException if object or filePath is null
     *
     * @see #loadFromJsonFile(String, Class)
     * @see JsonParser#toJson(Object)
     * @see java.nio.file.Files#write(java.nio.file.Path, byte[])
     */
    public static void saveToJsonFile(Object object, String filePath) {
        logger.info("Saving object to JSON file: {}", filePath);
        try {
            String json = JsonParser.toJson(object);

            Files.write(Paths.get(filePath), json.getBytes());

            logger.info("Object successfully saved to {}", filePath);
            System.out.println("Saved to: " + filePath);
        } catch (IOException e) {
            logger.error("Error saving to file: {}", filePath, e);
            throw new RuntimeException("Failed to save JSON to file", e);
        }
    }

    /**
     * Loads an object from a JSON file on disk.
     *
     * <p>This method:
     * <ul>
     *   <li>Reads the JSON content from the specified file</li>
     *   <li>Deserializes it to the specified class using {@link JsonParser#fromJson(String, Class)}</li>
     *   <li>Returns the deserialized object</li>
     *   <li>Logs the operation at info and debug levels</li>
     * </ul>
     * </p>
     *
     * <h2>Example:</h2>
     * <pre>
     * Student loadedStudent = JsonFileOperations.loadFromJsonFile(
     *     "data/students/charlie.json", Student.class
     * );
     * System.out.println(loadedStudent.getName());
     * </pre>
     *
     * <h2>Type Parameter:</h2>
     * <p>The {@code clazz} parameter determines the type of object returned.
     * This must match the JSON structure in the file for proper deserialization.</p>
     *
     * <h2>Exception Behavior:</h2>
     * <p>If the load operation fails (e.g., file not found, invalid JSON),
     * an error is logged and a {@link RuntimeException} is thrown.</p>
     *
     * @param filePath the file path to load from (must not be null)
     *                 Can be relative (e.g., "file.json") or absolute path
     * @param clazz the {@link Class} to deserialize the JSON into
     * @param <T> the type parameter matching the clazz parameter
     * @return a new instance of class {@code T} populated from the file's JSON data
     * @throws RuntimeException if an I/O error occurs during file reading
     *                          or if JSON deserialization fails
     * @throws NullPointerException if filePath or clazz is null
     *
     * @see #saveToJsonFile(Object, String)
     * @see JsonParser#fromJson(String, Class)
     * @see java.nio.file.Files#readString(java.nio.file.Path)
     */
    public static <T> T loadFromJsonFile(String filePath, Class<T> clazz) {
        logger.info("Loading object from JSON file: {}", filePath);
        try {
            String json = Files.readString(Paths.get(filePath));

            T object = JsonParser.fromJson(json, clazz);

            logger.info("Object successfully loaded from {}", filePath);
            return object;
        } catch (IOException e) {
            logger.error("Error loading from file: {}", filePath, e);
            throw new RuntimeException("Failed to load JSON from file", e);
        }
    }
}
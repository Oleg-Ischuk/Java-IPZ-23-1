package com.education.ztu.game;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class for JSON parsing and serialization using Google's Gson library.
 *
 * <p>This class provides convenient methods to convert Java objects to JSON format
 * and deserialize JSON strings back to Java objects. It uses Gson with pretty-printing
 * enabled for human-readable JSON output.</p>
 *
 * <h2>Key Features:</h2>
 * <ul>
 *   <li><b>Object to JSON:</b> Convert any Java object to formatted JSON string</li>
 *   <li><b>JSON to Object:</b> Deserialize JSON strings back to typed Java objects</li>
 *   <li><b>Pretty Printing:</b> JSON output is formatted for readability</li>
 *   <li><b>Logging:</b> Full SLF4J logging support for debugging</li>
 *   <li><b>Error Handling:</b> Comprehensive exception handling and logging</li>
 * </ul>
 *
 * <h2>Supported Types:</h2>
 * <p>This utility can serialize/deserialize:
 * <ul>
 *   <li>All Participant types: Schoolar, Student, Employee</li>
 *   <li>Team classes with generic type parameters</li>
 *   <li>Custom objects with Gson support</li>
 *   <li>Collections and arrays of supported types</li>
 * </ul>
 * </p>
 *
 * <h2>Usage Examples:</h2>
 * <pre>
 * // Convert object to JSON
 * Student student = new Student("John", 20);
 * String json = JsonParser.toJson(student);
 * System.out.println(json);
 * // Output:
 * // {
 * //   "name": "John",
 * //   "age": 20,
 * //   "tempInfo": "Temporary data for John"
 * // }
 *
 * // Convert JSON back to object
 * String jsonString = "{\"name\":\"Jane\",\"age\":21,\"tempInfo\":\"Temp\"}";
 * Student deserializedStudent = JsonParser.fromJson(jsonString, Student.class);
 * System.out.println(deserializedStudent.getName()); // Output: Jane
 *
 * // Convert team to JSON
 * Team<Student> team = new Team<>("Eagles");
 * team.addNewParticipant(student);
 * String teamJson = JsonParser.toJson(team);
 *
 * // Convert back to team
 * Team<Student> teamDeserialized = JsonParser.fromJson(teamJson, Team.class);
 * </pre>
 *
 * <h2>Exception Handling:</h2>
 * <p>All methods wrap checked exceptions in {@link RuntimeException} and log errors.
 * This simplifies error handling for the caller while maintaining detailed logging.</p>
 *
 * <h2>Implementation Notes:</h2>
 * <p>
 * <ul>
 *   <li>Gson instance is created once and reused for performance</li>
 *   <li>Pretty printing is enabled for better readability</li>
 *   <li>All conversions are logged at debug level (with class names)</li>
 *   <li>Successful conversions are logged at trace level</li>
 *   <li>Errors are logged at error level with full exception details</li>
 * </ul>
 * </p>
 *
 * @author ZTU Student
 * @version 1.0
 * @since 2024
 *
 * @see com.google.gson.Gson
 * @see Participant
 * @see Team
 * @see JsonFileOperations
 */
public class JsonParser {

    /**
     * Logger instance for this class.
     * Used for logging JSON conversion operations and errors.
     */
    private static final Logger logger = LoggerFactory.getLogger(JsonParser.class);

    /**
     * Gson instance configured with pretty printing.
     * <p>This is a class-level constant that is created once and reused
     * for all JSON operations. Pretty printing is enabled to produce
     * human-readable JSON output.</p>
     */
    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    /**
     * Converts a Java object to a JSON string with pretty printing.
     *
     * <p>This method serializes any object to JSON format. The resulting JSON is
     * formatted with indentation for readability. All fields visible to Gson
     * are included in the output.</p>
     *
     * <h2>Example:</h2>
     * <pre>
     * Student student = new Student("Alice", 22);
     * String json = JsonParser.toJson(student);
     * // json contains formatted JSON representation
     * </pre>
     *
     * @param object the object to convert to JSON (must not be null)
     * @return a formatted JSON string representation of the object
     * @throws RuntimeException if JSON conversion fails (with underlying exception logged)
     * @throws NullPointerException if object is null (from Gson)
     *
     * @see #fromJson(String, Class)
     * @see com.google.gson.Gson#toJson(Object)
     */
    public static String toJson(Object object) {
        logger.debug("Converting object to JSON: {}", object.getClass().getSimpleName());
        try {
            String json = gson.toJson(object);
            logger.trace("JSON conversion successful");
            return json;
        } catch (Exception e) {
            logger.error("Error converting object to JSON", e);
            throw new RuntimeException("JSON conversion failed", e);
        }
    }

    /**
     * Converts a JSON string to a typed Java object.
     *
     * <p>This method deserializes JSON data into a Java object of the specified class.
     * The JSON structure should match the target class structure for successful
     * deserialization.</p>
     *
     * <h2>Example:</h2>
     * <pre>
     * String json = "{\"name\":\"Bob\",\"age\":23}";
     * Student student = JsonParser.fromJson(json, Student.class);
     * System.out.println(student.getName()); // Output: Bob
     * </pre>
     *
     * <h2>Important Notes:</h2>
     * <ul>
     *   <li>The target class should have appropriate constructors (Gson uses reflection)</li>
     *   <li>JSON field names should match class field names</li>
     *   <li>Missing fields in JSON will use default values</li>
     *   <li>Extra fields in JSON will be ignored</li>
     * </ul>
     *
     * @param json the JSON string to deserialize (must be valid JSON)
     * @param clazz the {@link Class} to deserialize into
     * @param <T> the type parameter matching the clazz parameter
     * @return a new instance of class {@code T} populated from the JSON data
     * @throws RuntimeException if JSON deserialization fails (with underlying exception logged)
     * @throws NullPointerException if json or clazz is null
     *
     * @see #toJson(Object)
     * @see com.google.gson.Gson#fromJson(String, Class)
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        logger.debug("Converting JSON to object of type: {}", clazz.getSimpleName());
        try {
            T object = gson.fromJson(json, clazz);
            logger.trace("JSON deserialization successful");
            return object;
        } catch (Exception e) {
            logger.error("Error converting JSON to object", e);
            throw new RuntimeException("JSON deserialization failed", e);
        }
    }
}
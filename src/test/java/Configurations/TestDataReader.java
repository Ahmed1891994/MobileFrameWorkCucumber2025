package Configurations;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestDataReader {
    private static final Logger logger = LoggerFactory.getLogger(TestDataReader.class);
    private static final Gson gson = new Gson();
    private static final ThreadLocal<Map<String, Object>> testDataCache = ThreadLocal.withInitial(HashMap::new);

    /**
     * Reads test data from a JSON file and deserializes it into the specified class.
     *
     * @param fileName The name of the JSON file (without extension).
     * @param clazz    The class to deserialize the JSON into.
     * @param <T>      The type of the test data.
     * @return The deserialized test data.
     */
    public static <T> T readTestData(String fileName, Class<T> clazz) {
        return readTestData(fileName, clazz, null);
    }

    /**
     * Reads test data from a JSON file and deserializes it into the specified class.
     * If a specific key is provided, it extracts the data under that key from a JSON map.
     *
     * @param fileName    The name of the JSON file (without extension).
     * @param clazz       The class to deserialize the JSON into.
     * @param specificKey The key to extract specific data from the JSON map (optional).
     * @param <T>         The type of the test data.
     * @return The deserialized test data.
     */
    public static <T> T readTestData(String fileName, Class<T> clazz, String specificKey) {
        // Check if already cached
        if (!testDataCache.get().containsKey(fileName)) {
            Path filePath = Paths.get(System.getProperty("user.dir"), "src", "test", "resources", "TestData", fileName + ".json");
            logger.debug("Reading test data from file: {}", filePath);

            try (Stream<String> stream = Files.lines(filePath)) {
                String source = stream.collect(Collectors.joining(System.lineSeparator()));
                logger.debug("Successfully read JSON content from file: {}", filePath);

                // For generic JSON that might be a map or specific class
                if (specificKey != null) {
                    logger.debug("Extracting data under specific key: {}", specificKey);
                    Map<String, T> dataMap = gson.fromJson(source, new TypeToken<Map<String, T>>() {}.getType());
                    T data = dataMap.get(specificKey);
                    logger.debug("Data extracted for key '{}': {}", specificKey, data);
                    return data;
                } else {
                    logger.debug("Deserializing JSON content into class: {}", clazz.getSimpleName());
                    T data = gson.fromJson(source, clazz);
                    logger.debug("Caching test data for file: {}", fileName);
                    testDataCache.get().put(fileName, data);
                    return data;
                }
            } catch (IOException e) {
                logger.error("Failed to read test data file: {}", filePath, e);
                throw new RuntimeException("Failed to read test data file: " + fileName, e);
            }
        }

        logger.debug("Retrieving cached test data for file: {}", fileName);
        return (T) testDataCache.get().get(fileName);
    }

    /**
     * Reads all JSON files in the TestData directory and deserializes them into their respective classes.
     *
     * @return A map of file names to deserialized test data objects.
     */
    public static Map<String, Object> readAllTestData() {
        Map<String, Object> allTestData = new HashMap<>();
        Path testDataDir = Paths.get(System.getProperty("user.dir"), "src", "test", "resources", "TestData");
        logger.debug("Reading all test data files from directory: {}", testDataDir);

        try (Stream<Path> pathStream = Files.walk(testDataDir)) {
            pathStream
                    .filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".json"))
                    .forEach(path -> {
                        try {
                            String fileName = path.getFileName().toString().replace(".json", "");
                            logger.debug("Processing test data file: {}", fileName);

                            // Dynamically get the class based on the file name
                            Class<?> clazz = Class.forName("Data." + fileName); // Adjust the package name as needed
                            logger.debug("Deserializing JSON content into class: {}", clazz.getSimpleName());

                            // Read the JSON content
                            String content = Files.readString(path);
                            logger.debug("Successfully read JSON content from file: {}", path);

                            // Deserialize the JSON into an instance of the class
                            Object data = gson.fromJson(content, clazz);
                            logger.debug("Adding test data for file '{}' to map", fileName);

                            // Store the deserialized object in the map
                            allTestData.put(fileName, data);
                        } catch (IOException | ClassNotFoundException e) {
                            logger.error("Failed to read or parse test data file: {}", path, e);
                            throw new RuntimeException("Failed to read or parse test data file: " + path, e);
                        }
                    });
        } catch (IOException e) {
            logger.error("Failed to read test data directory: {}", testDataDir, e);
            throw new RuntimeException("Failed to read test data directory", e);
        }

        logger.info("Successfully loaded test data from all files in directory: {}", testDataDir);
        return allTestData;
    }

    /**
     * Clears the test data cache for the current thread.
     */
    public static void clearTestDataCache() {
        logger.debug("Clearing test data cache for the current thread");
        testDataCache.get().clear();
        testDataCache.remove();
        logger.info("Test data cache cleared successfully");
    }
}
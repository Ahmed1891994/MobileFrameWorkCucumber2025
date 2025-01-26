package Context;

import Configurations.Configuration;
import Configurations.TestDataReader;
import Driver.Driver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class Context {
    private static final Logger logger = LoggerFactory.getLogger(Context.class);

    // ThreadLocal for driver
    private static final ThreadLocal<Driver> driver = new ThreadLocal<>();

    // ThreadLocal for configuration
    private static final ThreadLocal<Configuration> configuration = new ThreadLocal<>();

    // ThreadLocal for test data
    private static final ThreadLocal<Map<String, Object>> testData = ThreadLocal.withInitial(HashMap::new);

    /**
     * Gets the current platform from system properties.
     *
     * @return The platform name (e.g., "android", "ios").
     */
    public String getPlatform() {
        String platform = System.getProperty("platform");
        logger.debug("Retrieved platform: {}", platform);
        return platform;
    }

    /**
     * Gets the driver for the current thread.
     *
     * @return The driver instance.
     */
    public Driver getDriver() {
        Driver currentDriver = driver.get();
        logger.debug("Retrieved driver for current thread: {}", currentDriver);
        return currentDriver;
    }

    /**
     * Sets the driver for the current thread.
     *
     * @param driver The driver instance to set.
     */
    public void setDriver(Driver driver) {
        logger.debug("Setting driver for current thread: {}", driver);
        Context.driver.set(driver);
    }

    /**
     * Removes the driver for the current thread.
     */
    public void removeDriver() {
        logger.debug("Removing driver for current thread");
        driver.remove();
    }

    /**
     * Gets the configuration for the current thread.
     *
     * @return The configuration instance.
     */
    public Configuration getConfiguration() {
        Configuration currentConfig = configuration.get();
        logger.debug("Retrieved configuration for current thread: {}", currentConfig);
        return currentConfig;
    }

    /**
     * Sets the configuration for the current thread.
     *
     * @param configuration The configuration instance to set.
     */
    public void setConfiguration(Configuration configuration) {
        logger.debug("Setting configuration for current thread: {}", configuration);
        Context.configuration.set(configuration);
    }

    /**
     * Removes the configuration for the current thread.
     */
    public void removeConfiguration() {
        logger.debug("Removing configuration for current thread");
        configuration.remove();
    }

    /**
     * Gets test data for a specific key.
     *
     * @param key The key for the test data.
     * @return The test data associated with the key.
     */
    public Object getTestData(String key) {
        Object data = testData.get().get(key);
        logger.debug("Retrieved test data for key '{}': {}", key, data);
        return data;
    }

    /**
     * Sets test data for a specific key.
     *
     * @param key   The key for the test data.
     * @param value The value to associate with the key.
     */
    public void setTestData(String key, Object value) {
        logger.debug("Setting test data for key '{}': {}", key, value);
        Map<String, Object> newData = new HashMap<>(testData.get()); // Copy existing data
        newData.put(key, value); // Add new data
        testData.set(newData); // Replace the entire Map in ThreadLocal
    }

    /**
     * Loads test data from a JSON file and stores it in the context.
     *
     * @param fileName The name of the JSON file.
     * @param clazz    The class type to deserialize the JSON into.
     * @param <T>      The type of the test data.
     */
    public <T> void loadTestData(String fileName, Class<T> clazz) {
        logger.debug("Loading test data from file: {}", fileName);
        T data = TestDataReader.readTestData(fileName, clazz);
        setTestData(fileName, data); // Use setTestData to ensure ThreadLocal is updated
    }

    /**
     * Loads specific test data from a JSON file using a key and stores it in the context.
     *
     * @param fileName    The name of the JSON file.
     * @param clazz       The class type to deserialize the JSON into.
     * @param specificKey The key to retrieve specific data from the JSON file.
     * @param <T>         The type of the test data.
     */
    public <T> void loadTestData(String fileName, Class<T> clazz, String specificKey) {
        logger.debug("Loading specific test data from file '{}' with key '{}'", fileName, specificKey);
        T data = TestDataReader.readTestData(fileName, clazz, specificKey);
        setTestData(specificKey, data); // Use setTestData to ensure ThreadLocal is updated
    }

    /**
     * Loads all test data from JSON files in the TestData folder and stores it in the context.
     */
    public void setAllTestData() {
        logger.debug("Loading all test data from TestData folder");
        Map<String, Object> allData = TestDataReader.readAllTestData();
        testData.set(allData); // Replace the entire Map in ThreadLocal
    }

    /**
     * Clears test data for the current thread.
     */
    public void clearTestData() {
        logger.debug("Clearing test data for current thread");
        testData.set(new HashMap<>()); // Clear the Map by replacing it with a new one
        testData.remove(); // Remove the ThreadLocal value for the current thread
    }
}
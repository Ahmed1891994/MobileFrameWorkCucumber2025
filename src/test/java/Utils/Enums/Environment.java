package Utils.Enums;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum Environment {
    LOCAL,
    BROWSERSTACK;

    private static final Logger logger = LoggerFactory.getLogger(Environment.class);

    /**
     * Retrieves the environment from the system property "environment".
     * Defaults to LOCAL if the property is null, empty, or invalid.
     *
     * @return The Environment enum value.
     */
    public static Environment getEnvironment() {
        String env = System.getProperty("environment");
        if (env == null || env.trim().isEmpty()) {
            logger.info("Environment property is not set. Defaulting to LOCAL.");
            return Environment.LOCAL; // Default to LOCAL if the property is null or empty
        }

        try {
            Environment environment = Environment.valueOf(env.toUpperCase());
            logger.info("Retrieved environment: {}", environment);
            return environment;
        } catch (IllegalArgumentException e) {
            logger.warn("Invalid environment value: {}. Defaulting to LOCAL.", env);
            return Environment.LOCAL; // Default to LOCAL if the value is invalid
        }
    }
}
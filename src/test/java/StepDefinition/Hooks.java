package StepDefinition;

import Configurations.Configuration;
import Context.Context;
import Driver.Driver;
import Driver.DriverFactory;
import Utils.Enums.Environment;
import Utils.Server.LocalServer;
import io.cucumber.java.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Hooks {
    private static final Logger logger = LoggerFactory.getLogger(Hooks.class);
    private static LocalServer server;
    private final Context context;
    private Driver driver;
    public Hooks(Context context) {
        this.context = context;
        logger.debug("Hooks initialized with context: {}", context);
    }

    /**
     * Initializes the local server before all scenarios if the environment is LOCAL.
     */
    @BeforeAll
    public static void beforeAll() {
        if (Environment.getEnvironment() == Environment.LOCAL) {
            logger.info("Initializing local server for LOCAL environment");
            server = new LocalServer();
            server.start();
            logger.info("Local server started successfully");
        } else {
            logger.info("Skipping local server initialization for non-LOCAL environment");
        }
    }

    /**
     * Sets up the configuration, test data, and driver before each scenario.
     */
    @Before
    public void before(Scenario scenario) {
        logger.info("Setting up configuration and test data for the scenario");
        context.setConfiguration(new Configuration().readConfigurations());
        context.setAllTestData();

        logger.debug("Initializing driver for the scenario");
        driver = DriverFactory.getDriver(context);
        context.setDriver(driver);
        logger.info("Driver initialized successfully for the scenario");
        logger.info("Starting execution of scenario: {}", scenario.getName());
    }

    /**
     * Cleans up the configuration and driver after each scenario.
     */
    @After
    public void after(Scenario scenario) {
        logger.info("Completed execution of scenario: {}", scenario.getName());
        driver = context.getDriver();
        if (driver != null) {
            if (scenario.isFailed()) {
                driver.takeScreenShot(scenario);
            }
            driver.quit();
        }

        logger.info("Cleaning up configuration and driver after the scenario");
        context.removeConfiguration();
        context.removeDriver();
        logger.info("Configuration and driver cleanup completed");
    }

    /**
     * Stops the local server after all scenarios if the environment is LOCAL.
     */
    @AfterAll
    public static void afterAll() {
        if (Environment.getEnvironment() == Environment.LOCAL) {
            logger.info("Stopping local server after all scenarios");
            server.stop();
            logger.info("Local server stopped successfully");
        } else {
            logger.info("Skipping local server cleanup for non-LOCAL environment");
        }
    }
}
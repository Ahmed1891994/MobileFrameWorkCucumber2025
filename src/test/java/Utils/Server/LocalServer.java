package Utils.Server;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class LocalServer {
    private static final Logger logger = LoggerFactory.getLogger(LocalServer.class);
    private final AppiumDriverLocalService appiumService;
    private final int port; // Port to be used for the Appium server

    public LocalServer() {
        this(4723); // Default port
    }

    public LocalServer(int port) {
        this.port = port;
        logger.debug("Initializing Appium server with port: {}", port);
        AppiumServiceBuilder serviceBuilder = new AppiumServiceBuilder()
                .withIPAddress("127.0.0.1") // Default IP Address
                .usingPort(port)            // Use the specified port
                .withArgument(GeneralServerFlag.BASEPATH, "/wd/hub")
                .withArgument(GeneralServerFlag.SESSION_OVERRIDE) // Override existing sessions
                .withArgument(GeneralServerFlag.LOG_LEVEL, "info") // Log level: info
                .withLogFile(new File("appium_server.log")); // Log Appium server output to a file
        appiumService = AppiumDriverLocalService.buildService(serviceBuilder);
        logger.info("Appium server initialized successfully");
    }

    /**
     * Starts the Appium server.
     *
     * @throws RuntimeException If the server fails to start.
     */
    public void start() {
        logger.debug("Starting Appium server on port: {}", port);

        // Free the port if it's already in use
        try {
            PortUtils.freePort(port);
        } catch (Exception e) {
            logger.warn("Failed to free port {}: {}", port, e.getMessage());
        }

        // Start the Appium server
        appiumService.start();

        // Wait for the server to become available
        boolean isServerRunning = waitForAppiumServer();
        if (!isServerRunning) {
            logger.error("Appium server failed to start within the specified timeout");
            throw new RuntimeException("Appium server failed to start within the specified timeout");
        }

        // Log the server URL
        logger.info("Appium server started successfully");
        logger.info("Server URL: {}", getServerUrl());
    }

    /**
     * Waits for the Appium server to become available.
     *
     * @return True if the server is running, false otherwise.
     */
    private boolean waitForAppiumServer() {
        long startTime = System.currentTimeMillis();
        long endTime = startTime + TimeUnit.SECONDS.toMillis(60);

        while (System.currentTimeMillis() < endTime) {
            if (appiumService.isRunning()) {
                logger.debug("Appium server is running");
                return true;
            }
            try {
                Thread.sleep(1000); // Wait for 1 second before checking again
            } catch (InterruptedException e) {
                logger.warn("Thread interrupted while waiting for Appium server to start");
                Thread.currentThread().interrupt();
                return false;
            }
        }

        logger.warn("Appium server did not start within the specified timeout");
        return false;
    }

    /**
     * Stops the Appium server if it is running.
     */
    public void stop() {
        logger.debug("Stopping Appium server");
        if (appiumService != null) {
            appiumService.stop();
            logger.info("Appium server stopped successfully");
        } else {
            logger.warn("Appium server is not running");
        }
    }

    /**
     * Retrieves the URL of the running Appium server.
     *
     * @return The server URL as a string.
     * @throws IllegalStateException If the server is not running.
     */
    public String getServerUrl() {
        if (appiumService != null) {
            String serverUrl = appiumService.getUrl().toString();
            logger.debug("Retrieved Appium server URL: {}", serverUrl);
            return serverUrl;
        } else {
            logger.error("Appium server is not running");
            throw new IllegalStateException("Appium server is not running");
        }
    }
}
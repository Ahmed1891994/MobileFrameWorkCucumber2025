package Utils.Server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PortUtils {

    // Initialize logger for this class
    private static final Logger logger = LoggerFactory.getLogger(PortUtils.class);

    /**
     * Frees a port by terminating the process using it.
     *
     * @param port The port number to free.
     * @throws IOException If an I/O error occurs.
     * @throws InterruptedException If the process is interrupted.
     */
    public static void freePort(int port) throws IOException, InterruptedException {
        logger.debug("Attempting to free port: {}", port);

        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            // Windows
            logger.debug("Detected Windows OS. Freeing port on Windows.");
            freePortOnWindows(port);
        } else if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
            // Linux or Mac
            logger.debug("Detected Unix-based OS. Freeing port on Unix.");
            freePortOnUnix(port);
        } else {
            logger.error("Unsupported operating system: {}", os);
            throw new UnsupportedOperationException("Unsupported operating system: " + os);
        }

        logger.info("Port {} freed successfully.", port);
    }

    /**
     * Frees a port on Windows by terminating the process using it.
     *
     * @param port The port number to free.
     * @throws IOException If an I/O error occurs.
     * @throws InterruptedException If the process is interrupted.
     */
    private static void freePortOnWindows(int port) throws IOException, InterruptedException {
        logger.debug("Finding process using port {} on Windows.", port);

        // Find the process ID (PID) using the port
        Process process = Runtime.getRuntime().exec("netstat -ano | findstr :" + port);
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        String pid = null;

        while ((line = reader.readLine()) != null) {
            if (line.contains("LISTENING")) {
                String[] parts = line.trim().split("\\s+");
                pid = parts[parts.length - 1]; // The last part is the PID
                break;
            }
        }

        if (pid != null) {
            logger.debug("Found process with PID {} using port {}. Terminating process.", pid, port);
            // Terminate the process
            Runtime.getRuntime().exec("taskkill /PID " + pid + " /F");
            logger.info("Terminated process with PID: {}", pid);
        } else {
            logger.warn("No process found using port: {}", port);
        }
    }

    /**
     * Frees a port on Unix-based systems (Linux/Mac) by terminating the process using it.
     *
     * @param port The port number to free.
     * @throws IOException If an I/O error occurs.
     * @throws InterruptedException If the process is interrupted.
     */
    private static void freePortOnUnix(int port) throws IOException, InterruptedException {
        logger.debug("Finding process using port {} on Unix.", port);

        // Find the process ID (PID) using the port
        Process process = Runtime.getRuntime().exec("lsof -t -i:" + port);
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String pid = reader.readLine();

        if (pid != null) {
            logger.debug("Found process with PID {} using port {}. Terminating process.", pid, port);
            // Terminate the process
            Runtime.getRuntime().exec("kill -9 " + pid);
            logger.info("Terminated process with PID: {}", pid);
        } else {
            logger.warn("No process found using port: {}", port);
        }
    }

    public static void main(String[] args) {
        int port = 8080; // Replace with the port you want to free
        try {
            logger.info("Attempting to free port: {}", port);
            freePort(port);
        } catch (IOException | InterruptedException e) {
            logger.error("Failed to free port {}: {}", port, e.getMessage(), e);
        }
    }
}
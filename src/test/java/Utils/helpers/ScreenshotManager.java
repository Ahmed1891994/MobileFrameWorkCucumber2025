package Utils.helpers;
import Context.Context;
import Driver.Driver;
import Utils.Enums.ScreenshotStorage;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import Utils.Enums.ScreenshotTrigger;

public class ScreenshotManager {
    private static final Logger logger = LoggerFactory.getLogger(ScreenshotManager.class);
    private final Context context;
    private final Driver driver;

    public ScreenshotManager(Context context) {
        this.context = context;
        this.driver = context.getDriver();
    }

    // Public API: Central decision point for screenshot capture
    public void captureIfRequired(Scenario scenario) {
        if (shouldCapture(scenario)) {
            byte[] screenshot = driver.takeScreenShot();
            storeScreenshot(scenario, screenshot);
        }
    }

    // Private: Determine if screenshot is needed based on trigger
    public boolean shouldCapture(Scenario scenario) {
        return switch (ScreenshotTrigger.valueOf(context.getConfiguration().getScreenShotConfiguration().getScreenshotTrigger())) {
            case ON_EVERY_STEP -> true;
            case ON_FAILURE -> scenario.isFailed();
            default -> false;
        };
    }

    // Private: Handle storage based on configuration
    public void storeScreenshot(Scenario scenario, byte[] screenshot) {
        if (screenshot.length == 0) return;

        ScreenshotStorage storage = ScreenshotStorage.valueOf(
                context.getConfiguration().getScreenShotConfiguration().getScreenshotStorage()
        );

        // Get trigger to decide naming strategy
        ScreenshotTrigger trigger = ScreenshotTrigger.valueOf(
                context.getConfiguration().getScreenShotConfiguration().getScreenshotTrigger()
        );

        // Choose name based on trigger
        String name;
        if (trigger == ScreenshotTrigger.ON_EVERY_STEP) {
            name = StepListener.step.getText();  // Thread-safe method
            System.out.println("###############################################"+name+"###############################################");
        } else {
            name = scenario.getName();
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+name+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

        }

        name = sanitizeName(name);

        // Rest of the storage logic...
        if (storage == ScreenshotStorage.LOCAL || storage == ScreenshotStorage.BOTH) {
            saveToDisk(name, screenshot);
        }

        if (storage == ScreenshotStorage.REPORT || storage == ScreenshotStorage.BOTH) {
            attachToReport(scenario, screenshot, name);
        }
    }

    // Private: Save to filesystem with organized structure
    private void saveToDisk(String name, byte[] screenshot) {
        try {
            String timestamp = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmssSSS"));

            // Include scenario name + step name (if applicable) in filename
            String fileName = String.format("%s_%s.png",
                    sanitizeName(name),  // Could be step or scenario name
                    timestamp
            );

            Path outputPath = Paths.get(
                    context.getConfiguration().getScreenShotConfiguration().getScreenshotDirectory(),
                    LocalDate.now().toString(),
                    fileName
            );

            Files.createDirectories(outputPath.getParent());
            Files.write(outputPath, screenshot);
            logger.info("Saved screenshot to: {}", outputPath);

        } catch (IOException e) {
            logger.error("Failed to save screenshot to disk", e);
        }
    }

    // Private: Attach to Cucumber report
    private void attachToReport(Scenario scenario, byte[] screenshot, String name) {
        scenario.attach(screenshot, "image/png", name);
    }

    // Private: Sanitize filenames
    private String sanitizeName(String rawName) {
        return rawName.replaceAll("[^a-zA-Z0-9-_]", "_");
    }
}

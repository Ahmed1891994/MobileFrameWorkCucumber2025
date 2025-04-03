package Driver;

import Context.Context;
import Utils.Enums.Direction;
import Utils.Enums.Environment;
import Utils.Locator.Element;
import Utils.Locator.Locator;
import Utils.helpers.Scroller;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.remote.AutomationName;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public class IOSDriverImplementation implements Driver {
    private static final Logger logger = LoggerFactory.getLogger(IOSDriverImplementation.class);
    private final IOSDriver driver;
    private final Context context;
    private final FluentWait<IOSDriver> wait;

    public IOSDriverImplementation(Context context) {
        this.context = context;
        XCUITestOptions options = new XCUITestOptions();
        URL url = createAppiumServerUrl();
        configureOptionsForEnvironment(options);
        driver = new IOSDriver(url, options);
        wait = new FluentWait<>(driver);
        logger.info("IOSDriver initialized successfully with context: {}", context);
    }

    private URL createAppiumServerUrl() {
        try {
            logger.debug("Creating Appium server URL for localhost:4723");
            // Use URI to construct the URL
            URI uri = new URI("http", null, "127.0.0.1", 4723, "/wd/hub", null, null);
            return uri.toURL(); // Convert URI to URL
        } catch (Exception e) {
            logger.error("Failed to create Appium server URL. Invalid URL format.", e);
            throw new RuntimeException("Invalid Appium server URL", e);
        }
    }

    private void configureOptionsForEnvironment(XCUITestOptions options) {
        Environment env = Environment.getEnvironment();
        logger.debug("Configuring XCUITestOptions for environment: {}", env);

        if (env == Environment.BROWSERSTACK) {
            logger.info("Setting up BROWSERSTACK environment options");
            options.setApp(System.getProperty("app"));
        } else if (env == Environment.LOCAL) {
            logger.info("Setting up LOCAL environment options");
            options.setPlatformName(Platform.IOS.name());
            options.setAutomationName(AutomationName.IOS_XCUI_TEST);
            options.setApp(context.getConfiguration().getIOSConfiguration().getPath());
            options.enforceAppInstall();
            options.setBundleId(context.getConfiguration().getIOSConfiguration().getBundleId());
            options.setPlatformVersion(context.getConfiguration().getIOSConfiguration().getPlatformVersion());
            options.setUdid(context.getConfiguration().getIOSConfiguration().getUdid());
            options.setWdaConnectionTimeout(Duration.ofMinutes(context.getConfiguration().getIOSConfiguration().getWdaConnectionTimeoutInMinutes()));
            options.setNewCommandTimeout(Duration.ofMinutes(context.getConfiguration().getDriverConfiguration().getNewCommandTimeoutInMinutes()));
        } else {
            logger.warn("Unsupported environment: {}. Defaulting to LOCAL configuration.", env);
        }
    }

    private Optional<WebElement> findWithTimeoutAndInterval(Element locators, Duration timeout, Duration interval) {
        logger.debug("Attempting to find element with timeout: {} and polling interval: {}", timeout, interval);
        wait.withTimeout(timeout)
                .pollingEvery(interval)
                .ignoring(NoSuchElementException.class)
                .ignoring(TimeoutException.class);

        return locators.getLocators().stream()
                .filter(locator -> Platform.IOS.is(locator.getStrategy().getPlatform()))
                .map(locator -> {
                    try {
                        logger.debug("Attempting to locate element using strategy: {} and value: {}", locator.getStrategy(), locator.getValue());
                        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator.getStrategy().locate(locator.getValue())));
                    } catch (NoSuchElementException | TimeoutException e) {
                        logger.warn("Element not found using strategy: {} and value: {}", locator.getStrategy(), locator.getValue(), e);
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .findFirst();
    }

    @Override
    public WebElement findElement(Element locators) {
        logger.debug("Finding element with default timeout and interval");
        return findWithTimeoutAndInterval(
                locators,
                Duration.ofSeconds(context.getConfiguration().getDriverConfiguration().getTimeoutInSeconds()),
                Duration.ofMillis(context.getConfiguration().getDriverConfiguration().getIntervalInMillis())
        ).orElseThrow(() -> {
            logger.error("Element not found with provided locators: {}", locators);
            return new NoSuchElementException("Element not found with provided locators.");
        });
    }

    @Override
    public WebElement findInShortPeriod(Element locators) {
        logger.debug("Finding element with short timeout and interval");
        return findWithTimeoutAndInterval(
                locators,
                Duration.ofSeconds(context.getConfiguration().getDriverConfiguration().getWaitForShortPeriodInSeconds()),
                Duration.ofMillis(context.getConfiguration().getDriverConfiguration().getIntervalForShortPeriodInMillis())
        ).orElse(null);
    }

    @Override
    public boolean isDisplayed(Element locators) {
        logger.debug("Checking if element is displayed using locators: {}", locators);
        return findElement(locators).isDisplayed();
    }

    @Override
    public boolean isNegligentlyDisplayed(Element locators) {
        logger.debug("Checking if element is negligently displayed using locators: {}", locators);
        return findInShortPeriod(locators) != null;
    }

    @Override
    public void scrollTo(Element target, Direction direction, Element container) {
        logger.debug("Scrolling to target element in direction: {} with container: {}", direction, container);
        new Scroller().scrollTo(this, target, direction, container);
    }

    @Override
    public void scrollTo(Element target, Direction direction) {
        logger.debug("Scrolling to target element in direction: {}", direction);
        scrollTo(target, direction, null);
    }

    @Override
    public String getPageSource() {
        logger.debug("Retrieving page source");
        return driver.getPageSource();
    }

    @Override
    public Dimension getScreenSize() {
        logger.debug("Retrieving screen size");
        return driver.manage().window().getSize();
    }

    @Override
    public void perform(Collection<Sequence> actions) {
        logger.debug("Performing actions: {}", actions);
        driver.perform(actions);
    }

    @Override
    public void dismissAlert() {
        logger.debug("Attempting to dismiss alert");
        try {
            Alert alert = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.alertIsPresent());
            if (alert != null) {
                alert.dismiss();
                logger.info("Alert dismissed successfully");
            }
        } catch (TimeoutException e) {
            logger.warn("No alert found to dismiss", e);
        }
    }

    @Override
    public void acceptAlert() {
        logger.debug("Attempting to accept alert");
        try {
            Alert alert = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.alertIsPresent());
            if (alert != null) {
                alert.accept();
                logger.info("Alert accepted successfully");
            }
        } catch (TimeoutException e) {
            logger.warn("No alert found to accept", e);
        }
    }

//    /**
//     * Takes a screenshot of the current mobile screen, attaches it to the Cucumber scenario,
//     * and saves it to the file system.
//     * The screenshot is embedded in the Cucumber report as a PNG image and saved to a specified location.
//     *
//     * @param scenario The Cucumber scenario to which the screenshot will be attached.
//     */
//    @Override
//    public void takeScreenShot(Scenario scenario) {
//        // Initialize logger for this class
//        Logger logger = LoggerFactory.getLogger(this.getClass());
//
//        // Log the start of the screenshot process
//        logger.debug("Taking screenshot for scenario: {}", scenario.getName());
//
//        // Capture the screenshot as a byte array
//        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
//
//        // Attach the screenshot to the Cucumber scenario
//        scenario.attach(screenshot, "image/png", scenario.getName());
//        logger.info("Screenshot attached to scenario: {}", scenario.getName());
//
//        // Save the screenshot to the file system
//        saveScreenshotToFileSystem(scenario.getName());
//    }
//
//    /**
//     * Saves the screenshot to the file system with a dynamically generated file name.
//     *
//     * @param scenarioName The name of the scenario (used in the file name).
//     */
//    private void saveScreenshotToFileSystem(String scenarioName) {
//        Logger logger = LoggerFactory.getLogger(this.getClass());
//
//        // Capture the screenshot as a file
//        File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//
//        // Get current time and date for the screenshot file name
//        String time = LocalTime.now().toString().replace(":", "-").substring(0, 5);
//        String date = LocalDate.now().toString();
//        logger.debug("Saving screenshot with time -> {} and date -> {}", time, date);
//
//        // Define the destination path for the screenshot
//        String destination = System.getProperty("user.dir") + File.separator + "ScreenShots" + File.separator + date
//                + "_" + time + File.separator + scenarioName + "_" + ThreadLocalRandom.current().nextInt() + ".png";
//
//        try {
//            // Create the directory if it doesn't exist
//            File directory = new File(destination).getParentFile();
//            if (!directory.exists()) {
//                boolean dirCreated = directory.mkdirs();
//                if (dirCreated) {
//                    logger.debug("Created directory: {}", directory.getAbsolutePath());
//                } else {
//                    logger.error("Failed to create directory: {}", directory.getAbsolutePath());
//                }
//            }
//
//            // Copy the screenshot file to the destination
//            logger.debug("Copying screenshot to destination: {}", destination);
//            FileUtils.copyFile(source, new File(destination));
//            logger.info("Screenshot saved to: {}", destination);
//        } catch (IOException e) {
//            logger.error("Failed to save screenshot to destination: {}", destination, e);
//        }
//    }

    @Override
    public byte[] takeScreenShot() {
        return  ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Override
    public void quit() {
        if (driver != null) {
            logger.info("Quitting IOSDriver");
            driver.quit();
        }
    }
}
package Driver;

import Context.Context;
import Utils.Enums.Direction;
import Utils.Locator.Element;
import Utils.helpers.Scroller;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import Utils.Enums.Environment;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public class AndroidDriverImplementation implements Driver {
    private static final Logger logger = LoggerFactory.getLogger(AndroidDriverImplementation.class);
    private final AndroidDriver driver;
    private final Context context;
    private final FluentWait<AndroidDriver> wait;

    public AndroidDriverImplementation(Context context) {
        this.context = context;
        UiAutomator2Options options = new UiAutomator2Options();
        URL url = createAppiumServerUrl();
        configureOptionsForEnvironment(options);
        driver = new AndroidDriver(url, options);
        wait = new FluentWait<>(driver);
        logger.info("AndroidDriver initialized successfully with context: {}", context);
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

    private void configureOptionsForEnvironment(UiAutomator2Options options) {
        Environment env = Environment.getEnvironment();
        logger.debug("Configuring UiAutomator2Options for environment: {}", env);

        if (env == Environment.BROWSERSTACK) {
            logger.info("Setting up BROWSERSTACK environment options");
            //options.setApp(System.getProperty("app"));
        } else if (env == Environment.LOCAL) {
            logger.info("Setting up LOCAL environment options");
            options.setPlatformName(Platform.ANDROID.name());
            options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
            options.setAppPackage(context.getConfiguration().getAndroidConfiguration().getAppPackage());
            options.setAppActivity(context.getConfiguration().getAndroidConfiguration().getAppActivity());
            options.setAutoGrantPermissions(context.getConfiguration().getAndroidConfiguration().isGetAutoGrantPermissions());
            options.setNewCommandTimeout(Duration.ofMinutes(context.getConfiguration().getDriverConfiguration().getNewCommandTimeoutInMinutes()));
            options.setAdbExecTimeout(Duration.ofMinutes(context.getConfiguration().getAndroidConfiguration().getAdbExecuteTimeoutInMinutes()));
            options.allowTestPackages();
        } else {
            logger.warn("Unsupported environment: {}. Defaulting to LOCAL configuration.", env);
        }
    }

    private Optional<WebElement> findWithTimeoutAndInterval(Element element, Duration timeout, Duration interval) {
        logger.debug("Attempting to find element with timeout: {} and polling interval: {}", timeout, interval);
        wait.withTimeout(timeout)
                .pollingEvery(interval)
                .ignoring(NoSuchElementException.class)
                .ignoring(TimeoutException.class);

        return element.getLocators().stream()
                .filter(locator -> Platform.ANDROID.is(locator.getStrategy().getPlatform()))
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

    @Override
    public byte[] takeScreenShot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Override
    public void quit() {
        if (driver != null) {
            logger.info("Quitting AndroidDriver");
            driver.quit();
        }
    }
}
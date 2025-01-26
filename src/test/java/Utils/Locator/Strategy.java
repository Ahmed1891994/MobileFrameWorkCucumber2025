package Utils.Locator;

import io.appium.java_client.AppiumBy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Function;

@AllArgsConstructor
@Getter
public enum Strategy {
    ANDROID_ID(Platform.ANDROID, AppiumBy::id),
    ANDROID_CLASS_NAME(Platform.ANDROID, AppiumBy::className),
    ANDROID_NAME(Platform.ANDROID, AppiumBy::name),
    ANDROID_CSS_SELECTOR(Platform.ANDROID, AppiumBy::cssSelector),
    ANDROID_XPATH(Platform.ANDROID, AppiumBy::xpath),
    ANDROID_ACCESSIBILITY_ID(Platform.ANDROID, AppiumBy::accessibilityId),
    ANDROID_UI_AUTOMATOR(Platform.ANDROID, AppiumBy::androidUIAutomator),
    ANDROID_VIEW_MATCHER(Platform.ANDROID, AppiumBy::androidViewMatcher),
    ANDROID_DATA_MATCHER(Platform.ANDROID, AppiumBy::androidDataMatcher),

    IOS_ID(Platform.IOS, AppiumBy::id),
    IOS_CLASS_NAME(Platform.IOS, AppiumBy::className),
    IOS_NAME(Platform.IOS, AppiumBy::name),
    IOS_CSS_SELECTOR(Platform.IOS, AppiumBy::cssSelector),
    IOS_XPATH(Platform.IOS, AppiumBy::xpath),
    IOS_ACCESSIBILITY_ID(Platform.IOS, AppiumBy::accessibilityId),
    IOS_NS_PREDICATE_STRING(Platform.IOS, AppiumBy::iOSNsPredicateString),
    IOS_CLASS_CHAIN(Platform.IOS, AppiumBy::iOSClassChain);

    private static final Logger logger = LoggerFactory.getLogger(Strategy.class);
    private final Platform platform;
    private final Function<String, By> locatorFunction;

    /**
     * Creates a locator using the specified strategy and value.
     *
     * @param locator The locator value (e.g., ID, XPath, etc.).
     * @return The created By locator.
     */
    public By locate(String locator) {
        logger.debug("Creating locator using strategy: {} with value: {}", this.name(), locator);
        By byLocator = locatorFunction.apply(locator);
        logger.debug("Locator created successfully: {}", byLocator);
        return byLocator;
    }
}
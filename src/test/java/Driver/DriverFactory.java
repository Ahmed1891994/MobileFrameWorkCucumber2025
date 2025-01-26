package Driver;

import Context.Context;
import org.openqa.selenium.Platform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DriverFactory {
    private static final Logger logger = LoggerFactory.getLogger(DriverFactory.class);

    /**
     * Creates and returns a platform-specific driver instance based on the context.
     *
     * @param context The context containing platform information.
     * @return A Driver instance (AndroidDriverImplementation or IOSDriverImplementation).
     * @throws IllegalArgumentException If the platform is unsupported.
     */
    public static Driver getDriver(Context context) {
        String platform = context.getPlatform().toUpperCase();
        logger.debug("Creating driver for platform: {}", platform);

        if (Platform.valueOf(platform).is(Platform.ANDROID)) {
            logger.info("Initializing AndroidDriver for platform: {}", platform);
            return new AndroidDriverImplementation(context);
        } else if (Platform.valueOf(platform).is(Platform.IOS)) {
            logger.info("Initializing IOSDriver for platform: {}", platform);
            return new IOSDriverImplementation(context);
        } else {
            logger.error("Unsupported platform: {}", platform);
            throw new IllegalArgumentException("Unsupported platform: " + platform + " , Platform should be ANDROID or IOS");
        }
    }
}
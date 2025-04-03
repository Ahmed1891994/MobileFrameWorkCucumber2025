package Utils.helpers;

import Driver.Driver;
import Utils.Enums.Direction;
import Utils.Locator.Element;
import Utils.Locator.Locator;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;

public class Scroller {
    private static final Logger logger = LoggerFactory.getLogger(Scroller.class);

    /**
     * Scrolls to the target element in the specified direction within the container.
     *
     * @param driver    The driver instance.
     * @param target    The target element locators.
     * @param direction The direction to scroll.
     * @param container The container locators (optional).
     */
    public void scrollTo(Driver driver, Element target, Direction direction, Element container) {
        logger.debug("Attempting to scroll to target element in direction: {}", direction);
        WebElement element = driver.findInShortPeriod(target);
        if (element != null) {
            logger.debug("Target element is already visible. No scrolling needed.");
            return;
        }

        String previousPageSource = "";
        String currentPageSource = driver.getPageSource();

        Point origin = getOriginPoint(driver, container);
        Dimension size = getContainerSize(driver, container);

        logger.debug("Starting scroll process with origin: {} and container size: {}", origin, size);

        while (element == null && !currentPageSource.equals(previousPageSource)) {
            previousPageSource = currentPageSource;
            swipe(driver, direction, origin, size);
            currentPageSource = driver.getPageSource();
            element = driver.findInShortPeriod(target);
            logger.debug("Scrolled in direction: {}. Checking for target element...", direction);
        }

        if (element != null) {
            logger.info("Successfully scrolled to the target element.");
        } else {
            logger.warn("Target element not found after scrolling.");
        }
    }

    /**
     * Gets the size of the container. If no container is provided, returns the screen size.
     *
     * @param driver    The driver instance.
     * @param container The container locators (optional).
     * @return The size of the container or screen.
     */
    private Dimension getContainerSize(Driver driver, Element container) {
        Dimension size = (container != null && !container.getLocators().isEmpty()) ? driver.findElement(container).getSize()
                : driver.getScreenSize();
        logger.debug("Retrieved container size: {}", size);
        return size;
    }

    /**
     * Gets the origin point of the container. If no container is provided, returns the top-left corner (0, 0).
     *
     * @param driver    The driver instance.
     * @param container The container locators (optional).
     * @return The origin point of the container or screen.
     */
    private Point getOriginPoint(Driver driver, Element container) {
        Point origin = (container != null && !container.getLocators().isEmpty()) ? driver.findElement(container).getLocation()
                : new Point(0, 0);
        logger.debug("Retrieved origin point: {}", origin);
        return origin;
    }

    /**
     * Performs a swipe action in the specified direction.
     *
     * @param driver    The driver instance.
     * @param direction The direction to swipe.
     * @param origin    The origin point of the swipe.
     * @param size      The size of the container or screen.
     */
    private void swipe(Driver driver, Direction direction, Point origin, Dimension size) {
        int startX = origin.x + (size.width / 2);
        int startY = origin.y + (size.height / 2);
        int endX = startX;
        int endY = startY;

        switch (direction) {
            case UP -> {
                startY = origin.y + (int) (size.height * .1);
                endY = origin.y + (int) (size.height * .6);
            }
            case DOWN -> {
                startY = origin.y + (int) (size.height * .6);
                endY = origin.y + (int) (size.height * .1);
            }
            case RIGHT -> {
                startX = origin.x + (int) (size.width * .1);
                endX = origin.x + (int) (size.width * .6);
            }
            case LEFT -> {
                startX = origin.x + (int) (size.width * .6);
                endX = origin.x + (int) (size.width * .1);
            }
        }

        logger.debug("Performing swipe from ({}, {}) to ({}, {}) in direction: {}", startX, startY, endX, endY, direction);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);

        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));

        swipe.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), endX, endY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(List.of(swipe));
    }
}
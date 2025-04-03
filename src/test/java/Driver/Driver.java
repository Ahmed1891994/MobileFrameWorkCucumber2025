package Driver;

import Utils.Enums.Direction;
import Utils.Locator.Element;
import Utils.Locator.Locator;
import io.cucumber.java.Scenario;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Sequence;

import java.util.Collection;
import java.util.List;

public interface Driver {
        WebElement findElement(Element locators);

        WebElement findInShortPeriod(Element locators);

        public boolean isDisplayed(Element locators);

        public boolean isNegligentlyDisplayed(Element locators);

        public void scrollTo(Element target, Direction direction, Element container);

        public void scrollTo(Element target,Direction direction);

        public String getPageSource();

        public Dimension getScreenSize();

        public void perform(Collection<Sequence> actions);

        public void dismissAlert();
;
        public void acceptAlert();

        public byte[] takeScreenShot();

        void quit();
}

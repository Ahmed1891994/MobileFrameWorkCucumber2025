package Driver;

import Utils.Enums.Direction;
import Utils.Locator.Locator;
import io.cucumber.java.Scenario;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Sequence;

import java.util.Collection;
import java.util.List;

public interface Driver {
        WebElement findElement(List<Locator> locators);

        WebElement findInShortPeriod(List<Locator> locators);

        public boolean isDisplayed(List<Locator> locators);

        public boolean isNegligentlyDisplayed(List<Locator> locators);

        public void scrollTo(List<Locator> target, Direction direction, List<Locator> container);

        public void scrollTo(List<Locator> target,Direction direction);

        public String getPageSource();

        public Dimension getScreenSize();

        public void perform(Collection<Sequence> actions);

        public void dismissAlert();
;
        public void acceptAlert();

        public void takeScreenShot(Scenario scenario);

        void quit();
}

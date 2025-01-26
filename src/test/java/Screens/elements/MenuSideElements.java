package Screens.elements;

import Utils.Locator.Locator;
import Utils.Locator.Strategy;

import java.util.List;

public class MenuSideElements {
    public List<Locator> menuChoiceButton(String menuChoice) {
        return List.of(
                new Locator(Strategy.ANDROID_UI_AUTOMATOR, String.format("new UiSelector().text(\"%s\")", menuChoice))
        );
    }
}

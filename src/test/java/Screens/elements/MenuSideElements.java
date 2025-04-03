package Screens.elements;

import Utils.Locator.Element;
import Utils.Locator.Locator;
import Utils.Locator.Strategy;

import java.util.List;

public class MenuSideElements {
    public Element menuChoiceButton(String menuChoice) {
        return new Element(String.format("menuChoiceButton - %s", menuChoice), List.of(
                new Locator(Strategy.ANDROID_UI_AUTOMATOR, String.format("new UiSelector().text(\"%s\")", menuChoice))
        ));
    }
}

package Screens.elements;

import Utils.Locator.*;

import java.util.List;

public class MenuSideElements {
    public Element menuChoiceButton(String menuChoice) {
        return new Element(String.format("menu Choice Button - %s", menuChoice), List.of(
                new Locator(Strategy.ANDROID_UI_AUTOMATOR, String.format("new UiSelector().text(\"%s\")", menuChoice))
        ));
    }
}

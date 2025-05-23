package Screens.actions;

import Context.Context;
import Driver.Driver;
import Screens.elements.MenuSideElements;
import io.qameta.allure.Step;

public class MenuSideActions {
    private final MenuSideElements elements;
    private final Driver driver;
    private Context context;

    public MenuSideActions(Context context) {
        this.context = context;
        this.driver = context.getDriver();
        elements = new MenuSideElements();
    }

    @Step
    public void chooseMenuItem(String menuChoice)
    {
        driver.findElement(elements.menuChoiceButton(menuChoice)).click();
    }

    @Step
    public boolean isMenuItemExist(String menuChoice)
    {
        return driver.isDisplayed(elements.menuChoiceButton(menuChoice));
    }
}

package Screens.actions;

import Context.Context;
import Driver.Driver;
import Screens.elements.TopBarElements;

public class TopBarActions {
    private final Driver driver;
    private final TopBarElements elements;

    public TopBarActions(Context context) {
        this.driver = context.getDriver();
        elements = new TopBarElements();
    }

    public boolean isInScreen()
    {
        return driver.isDisplayed(elements.title);
    }

    public void clickOnBurgerButton()
    {
        driver.findElement(elements.burgerButton).click();
    }

    public void clickOnCartButton()
    {
        driver.findElement(elements.cartButton).click();
    }
}

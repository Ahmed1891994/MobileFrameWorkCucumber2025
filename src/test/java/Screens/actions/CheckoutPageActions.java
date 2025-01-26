package Screens.actions;

import Context.Context;
import Driver.Driver;
import Screens.elements.CheckoutPageElements;

public class CheckoutPageActions {
    private final CheckoutPageElements elements;
    private final Driver driver;

    protected CheckoutPageActions(Context context) {
        this.driver = context.getDriver();
        elements = new CheckoutPageElements();
    }
}

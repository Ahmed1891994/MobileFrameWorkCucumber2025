package Screens.actions;

import Context.Context;
import Driver.Driver;
import Screens.elements.CartPageElements;

public class CartPageActions {
    private final CartPageElements elements;
    private final Driver driver;

    protected CartPageActions(Context context) {
        this.driver = context.getDriver();
        elements = new CartPageElements();
    }
}

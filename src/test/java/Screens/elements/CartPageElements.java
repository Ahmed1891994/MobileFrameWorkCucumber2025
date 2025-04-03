package Screens.elements;

import Utils.Locator.Element;
import Utils.Locator.Locator;
import Utils.Locator.Strategy;

import java.util.List;

public class CartPageElements {
    public Element noItemTitle = new Element("noItemTitle",List.of(
            new Locator(Strategy.ANDROID_ID,"com.saucelabs.mydemoapp.android:id/noItemTitleTV"),
            new Locator(Strategy.IOS_ID,"com.saucelabs.mydemoapp.android:id/noItemTitleTV")
    ));

    public Element noItemIcon = new Element("noItemIcon",List.of(
            new Locator(Strategy.ANDROID_UI_AUTOMATOR,"new UiSelector().className(\"android.widget.ImageView\").instance(3)")
    ));

    public Element noItemSubtitle = new Element("noItemSubtitle",List.of(
            new Locator(Strategy.ANDROID_XPATH,"//android.widget.LinearLayout[@resource-id=\"com.saucelabs.mydemoapp.android:id/cartInfoLL\"]/android.widget.TextView")
    ));

    public Element shoppingButton = new Element("shoppingButton",List.of(
            new Locator(Strategy.ANDROID_ID,"com.saucelabs.mydemoapp.android:id/shoppingBt")
    ));

    public Element cartTitle = new Element("cartTitle",List.of(
            new Locator(Strategy.ANDROID_ID,"com.saucelabs.mydemoapp.android:id/productTV")
    ));

    public Element productByName(String productName) {
        return new Element(String.format("productName %s", productName), List.of(
                new Locator(Strategy.ANDROID_XPATH, String.format("//android.widget.TextView[@resource-id=\"com.saucelabs.mydemoapp.android:id/titleTV\" and @text=\"%s\")]", productName))
        ));
    }

    public Element productByPartOfName(String productName) {
        return new Element(String.format("productName %s", productName), List.of(
                new Locator(Strategy.ANDROID_XPATH, String.format("//android.widget.TextView[@resource-id=\"com.saucelabs.mydemoapp.android:id/titleTV\"  and contains(@text, \"%s\")]", productName))
        ));
    }

    public Element priceOfPruduct(String productName) {
        return new Element(String.format("priceOfProduct %s", productName), List.of(
                new Locator(Strategy.ANDROID_XPATH, String.format("//android.widget.TextView[@resource-id=\"com.saucelabs.mydemoapp.android:id/titleTV\"  and contains(@text, \"%s\")]/following-sibling::android.widget.TextView", productName))
        ));
    }

    public Element totalPrice = new Element("totalPrice",List.of(
            new Locator(Strategy.ANDROID_ID,"com.saucelabs.mydemoapp.android:id/totalPriceTV")
    ));

    public Element numberOfItems = new Element("numberOfItems",List.of(
            new Locator(Strategy.ANDROID_ID,"com.saucelabs.mydemoapp.android:id/itemsTV")
    ));

    public Element checkoutButton = new Element("checkoutButton",List.of(
            new Locator(Strategy.ANDROID_ID,"com.saucelabs.mydemoapp.android:id/cartBt")
    ));
}

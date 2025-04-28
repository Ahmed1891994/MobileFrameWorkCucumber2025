package Screens.elements;


import Utils.Locator.*;

import java.util.List;

public class CatalogPageElements {
    public Element sortButton = new Element("sort Button", List.of(
            new Locator(Strategy.ANDROID_ID, "com.saucelabs.mydemoapp.android:id/sortIV")
    ));

    public Element productTitle = new Element("product Title", List.of(
            new Locator(Strategy.ANDROID_ID, "com.saucelabs.mydemoapp.android:id/productTV")
    ));

    public Element productByName(String productName) {
        return new Element(String.format("product By Name %s", productName), List.of(
                new Locator(Strategy.ANDROID_XPATH, String.format("//android.widget.TextView[@content-desc=\"Product Title\" and @text=\"%s\"] /preceding-sibling::android.widget.ImageView", productName))
        ));
    }

    public Element productByPartOfName(String productName) {
        return new Element(String.format("product By Part Of Name %s", productName), List.of(
                new Locator(Strategy.ANDROID_XPATH, String.format("//android.widget.TextView[@content-desc=\"Product Title\" and contains(@text, \"%s\")]/preceding-sibling::android.widget.ImageView", productName))
        ));
    }

    public Element priceOfProduct(String productName) {
        return new Element(String.format("price Of Product %s", productName), List.of(
                new Locator(Strategy.ANDROID_XPATH, String.format("//android.widget.TextView[@content-desc=\"Product Title\" and @text=\"%s\"] /following-sibling::android.widget.TextView", productName))
        ));
    }

    public Element sortTitle = new Element("sort Title", List.of(
            new Locator(Strategy.ANDROID_ID, "com.saucelabs.mydemoapp.android:id/sortTV")
    ));

    public Element sortingOptions(String type, String order) {
        return new Element(String.format("sorting Options %s %s", type, order), List.of(
                new Locator(Strategy.ANDROID_XPATH, String.format("//android.widget.TextView[contains(@text, \"%s\") and contains(@text, \"%s\")]", type, order))
        ));
    }
}

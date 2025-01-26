package Screens.elements;

import Utils.Locator.Locator;
import Utils.Locator.Strategy;

import java.util.List;

public class CartPageElements {
    public List<Locator> noItemTitle = List.of(
            new Locator(Strategy.ANDROID_ID,"com.saucelabs.mydemoapp.android:id/noItemTitleTV"),
            new Locator(Strategy.IOS_ID,"com.saucelabs.mydemoapp.android:id/noItemTitleTV")
    );

    public List<Locator> noItemIcon = List.of(
            new Locator(Strategy.ANDROID_UI_AUTOMATOR,"new UiSelector().className(\"android.widget.ImageView\").instance(3)")
    );

    public List<Locator> noItemSubtitle = List.of(
            new Locator(Strategy.ANDROID_XPATH,"//android.widget.LinearLayout[@resource-id=\"com.saucelabs.mydemoapp.android:id/cartInfoLL\"]/android.widget.TextView")
    );

    public List<Locator> shoppingButton = List.of(
            new Locator(Strategy.ANDROID_ID,"com.saucelabs.mydemoapp.android:id/shoppingBt")
    );

    public List<Locator> cartTitle = List.of(
            new Locator(Strategy.ANDROID_ID,"com.saucelabs.mydemoapp.android:id/productTV")
    );

    public List<Locator> productByName(String productName) {
        return List.of(
                new Locator(Strategy.ANDROID_XPATH, String.format("//android.widget.TextView[@resource-id=\"com.saucelabs.mydemoapp.android:id/titleTV\" and @text=\"%s\")]", productName))
            );
}

    public List<Locator> productByPartOfName(String productName) {
        return List.of(
                new Locator(Strategy.ANDROID_XPATH, String.format("//android.widget.TextView[@resource-id=\"com.saucelabs.mydemoapp.android:id/titleTV\"  and contains(@text, \"%s\")]", productName))
        );
    }

    public List<Locator> priceOfPruduct(String productName) {
        return List.of(
                new Locator(Strategy.ANDROID_XPATH, String.format("//android.widget.TextView[@resource-id=\"com.saucelabs.mydemoapp.android:id/titleTV\"  and contains(@text, \"%s\")]/following-sibling::android.widget.TextView", productName))
        );
    }

    public List<Locator> totalPrice = List.of(
            new Locator(Strategy.ANDROID_ID,"com.saucelabs.mydemoapp.android:id/totalPriceTV")
    );

    public List<Locator> numberOfItems = List.of(
            new Locator(Strategy.ANDROID_ID,"com.saucelabs.mydemoapp.android:id/itemsTV")
    );

    public List<Locator> checkoutButton = List.of(
            new Locator(Strategy.ANDROID_ID,"com.saucelabs.mydemoapp.android:id/cartBt")
    );
}

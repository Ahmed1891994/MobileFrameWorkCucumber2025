package Screens.elements;

import Utils.Locator.Locator;
import Utils.Locator.Strategy;
import java.util.List;

public class CatalogPageElements {
    public List<Locator> sortButton = List.of(
            new Locator(Strategy.ANDROID_ID,"com.saucelabs.mydemoapp.android:id/sortIV")
    );

    public List<Locator> productTitle = List.of(
            new Locator(Strategy.ANDROID_ID,"com.saucelabs.mydemoapp.android:id/productTV")
    );

    public List<Locator> productByName(String productName) {
        return List.of(
                new Locator(Strategy.ANDROID_XPATH, String.format("//android.widget.TextView[@content-desc=\"Product Title\" and @text=%s]/preceding-sibling::android.widget.ImageView", productName))
        );
    }

    public List<Locator> productByPartOfName(String productName) {
        return List.of(
                new Locator(Strategy.ANDROID_XPATH, String.format("//android.widget.TextView[@content-desc=\"Product Title\" and contains(@text, \"%s\")]/preceding-sibling::android.widget.ImageView", productName))
        );
    }

    public List<Locator> priceOfPruduct(String productName) {
        return List.of(
                new Locator(Strategy.ANDROID_XPATH, String.format("//android.widget.TextView[@content-desc=\"Product Title\" and @text=\"%s\")]/following-sibling::android.widget.TextView", productName))
        );
    }

    public List<Locator> sortTitle = List.of(
            new Locator(Strategy.ANDROID_ID,"com.saucelabs.mydemoapp.android:id/sortTV")
    );

    public List<Locator> sortingOptions(String type,String order)
    {
        return List.of(
                new Locator(Strategy.ANDROID_XPATH,String.format("//android.widget.TextView[contains(@text, %s\"%s\") and contains(@text, \"%s\")]", type, order))
        );
    }
}

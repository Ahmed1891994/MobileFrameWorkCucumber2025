package Screens.elements;

import Utils.Locator.Element;
import Utils.Locator.Locator;
import Utils.Locator.Strategy;

import java.util.List;

public class TopBarElements {
    public Element title = new Element("title", List.of(
            new Locator(Strategy.ANDROID_ID, "com.saucelabs.mydemoapp.android:id/mTvTitle")
    ));

    public Element burgerButton = new Element("burgerButton", List.of(
            new Locator(Strategy.ANDROID_ID, "com.saucelabs.mydemoapp.android:id/menuIV")
    ));

    public Element cartButton = new Element("cartButton", List.of(
            new Locator(Strategy.ANDROID_ID, "com.saucelabs.mydemoapp.android:id/cartIV")
    ));
}

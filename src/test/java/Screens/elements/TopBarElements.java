package Screens.elements;

import Utils.Locator.*;

import java.util.List;

public class TopBarElements {
    public Element title = new Element("title", List.of(
            new Locator(Strategy.ANDROID_ID, "com.saucelabs.mydemoapp.android:id/mTvTitle")
    ));

    public Element burgerButton = new Element("burger Button", List.of(
            new Locator(Strategy.ANDROID_ID, "com.saucelabs.mydemoapp.android:id/menuIV")
    ));

    public Element cartButton = new Element("cart Button", List.of(
            new Locator(Strategy.ANDROID_ID, "com.saucelabs.mydemoapp.android:id/cartIV")
    ));
}

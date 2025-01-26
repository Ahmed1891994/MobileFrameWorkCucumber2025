package Screens.elements;

import Utils.Locator.Locator;
import Utils.Locator.Strategy;

import java.util.List;

public class TopBarElements {
    public List<Locator> title = List.of(
            new Locator(Strategy.ANDROID_ID,"com.saucelabs.mydemoapp.android:id/mTvTitle")
    );

    public List<Locator> burgerButton = List.of(
            new Locator(Strategy.ANDROID_ID,"com.saucelabs.mydemoapp.android:id/menuIV")
    );

    public List<Locator> cartButton = List.of(
            new Locator(Strategy.ANDROID_ID,"com.saucelabs.mydemoapp.android:id/cartIV")
    );
}

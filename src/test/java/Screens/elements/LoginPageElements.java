package Screens.elements;

import Utils.Locator.Locator;
import Utils.Locator.Strategy;

import java.util.List;

public class LoginPageElements {
    public List<Locator> title = List.of(
            new Locator(Strategy.ANDROID_ID,"com.saucelabs.mydemoapp.android:id/loginTV")
    );

    public List<Locator> usernameInput = List.of(
            new Locator(Strategy.ANDROID_ID,"com.saucelabs.mydemoapp.android:id/nameET")
    );

    public List<Locator> passwordInput = List.of(
            new Locator(Strategy.ANDROID_ID,"com.saucelabs.mydemoapp.android:id/passwordET")
    );

    public List<Locator> loginButton = List.of(
            new Locator(Strategy.ANDROID_ID,"com.saucelabs.mydemoapp.android:id/loginBtn")
    );
}

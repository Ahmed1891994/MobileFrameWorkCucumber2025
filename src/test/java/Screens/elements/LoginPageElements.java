package Screens.elements;

import Utils.Locator.Element;
import Utils.Locator.Locator;
import Utils.Locator.Strategy;

import java.util.List;

public class LoginPageElements {
    public Element title = new Element("loginTitle", List.of(
            new Locator(Strategy.ANDROID_ID, "com.saucelabs.mydemoapp.android:id/loginTV")
    ));

    public Element usernameInput = new Element("usernameInput", List.of(
            new Locator(Strategy.ANDROID_ID, "com.saucelabs.mydemoapp.android:id/nameET")
    ));

    public Element passwordInput = new Element("passwordInput", List.of(
            new Locator(Strategy.ANDROID_ID, "com.saucelabs.mydemoapp.android:id/passwordET")
    ));

    public Element loginButton = new Element("loginButton", List.of(
            new Locator(Strategy.ANDROID_ID, "com.saucelabs.mydemoapp.android:id/loginBtn")
    ));
}

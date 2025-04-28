package Screens.elements;

import Utils.Locator.*;

import java.util.List;

public class LoginPageElements {
    public Element title = new Element("login Title", List.of(
            new Locator(Strategy.ANDROID_ID, "com.saucelabs.mydemoapp.android:id/loginTV")
    ));

    public Element usernameInput = new Element("username Input", List.of(
            new Locator(Strategy.ANDROID_ID, "com.saucelabs.mydemoapp.android:id/nameET")
    ));

    public Element passwordInput = new Element("password Input", List.of(
            new Locator(Strategy.ANDROID_ID, "com.saucelabs.mydemoapp.android:id/passwordET")
    ));

    public Element loginButton = new Element("login Button", List.of(
            new Locator(Strategy.ANDROID_ID, "com.saucelabs.mydemoapp.android:id/loginBtn")
    ));
}

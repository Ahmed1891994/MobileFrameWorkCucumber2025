package Screens.elements;

import Utils.Locator.*;

import java.util.List;

public class CheckoutPageElements {
    public Element title = new Element("checkoutTitle", List.of(
            new Locator(Strategy.ANDROID_ID, "com.saucelabs.mydemoapp.android:id/checkoutTitleTV")
    ));

    public Element fullNameInput = new Element("fullNameInput", List.of(
            new Locator(Strategy.ANDROID_ID, "com.saucelabs.mydemoapp.android:id/fullNameET")
    ));

    public Element fullNameErrorLabel = new Element("fullNameErrorLabel", List.of(
            new Locator(Strategy.ANDROID_ID, "com.saucelabs.mydemoapp.android:id/fullNameErrorTV")
    ));

    public Element address1Input = new Element("address1Input", List.of(
            new Locator(Strategy.ANDROID_ID, "com.saucelabs.mydemoapp.android:id/address1ET")
    ));

    public Element address1ErrorLabel = new Element("address1ErrorLabel", List.of(
            new Locator(Strategy.ANDROID_ID, "com.saucelabs.mydemoapp.android:id/address1ErrorTV")
    ));

    public Element address2Input = new Element("address2Input", List.of(
            new Locator(Strategy.ANDROID_ID, "com.saucelabs.mydemoapp.android:id/address2ET")
    ));

    public Element cityInput = new Element("cityInput", List.of(
            new Locator(Strategy.ANDROID_ID, "com.saucelabs.mydemoapp.android:id/cityET")
    ));

    public Element cityErrorLabel = new Element("cityErrorLabel", List.of(
            new Locator(Strategy.ANDROID_ID, "com.saucelabs.mydemoapp.android:id/cityErrorTV")
    ));

    public Element stateInput = new Element("stateInput", List.of(
            new Locator(Strategy.ANDROID_ID, "com.saucelabs.mydemoapp.android:id/stateET")
    ));

    public Element zipInput = new Element("zipInput", List.of(
            new Locator(Strategy.ANDROID_ID, "com.saucelabs.mydemoapp.android:id/zipET")
    ));

    public Element zipErrorLabel = new Element("zipErrorLabel", List.of(
            new Locator(Strategy.ANDROID_ID, "com.saucelabs.mydemoapp.android:id/zipErrorTV")
    ));

    public Element countryInput = new Element("country Input", List.of(
            new Locator(Strategy.ANDROID_ID, "com.saucelabs.mydemoapp.android:id/countryET")
    ));

    public Element countryErrorLabel = new Element("country Error Label", List.of(
            new Locator(Strategy.ANDROID_ID, "com.saucelabs.mydemoapp.android:id/countryErrorTV")
    ));

    public Element checkoutPaymentButton = new Element("checkout Payment Button", List.of(
            new Locator(Strategy.ANDROID_ID, "com.saucelabs.mydemoapp.android:id/checkoutTitleTV")
    ));

    public Element cardFullNameInput = new Element("card FullName Input", List.of(
            new Locator(Strategy.ANDROID_ID, "com.saucelabs.mydemoapp.android:id/nameET")
    ));

    public Element cardNumberInput = new Element("card Number Input", List.of(
            new Locator(Strategy.ANDROID_ID, "com.saucelabs.mydemoapp.android:id/cardNumberET")
    ));

    public Element cardExpirationDateInput = new Element("card Expiration Date Input", List.of(
            new Locator(Strategy.ANDROID_ID, "com.saucelabs.mydemoapp.android:id/expirationDateET")
    ));

    public Element cardSecurityCodeInput = new Element("card Security Code Input", List.of(
            new Locator(Strategy.ANDROID_ID, "com.saucelabs.mydemoapp.android:id/securityCodeET")
    ));

    public Element cardPaymentButton = new Element("card Payment Button", List.of(
            new Locator(Strategy.ANDROID_ID, "com.saucelabs.mydemoapp.android:id/paymentBtn")
    ));

    public Element billingAddressCheckBox = new Element("billing Address CheckBox", List.of(
            new Locator(Strategy.ANDROID_ID, "com.saucelabs.mydemoapp.android:id/billingAddressCB")
    ));

    public Element reviewYourOrderLabel = new Element("review Your Order Label", List.of(
            new Locator(Strategy.ANDROID_ID, "com.saucelabs.mydemoapp.android:id/enterShippingAddressTV")
    ));

    public Element checkoutCompleteLabel = new Element("checkout Complete Label", List.of(
            new Locator(Strategy.ANDROID_ID, "com.saucelabs.mydemoapp.android:id/completeTV")
    ));

    public Element statusLabel = new Element("status Label", List.of(
            new Locator(Strategy.ANDROID_ID, "com.saucelabs.mydemoapp.android:id/swagTV")
    ));

    public Element continueShoppingLabel = new Element("continue Shopping Label", List.of(
            new Locator(Strategy.ANDROID_ID, "com.saucelabs.mydemoapp.android:id/shoopingBt")
    ));
}

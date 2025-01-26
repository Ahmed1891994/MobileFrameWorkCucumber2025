package Screens.elements;

import Utils.Locator.Locator;
import Utils.Locator.Strategy;

import java.util.List;

public class CheckoutPageElements {
    public List<Locator> title = List.of(
            new Locator(Strategy.ANDROID_ID,"com.saucelabs.mydemoapp.android:id/checkoutTitleTV")
    );

    public List<Locator> fullNameInput = List.of(
            new Locator(Strategy.ANDROID_ID,"com.saucelabs.mydemoapp.android:id/fullNameET")
    );

    public List<Locator> fullNameErrorLabel = List.of(
            new Locator(Strategy.ANDROID_ID,"com.saucelabs.mydemoapp.android:id/fullNameErrorTV")
    );

    public List<Locator> address1Input = List.of(
            new Locator(Strategy.ANDROID_ID,"com.saucelabs.mydemoapp.android:id/address1ET")
    );

    public List<Locator> address1ErrorLabel= List.of(
            new Locator(Strategy.ANDROID_ID,"com.saucelabs.mydemoapp.android:id/address1ErrorTV")
    );

    public List<Locator> address2Input = List.of(
            new Locator(Strategy.ANDROID_ID,"com.saucelabs.mydemoapp.android:id/address2ET")
    );

    public List<Locator> cityInput = List.of(
            new Locator(Strategy.ANDROID_ID,"com.saucelabs.mydemoapp.android:id/cityET")
    );

    public List<Locator> cityErrorLabel= List.of(
            new Locator(Strategy.ANDROID_ID,"com.saucelabs.mydemoapp.android:id/cityErrorTV")
    );

    public List<Locator> stateInput = List.of(
            new Locator(Strategy.ANDROID_ID,"com.saucelabs.mydemoapp.android:id/stateET")
    );

    public List<Locator> zipInput = List.of(
            new Locator(Strategy.ANDROID_ID,"com.saucelabs.mydemoapp.android:id/zipET")
    );

    public List<Locator> zipErrorLabel= List.of(
            new Locator(Strategy.ANDROID_ID,"com.saucelabs.mydemoapp.android:id/zipErrorTV")
    );

    public List<Locator> countryInput = List.of(
            new Locator(Strategy.ANDROID_ID,"com.saucelabs.mydemoapp.android:id/countryET")
    );

    public List<Locator> countryErrorLabel= List.of(
            new Locator(Strategy.ANDROID_ID,"com.saucelabs.mydemoapp.android:id/countryErrorTV")
    );

    public List<Locator> checkoutPaymentButton = List.of(
            new Locator(Strategy.ANDROID_ID,"com.saucelabs.mydemoapp.android:id/checkoutTitleTV")
    );

    public List<Locator> cardFullNameInput = List.of(
            new Locator(Strategy.ANDROID_ID,"com.saucelabs.mydemoapp.android:id/nameET")
    );

    public List<Locator> cardNumberInput = List.of(
            new Locator(Strategy.ANDROID_ID,"com.saucelabs.mydemoapp.android:id/cardNumberET")
    );

    public List<Locator> cardExpirationDateInput = List.of(
            new Locator(Strategy.ANDROID_ID,"com.saucelabs.mydemoapp.android:id/expirationDateET")
    );

    public List<Locator> cardSecurityCodeInput = List.of(
            new Locator(Strategy.ANDROID_ID,"com.saucelabs.mydemoapp.android:id/securityCodeET")
    );

    public List<Locator> cardPaymentButton = List.of(
            new Locator(Strategy.ANDROID_ID,"com.saucelabs.mydemoapp.android:id/paymentBtn")
    );

    public List<Locator> billingAddressCheckBox = List.of(
            new Locator(Strategy.ANDROID_ID,"com.com.saucelabs.mydemoapp.android:id/billingAddressCB")
    );

    public List<Locator> reviewYourOrderLabel = List.of(
            new Locator(Strategy.ANDROID_ID,"com.saucelabs.mydemoapp.android:id/enterShippingAddressTV")
    );

    public List<Locator> checkoutCompleteLabel = List.of(
            new Locator(Strategy.ANDROID_ID,"com.saucelabs.mydemoapp.android:id/completeTV")
    );

    public List<Locator> statusLabel = List.of(
            new Locator(Strategy.ANDROID_ID,"com.saucelabs.mydemoapp.android:id/swagTV")
    );

    public List<Locator> continueShoppingLabel = List.of(
            new Locator(Strategy.ANDROID_ID,"com.saucelabs.mydemoapp.android:id/shoopingBt")
    );
}

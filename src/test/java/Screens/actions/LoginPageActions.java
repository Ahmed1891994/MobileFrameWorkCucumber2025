package Screens.actions;

import Context.Context;
import Data.UserData;
import Data.UserType;
import Driver.Driver;
import Screens.elements.LoginPageElements;

public class LoginPageActions {
    private final LoginPageElements elements;
    private final Driver driver;
    private Context context;

    public LoginPageActions(Context context) {
        this.context = context;
        this.driver = context.getDriver();
        elements = new LoginPageElements();
    }

    public boolean isInScreen() {
        return driver.isDisplayed(elements.title);
    }

    public void enterUsername(String username) {
        driver.findElement(elements.usernameInput).sendKeys();
    }

    public void enterUsername(UserType userType) {
        UserData userData = (UserData) context.getTestData("UserData");
        String username = userType.getCredentials(userData).getUsername();
        driver.findElement(elements.usernameInput).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(elements.passwordInput).sendKeys(password);
    }

    public void enterPassword(UserType userType) {
        UserData userData = (UserData) context.getTestData("UserData");
        String password = userType.getCredentials(userData).getPassword();
        driver.findElement(elements.passwordInput).sendKeys(password);
    }

    public void pressLoginButton() {
        driver.findElement(elements.loginButton).click();
    }
}

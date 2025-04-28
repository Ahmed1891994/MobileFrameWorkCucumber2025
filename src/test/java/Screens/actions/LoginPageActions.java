package Screens.actions;

import Context.Context;
import Data.UserData;
import Data.UserType;
import Driver.Driver;
import Screens.elements.LoginPageElements;
import io.qameta.allure.Step;

public class LoginPageActions {
    private final LoginPageElements elements;
    private final Driver driver;
    private Context context;

    public LoginPageActions(Context context) {
        this.context = context;
        this.driver = context.getDriver();
        elements = new LoginPageElements();
    }

    @Step("is In Login Screen")
    public boolean isInScreen() {
        return driver.isDisplayed(elements.title);
    }

    @Step("enter username {username}")
    public void enterUsername(String username) {
        driver.findElement(elements.usernameInput).sendKeys();
    }

    @Step("enter username {username}")
    public void enterUsername(UserType userType) {
        UserData userData = (UserData) context.getTestData("UserData");
        String username = userType.getCredentials(userData).getUsername();
        driver.findElement(elements.usernameInput).sendKeys(username);
    }

    @Step("enter password {password}")
    public void enterPassword(String password) {
        driver.findElement(elements.passwordInput).sendKeys(password);
    }

    @Step("enter password {password}")
    public void enterPassword(UserType userType) {
        UserData userData = (UserData) context.getTestData("UserData");
        String password = userType.getCredentials(userData).getPassword();
        driver.findElement(elements.passwordInput).sendKeys(password);
    }

    @Step("press login button")
    public void pressLoginButton() {
        driver.findElement(elements.loginButton).click();
    }
}

package StepDefinition;

import Context.Context;
import Data.UserType;
import Screens.actions.LoginPageActions;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;
import org.testng.Assert;

public class LoginPageSteps {
    Context context;
    LoginPageActions loginPageActions;

    public LoginPageSteps(Context context) {
        this.context = context;
        loginPageActions = new LoginPageActions(context);
    }

    @Step
    @When("user login with valid {} credentials")
    public void loginWithValidCredentials(UserType usertype) {
        Assert.assertTrue(loginPageActions.isInScreen());
        loginPageActions.enterUsername(usertype);
        loginPageActions.enterPassword(usertype);
        loginPageActions.pressLoginButton();
    }


}

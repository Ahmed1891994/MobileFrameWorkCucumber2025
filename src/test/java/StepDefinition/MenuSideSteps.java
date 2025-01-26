package StepDefinition;

import Context.Context;
import Screens.actions.MenuSideActions;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class MenuSideSteps {
    Context context;
    MenuSideActions menuSideActions;

    public MenuSideSteps(Context context) {
        this.context = context;
        menuSideActions = new MenuSideActions(context);
    }

    @When("User presses on {} in menu side")
    public void Userpressesoniteminmenuside(String menuChoice) {
        menuSideActions.chooseMenuItem(menuChoice);
    }

    @When("User can see {} on menu side")
    public void userCanSeeCurlyBracesOnMenuSide(String menuChoice) {
        Assert.assertTrue(menuSideActions.isMenuItemExist(menuChoice));
    }
}

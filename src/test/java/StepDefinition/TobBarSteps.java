package StepDefinition;

import Context.Context;
import Screens.actions.TopBarActions;
import io.cucumber.java.en.When;

public class TobBarSteps {
    Context context;
    TopBarActions topBarActions;

    public TobBarSteps(Context context) {
        this.context = context;
        topBarActions = new TopBarActions(context);
    }

    @When("User presses burger button")
    public void Userpressesoniteminmenuside() {
        topBarActions.clickOnBurgerButton();
    }
}

package StepDefinition;

import Context.Context;
import Screens.actions.CatalogPageActions;
import io.cucumber.java.en.Given;
import io.qameta.allure.Step;
import org.testng.Assert;

public class CatalogPageSteps {
    Context context;
    CatalogPageActions catalogPageActions;

    public CatalogPageSteps(Context context) {
        this.context = context;
        catalogPageActions = new CatalogPageActions(context);
    }

    @Step
    @Given("User is in Catalog Page")
    public void userIsInCatalogPage() {
        Assert.assertTrue(catalogPageActions.isInScreen());
    }
}
package Screens.actions;

import Context.Context;
import Driver.Driver;
import Screens.elements.CatalogPageElements;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CatalogPageActions extends CatalogPageElements {
    private final CatalogPageElements elements;
    private final Driver driver;

    public CatalogPageActions(Context context) {
        this.driver = context.getDriver();
        elements = new CatalogPageElements();
    }

    public boolean isInScreen()
    {
        return driver.isDisplayed(elements.productTitle);
    }

    public void clickOnSortButton()
    {
        driver.findElement(elements.sortButton).click();
    }

    public void clickOnProductByName(String productName)
    {
        driver.findElement(elements.productByName(productName)).click();
    }

    public void clickOnFirstProductContainsName(String productNamePart)
    {
        driver.findElement(elements.productByPartOfName(productNamePart)).click();
    }

    public String getPriceOfProduct(String productName)
    {
        return driver.findElement(elements.priceOfProduct(productName)).getText().replaceAll("[^0-9.]","");
    }

    public void sortBy(String type,String order)
    {
        //Type is Name or Price and for order Asc or Des
        driver.findElement(elements.sortingOptions(type,order)).click();
    }

}

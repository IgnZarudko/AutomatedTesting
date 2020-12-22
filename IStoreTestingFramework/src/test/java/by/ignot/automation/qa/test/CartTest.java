package by.ignot.automation.qa.test;

import by.ignot.automation.qa.action.UserAction;
import by.ignot.automation.qa.page.ItemPage;
import by.ignot.automation.qa.util.ItemDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTest extends CommonSetup{

    @Test(dataProvider = "getItemToAddUrls", dataProviderClass = ItemDataProvider.class)
    public void addOneItemToCartTest(String itemUrl) {
        ItemPage itemPage = new ItemPage(driver, itemUrl);

        String itemVendorCode = itemPage
                .openPage()
                .getItemVendorCode();

        String addedVendorCode = itemPage
                .addToCart()
                .goToCart()
                .getItemVendorCode();

        Assert.assertEquals(itemVendorCode, addedVendorCode);
    }


    @Test(dataProvider = "getSeveralItemToAddUrls", dataProviderClass = ItemDataProvider.class)
    public void addSeveralItemsToCartTest(String[] itemUrls) {
        int amountOfItemsExpected = itemUrls.length;

        int amountOfItemsActual = UserAction
                .addItemsAndGoToCart(new ItemPage(driver), itemUrls)
                .getItemsAmount();

        Assert.assertEquals(amountOfItemsActual, amountOfItemsExpected);
    }

    @Test(dataProvider = "getSeveralItemToAddUrls", dataProviderClass = ItemDataProvider.class)
    public void deleteItemFromCartTest(String[] itemUrls) {
        int amountOfItemsExpected = itemUrls.length - 1;

        int amountOfItemsActual = UserAction
                .addItemsAndGoToCart(new ItemPage(driver), itemUrls)
                .deleteOneItem()
                .getItemsAmount();

        Assert.assertEquals(amountOfItemsActual, amountOfItemsExpected);
    }
}

package by.ignot.automation.qa.test;

import by.ignot.automation.qa.page.ItemPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTest extends CommonSetup{

    @Test
    public void addToCartTest(String itemUrl){
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
}

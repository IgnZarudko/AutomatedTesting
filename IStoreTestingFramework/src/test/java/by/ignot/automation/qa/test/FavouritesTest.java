package by.ignot.automation.qa.test;

import by.ignot.automation.qa.action.UserAction;
import by.ignot.automation.qa.page.FavouritesPage;
import by.ignot.automation.qa.page.ItemPage;
import by.ignot.automation.qa.page.LoginPage;
import by.ignot.automation.qa.util.ItemDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FavouritesTest extends CommonSetup {

    @Test(dataProvider = "getItemToAddUrls", dataProviderClass = ItemDataProvider.class)
    public void addToFavouritesRequiresLoginTest(String itemUrl) {
        new ItemPage(driver, itemUrl)
                .openPage()
                .addToFavourites();

        boolean isLoginRequired = new LoginPage(driver).isLoginPageOpened();

        Assert.assertTrue(isLoginRequired);
    }

    @Test(dataProvider = "getItemToAddUrls", dataProviderClass = ItemDataProvider.class)
    public void addToFavouritesTest(String itemUrl) {
        int favouriteItemsAmountBefore = UserAction
                .loginWithDefaultCredentials(new LoginPage(driver))
                .goToFavourites()
                .getFavouriteItemsAmount();

        int favouriteItemsAmountAfter = new ItemPage(driver, itemUrl)
                .openPage()
                .addToFavourites()
                .goToFavourites()
                .getFavouriteItemsAmount();

        new FavouritesPage(driver)
                .openPage()
                .deleteOneItem();

        Assert.assertTrue(favouriteItemsAmountBefore < favouriteItemsAmountAfter);
    }

    @Test(dataProvider = "getItemToAddUrls", dataProviderClass = ItemDataProvider.class)
    public void deleteFromFavouritesTest(String itemUrl) {

        UserAction.loginWithDefaultCredentials(new LoginPage(driver))
                .goToFavourites();

        FavouritesPage favouritesPage = new ItemPage(driver, itemUrl)
                .openPage()
                .addToFavourites()
                .goToFavourites();

        int favouriteItemsAmountBefore = favouritesPage
                .getFavouriteItemsAmount();

        int favouriteItemsAmountAfter = favouritesPage
                .deleteOneItem()
                .getFavouriteItemsAmount();

        Assert.assertTrue(favouriteItemsAmountBefore > favouriteItemsAmountAfter);
    }

}

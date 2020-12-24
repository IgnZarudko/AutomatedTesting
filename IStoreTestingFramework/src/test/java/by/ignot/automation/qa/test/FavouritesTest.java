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
        new ItemPage(driverProvider.getThreadedDriver(), itemUrl)
                .openPage()
                .addToFavourites();

        boolean isLoginRequired = new LoginPage(driverProvider.getThreadedDriver()).isLoginPageOpened();

        Assert.assertTrue(isLoginRequired);
    }

    @Test(dataProvider = "getItemToAddUrls", dataProviderClass = ItemDataProvider.class)
    public void addToFavouritesTest(String itemUrl) {
        int favouriteItemsAmountBefore = UserAction
                .loginWithDefaultCredentials(new LoginPage(driverProvider.getThreadedDriver()))
                .goToFavourites()
                .getFavouriteItemsAmount();

        int favouriteItemsAmountAfter = new ItemPage(driverProvider.getThreadedDriver(), itemUrl)
                .openPage()
                .addToFavourites()
                .goToFavourites()
                .getFavouriteItemsAmount();

        new FavouritesPage(driverProvider.getThreadedDriver())
                .openPage()
                .deleteOneItem();

        Assert.assertTrue(favouriteItemsAmountBefore < favouriteItemsAmountAfter);
    }

    @Test(dataProvider = "getItemToAddUrls", dataProviderClass = ItemDataProvider.class)
    public void deleteFromFavouritesTest(String itemUrl) {

        UserAction.loginWithDefaultCredentials(new LoginPage(driverProvider.getThreadedDriver()))
                .goToFavourites();

        FavouritesPage favouritesPage = new ItemPage(driverProvider.getThreadedDriver(), itemUrl)
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

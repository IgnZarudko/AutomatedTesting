package by.ignot.automation.qa.page;

import by.ignot.automation.qa.util.LogProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class FavouritesPage extends CommonPage{

    protected String pageUrl = "https://i-store.by/account/favorites";

    @FindAll({
            @FindBy(xpath = "//button[@class=\"btn btn-favorite\"]")
    })
    private List<WebElement> favouriteItems;

    public FavouritesPage(WebDriver driver){
        super(driver, LogProvider.getLog());
        PageFactory.initElements(driver, this);
    }

    public FavouritesPage(WebDriver driver, String pageURL){
        super(driver, LogProvider.getLog(), pageURL);
        PageFactory.initElements(driver, this);
    }

    @Override
    @SuppressWarnings("unchecked")
    public FavouritesPage openPage() {
        log.info("Opening favourites page");
        driver.get(this.pageUrl);
        return this;
    }

    public FavouritesPage deleteOneItem() {
        log.info("Deleting one item from favourites");

        new WebDriverWait(driver, WAIT_TIMEOUT)
                .until(ExpectedConditions.elementToBeClickable(favouriteItems.get(0)));

        favouriteItems.get(0).click();

        openPage();
        return this;
    }

    public int getFavouriteItemsAmount() {
        new WebDriverWait(driver, WAIT_TIMEOUT)
                .until(ExpectedConditions.visibilityOfAllElements(favouriteItems));

        return favouriteItems.size();
    }
}

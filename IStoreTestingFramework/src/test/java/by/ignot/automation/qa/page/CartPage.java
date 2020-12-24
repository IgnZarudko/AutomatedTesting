package by.ignot.automation.qa.page;

import by.ignot.automation.qa.util.LogProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CartPage extends CommonPage{

    protected String pageUrl = "https://i-store.by/cart";

    private String itemTitleXPath = "//div[@class=\"wrapper\"]//div[@class=\"header ng-binding\"][@ng-bind=\"x.name\"]";
    private By itemTitle = By.xpath(itemTitleXPath);

    @FindAll({
            @FindBy(xpath = "//button[@class=\"pull-right button-delete visible-lg\"]")
    })
    List<WebElement> deleteItemButtons;

    @FindAll({
            @FindBy(xpath = "//div[@class=\"item-cross-sale\"]")
    })
    List<WebElement> crossSaleOfferings;


    public CartPage(WebDriver driver){
        super(driver, LogProvider.getLog());
        PageFactory.initElements(driver, this);
    }

    public CartPage(WebDriver driver, String pageURL){
        super(driver, LogProvider.getLog(), pageURL);
        PageFactory.initElements(driver, this);
    }

    @Override
    @SuppressWarnings("unchecked")
    public CartPage openPage(){
        log.info("Opening cart page");
        driver.get(this.pageUrl);
        return this;
    }

    public CartPage deleteOneItem() {
        log.info("Deleting one item");

        waitUntilBeClickable(deleteItemButtons.get(0));

        deleteItemButtons.get(0).click();

        openPage();

        return this;
    }

    public String getItemTitle(){
        log.info("Getting first's item title");

        return waitPresenceOfElement(itemTitle).getText();
    }

    public int getItemsAmount(){
        log.info("Counting items in cart");
        waitVisibilityOfAllElements(deleteItemButtons);
        return deleteItemButtons.size();
    }
}

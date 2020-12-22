package by.ignot.automation.qa.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CartPage extends CommonPage{

    protected String pageUrl = "https://i-store.by/cart";

    @FindBy(xpath = "//span[@ng-bind-html=\"translations.productSku + ' ' + x.sku | trusted\"]")
    private WebElement itemVendorCode;

    @FindAll({
            @FindBy(xpath = "//button[@class=\"pull-right button-delete visible-lg\"]")
    })
    List<WebElement> deleteItemButtons;

    @FindAll({
            @FindBy(xpath = "//div[@class=\"item-cross-sale\"]")
    })
    List<WebElement> crossSaleOfferings;


    public CartPage(WebDriver driver){
        super(driver);
    }

    public CartPage(WebDriver driver, String pageURL){
        super(driver, pageURL);
    }

    @Override
    @SuppressWarnings("unchecked")
    public CartPage openPage(){
        driver.get(this.pageUrl);
        return this;
    }

    public CartPage deleteOneItem() {
        new WebDriverWait(driver, WAIT_TIMEOUT)
                .until(ExpectedConditions.elementToBeClickable(deleteItemButtons.get(0)));

        deleteItemButtons.get(0).click();

        openPage();

        return this;
    }

    public String getItemVendorCode(){
        new WebDriverWait(driver, WAIT_TIMEOUT)
                .until(_driver -> itemVendorCode.getText().split(" ").length == 2);

        return itemVendorCode.getText().split(" ")[1];
    }

    public int getItemsAmount(){
        new WebDriverWait(driver, WAIT_TIMEOUT)
                .until(ExpectedConditions.visibilityOfAllElements(deleteItemButtons));

        return deleteItemButtons.size();
    }
}

package by.ignot.automation.qa.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ItemPage extends CommonPage{
    @FindBy(xpath = "//button[@ng-if=\"!linkOrButton(page.product.id) && page.product.prices.status == 0\"]")
    private WebElement addToCartButton;

    @FindBy(xpath = "//a[@ng-href=\"cart\"]")
    private WebElement afterAddToCartButton;

    @FindBy(xpath = "//a[@ng-if=\"linkOrButton(page.product.id)\"][@class=\"button ng-scope\"]")
    private WebElement goToCartButton;

    @FindBy(xpath = "//span[@itemprop=\"sku\"]")
    private WebElement itemVendorCode;

    public ItemPage(WebDriver driver){
        super(driver);
    }

    public ItemPage(WebDriver driver, String pageURL){
        super(driver, pageURL);
    }

    @Override
    @SuppressWarnings("unchecked")
    public ItemPage openPage(){
        driver.get(pageURL);
        return this;
    }

    public String getItemVendorCode(){
        new WebDriverWait(driver, 10)
                .until(_driver -> !this.itemVendorCode.getText().isEmpty());
        return itemVendorCode.getText();
    }

    public ItemPage addToCart() {
        addToCartButton.click();
        new WebDriverWait(driver, WAIT_TIMEOUT)
                .until(ExpectedConditions.elementToBeClickable(afterAddToCartButton));
        return this;
    }

    public CartPage goToCart(){
        goToCartButton.click();
        return new CartPage(driver);
    }

}

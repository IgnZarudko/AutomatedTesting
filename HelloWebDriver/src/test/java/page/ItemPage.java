package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.regex.Pattern;

public class ItemPage extends Page{
    @FindBy(xpath = "//button[@ng-if=\"!linkOrButton(page.product.id) && page.product.prices.status == 0\"]")
    private WebElement addToCartButton;

    @FindBy(xpath = "//a[@ng-if=\"linkOrButton(page.product.id)\"][@class=\"button ng-scope\"]")
    private WebElement goToCartButton;

    @FindBy(xpath = "//span[@itemprop=\"sku\"]")
    private WebElement itemVendorCode;

    public ItemPage(WebDriver driver, String pageURL){
        super(driver, pageURL);
    }

    public void addToCart(){
        addToCartButton.click();
    }

    public void goToCart(){
        goToCartButton.click();
    }

    public String getVendorCode(){
        return itemVendorCode.getText();
    }
}

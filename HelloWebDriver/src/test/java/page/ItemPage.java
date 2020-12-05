package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ItemPage {
    private WebDriver driver;
    private String pageURL;

    @FindBy(xpath = "//button[@ng-if=\"!linkOrButton(page.product.id) && page.product.prices.status == 0\"]")
    private WebElement addToCartButton;

    @FindBy(xpath = "//a[@ng-if=\"linkOrButton(page.product.id)\"][@class=\"button ng-scope\"]")
    private WebElement goToCartButton;

    @FindBy(xpath = "//span[@itemprop=\"sku\"]")
    private WebElement itemVendorCode;

    public ItemPage(WebDriver driver, String pageURL){
        PageFactory.initElements(driver, this);
        this.pageURL = pageURL;
        this.driver = driver;
    }

    public void openPage(){
        driver.get(pageURL);
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

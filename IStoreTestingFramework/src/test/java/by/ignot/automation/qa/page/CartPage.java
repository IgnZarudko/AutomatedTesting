package by.ignot.automation.qa.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage extends CommonPage{
    @FindBy(xpath = "//span[starts-with(text(),'Артикул')]")
    private WebElement itemVendorCode;

    public CartPage(WebDriver driver){
        super(driver);
    }

    public CartPage(WebDriver driver, String pageURL){
        super(driver, pageURL);
    }

    @Override
    @SuppressWarnings("unchecked")
    public CartPage openPage(){
        driver.get(pageURL);
        return this;
    }

    public String getItemVendorCode(){
        new WebDriverWait(driver, WAIT_TIMEOUT)
                .until(_driver -> itemVendorCode.getText().split(" ").length == 2);

        return itemVendorCode.getText().split(" ")[1];
    }
}

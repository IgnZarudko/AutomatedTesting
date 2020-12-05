package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends Page {

    public CartPage(WebDriver driver, String pageURL){
        super(driver, pageURL);
    }

    @FindBy(xpath = "//span[starts-with(text(),'Артикул')]")
    private WebElement itemVendorCode;

    public String getItemVendorCode(){
        return itemVendorCode.getText();
    }
}

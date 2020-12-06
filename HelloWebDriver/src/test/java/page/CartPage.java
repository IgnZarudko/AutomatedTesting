package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage extends Page {

    public CartPage(WebDriver driver, String pageURL){
        super(driver, pageURL);
    }

    @FindBy(xpath = "//span[starts-with(text(),'Артикул')]")
    private WebElement itemVendorCode;

    public void waitUntilVendorCodeIsAvailable(){
        new WebDriverWait(driver, 10)
                .until(_driver -> this.getItemVendorCodeLine().split(" ").length == 2);
    }

    private String getItemVendorCodeLine() {
        return itemVendorCode.getText();
    }

    public String getItemVendorCode(){
        return itemVendorCode.getText().split(" ")[1];
    }
}

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

    public String getItemVendorCode(){
        new WebDriverWait(driver, 10)
                .until(_driver -> itemVendorCode.getText().split(" ").length == 2);

        return itemVendorCode.getText().split(" ")[1];
    }
}

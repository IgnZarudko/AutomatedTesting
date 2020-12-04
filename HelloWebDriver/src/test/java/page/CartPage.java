package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {
    public WebDriver driver;
    public CartPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//span[starts-with(text(),'Артикул')]")
    private WebElement vendorCodeOfItem;

    public String getVendorCodeOfItem(){
        return vendorCodeOfItem.getText();
    }
}

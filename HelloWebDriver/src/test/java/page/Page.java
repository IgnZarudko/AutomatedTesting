package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class Page {
    protected WebDriver driver;

    protected String pageURL;

    public Page(WebDriver driver, String pageURL){
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.pageURL = pageURL;
    }

    public void openPage(){
        driver.get(pageURL);
    }
}

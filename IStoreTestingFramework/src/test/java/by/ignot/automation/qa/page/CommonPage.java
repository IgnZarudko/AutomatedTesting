package by.ignot.automation.qa.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class CommonPage {
    protected WebDriver driver;

    protected String pageURL = "https://i-store.by/";

    protected final int WAIT_TIMEOUT = 15;

    public CommonPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public CommonPage(WebDriver driver, String pageURL){
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.pageURL = pageURL;
    }

    public void setPageURL(String pageURL) {
        this.pageURL = pageURL;
    }

    public abstract <T extends CommonPage> T openPage();
}

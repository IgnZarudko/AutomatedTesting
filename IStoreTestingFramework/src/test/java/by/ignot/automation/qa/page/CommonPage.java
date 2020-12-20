package by.ignot.automation.qa.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class CommonPage {
    protected WebDriver driver;

    protected String pageURL;

    protected final int WAIT_TIMEOUT = 10;

    public CommonPage(WebDriver driver, String pageURL){
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.pageURL = pageURL;
    }

    public abstract <T extends CommonPage> T openPage();


}

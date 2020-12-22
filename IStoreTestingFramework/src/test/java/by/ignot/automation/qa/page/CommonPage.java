package by.ignot.automation.qa.page;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public abstract class CommonPage {
    protected WebDriver driver;
    protected String pageURL = "https://i-store.by/";
    protected final int WAIT_TIMEOUT = 15;
    protected final Logger log;

    public CommonPage(WebDriver driver, Logger log){
        this.driver = driver;
        this.log = log;
    }

    public CommonPage(WebDriver driver, Logger log, String pageURL){
        this(driver, log);
        this.pageURL = pageURL;
    }

    public void setPageURL(String pageURL) {
        this.pageURL = pageURL;
    }

    public abstract <T extends CommonPage> T openPage();
}

package by.ignot.automation.qa.page;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

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

    protected WebElement waitUntilBeClickable(WebElement webElement){
        return new WebDriverWait(driver, WAIT_TIMEOUT)
                .until(ExpectedConditions.elementToBeClickable(webElement));
    }

    protected WebElement waitVisibilityOfElement(WebElement webElement){
        return new WebDriverWait(driver, WAIT_TIMEOUT)
                .until(ExpectedConditions.visibilityOf(webElement));
    }

    protected List<WebElement> waitVisibilityOfAllElements(List<WebElement> webElements) {
        return new WebDriverWait(driver, WAIT_TIMEOUT)
                .until(ExpectedConditions.visibilityOfAllElements(webElements));
    }


    public void setPageURL(String pageURL) {
        this.pageURL = pageURL;
    }

    public abstract <T extends CommonPage> T openPage();
}

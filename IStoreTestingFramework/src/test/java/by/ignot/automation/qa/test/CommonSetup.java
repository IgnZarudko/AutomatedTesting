package by.ignot.automation.qa.test;

import by.ignot.automation.qa.driver.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public class CommonSetup {
    protected WebDriver driver;

    @BeforeMethod()
    public void prepareAll() {
        driver = DriverSingleton.getDriver();
    }

    @AfterMethod()
    public void closeAll() {
        driver.quit();
        DriverSingleton.closeDriver();
    }
}

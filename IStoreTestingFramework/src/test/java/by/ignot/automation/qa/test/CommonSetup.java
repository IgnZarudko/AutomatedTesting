package by.ignot.automation.qa.test;

import by.ignot.automation.qa.driver.DriverProvider;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public class CommonSetup {
    protected DriverProvider driverProvider;

    public CommonSetup() {
        driverProvider = new DriverProvider();
    }

    @BeforeMethod(alwaysRun = true)
    public void prepareAll() {
        driverProvider.updateThreadedDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void closeAll() {
        driverProvider.shutThreadedDriver();
    }
}

package by.ignot.automation.qa.test;

import by.ignot.automation.qa.driver.DriverProvider;
import by.ignot.automation.qa.util.LogProvider;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public class CommonSetup {
    // providing different drivers for different threads
    public static DriverProvider driverProvider;

    public CommonSetup() {
        driverProvider = new DriverProvider();
    }

    @BeforeMethod(alwaysRun = true)
    public void browserDriverSetup() {
        LogProvider.getLog().info("Initializing browser");
        driverProvider.updateContextDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void browserDriverShutDown(){
        LogProvider.getLog().info("Shutting down browser");
        driverProvider.shutContextDriver();
    }
}

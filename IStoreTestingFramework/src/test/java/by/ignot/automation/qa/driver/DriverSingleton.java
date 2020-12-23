package by.ignot.automation.qa.driver;

import by.ignot.automation.qa.util.LogProvider;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;

import java.util.concurrent.TimeUnit;

public class DriverSingleton {
    private static WebDriver driver;

    private DriverSingleton(){}

    public static WebDriver getDriver(){

        if (driver == null) {
            switch(System.getProperty("browser")) {
                case "opera":{
                    LogProvider.getLog().info("setting up operadriver");
                    WebDriverManager.operadriver().setup();
                    OperaOptions operaOptions = new OperaOptions();
                    operaOptions.addArguments("--headless");
                    operaOptions.addArguments("disable-gpu");
                    operaOptions.addArguments("--window-size=1920,1080");
                    driver = new OperaDriver(operaOptions);
                    break;
                }
                default: {
                    LogProvider.getLog().info("setting up chromedriver");
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--headless");
                    chromeOptions.addArguments("disable-gpu");
                    chromeOptions.addArguments("--window-size=1920,1080");
                    driver = new ChromeDriver(chromeOptions);
                }
            }
            LogProvider.getLog().info("making common preparations");
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        }

        return driver;
    }

    public static void closeDriver(){
        driver.quit();
        driver = null;
    }
}

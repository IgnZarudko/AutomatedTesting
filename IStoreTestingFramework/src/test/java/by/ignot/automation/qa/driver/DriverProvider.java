package by.ignot.automation.qa.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class DriverProvider {
    // stores thread id and driver for each test context
    private final HashMap<Long, WebDriver> drivers;

    public DriverProvider() {
        drivers = new HashMap<>();
    }

    public void updateContextDriver() {
        long threadId = Thread.currentThread().getId();
        drivers.put(threadId, DriverProvider.getNewDriver());
    }

    public WebDriver getContextDriver() {
        long threadId = Thread.currentThread().getId();
        return drivers.get(threadId);
    }

    public void shutContextDriver() {
        long threadId = Thread.currentThread().getId();
        WebDriver currentDriver = drivers.get(threadId);
        if (currentDriver != null) {
            currentDriver.close();
            currentDriver.quit();
            drivers.remove(threadId);
        }
    }

    private static WebDriver getNewDriver() {
        WebDriver driver;
        switch(System.getProperty("browser")) {
            case "opera":{
                WebDriverManager.operadriver().setup();
                OperaOptions operaOptions = new OperaOptions();
                operaOptions.addArguments("--headless");
                operaOptions.addArguments("disable-gpu");
                operaOptions.addArguments("--window-size=1920,1080");
                driver = new OperaDriver(operaOptions);
                break;
            }
            default: {
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless");
                chromeOptions.addArguments("disable-gpu");
                chromeOptions.addArguments("--window-size=1920,1080");
                driver = new ChromeDriver(chromeOptions);
            }
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        return driver;
    }
}

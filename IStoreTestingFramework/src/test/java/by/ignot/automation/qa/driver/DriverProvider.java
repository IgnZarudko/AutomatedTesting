package by.ignot.automation.qa.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;

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
        WebDriverManager.chromedriver().setup();
        // support for no-display linux instances on ci
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("disable-gpu");
        chromeOptions.addArguments("window-size=1280,720");
        return new ChromeDriver(chromeOptions);
    }
}

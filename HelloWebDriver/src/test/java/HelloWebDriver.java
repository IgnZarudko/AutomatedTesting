import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class HelloWebDriver {
    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        LoginPage loginPage = new LoginPage(driver);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        driver.get("https://i-store.by/");

        loginPage.clickCloseAnnoyingAdButton();
        Thread.sleep(2000);

        loginPage.clickToLoginButton();
        Thread.sleep(2000);

        loginPage.enterUsername("+375292670264");
        Thread.sleep(2000);

        loginPage.clickConfirmUsernameButton();
        Thread.sleep(2000);

        loginPage.enterPassword("1TROLLFACE1_v");
        Thread.sleep(2000);

        loginPage.clickConfirmPasswordButton();
        Thread.sleep(2000);


        driver.quit();
    }
}

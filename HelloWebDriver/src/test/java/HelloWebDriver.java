import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Driver;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class HelloWebDriver {

    WebDriver driver;
    LoginPage loginPage;
    ProfilePage profilePage;

    @BeforeClass
    public void prepareAll(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");

        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        profilePage = new ProfilePage(driver);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void loginTest(){
        driver.get("https://i-store.by/");

        loginPage.clickCloseAnnoyingAdButton();

        loginPage.clickToLoginButton();

        loginPage.enterUsername("+375292670264");

        loginPage.clickConfirmUsernameButton();

        loginPage.enterPassword("1TROLLFACE1_v");

        loginPage.clickConfirmPasswordButton();

        new WebDriverWait(driver, Duration.ofSeconds(10).toSeconds())
                .until(d -> d.findElement(By.xpath("/html/body/div[1]/div[1]/header/div/div/div/div/div/div[3]/div/button/span[1]")).getText());



        driver.get("https://i-store.by/account/profile");

        String actual = profilePage.getUsername();

        Assert.assertNotEquals(actual, "Ignot");

        driver.quit();
    }
}

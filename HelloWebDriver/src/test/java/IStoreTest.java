import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.CartPage;
import page.ItemPage;
import page.LandingPage;
import read.CsvReader;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class IStoreTest {

    WebDriver driver;

    @BeforeMethod
    public void setUpEnvironment() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("disable-gpu");
        chromeOptions.addArguments("window-size=1920,1080");

        driver = new ChromeDriver(chromeOptions);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void loginPositiveTestNoPageObject() {
        driver.get("https://i-store.by/");

        String[][] data = CsvReader.getLoginData();

        driver.findElement(By.xpath("//button[@class=\"sp-prompt-btn sp-accept-btn sp_notify_prompt\"]")).click();

        driver.findElement(By.xpath("//div[@class=\"block-item\"]/button[@ng-if=\"!isLoggedIn\"]")).click();

        driver.findElement(By.xpath("//*[@id=\"login\"]")).sendKeys(data[0][0]);

        driver.findElement(By.xpath("//button[@ng-click=\"account.onSetModalIndex(1, signInForm)\"]")).click();

        driver.findElement(By.xpath("//*[@name=\"password\"]")).sendKeys(data[0][1]);

        driver.findElement(By.xpath("//button[@ng-bind=\"translations.accountLoginBtnText\"]")).click();

        new WebDriverWait(driver, Duration.ofSeconds(10).getSeconds())
                .until(d -> !d.findElement(By.xpath("//span[@ng-bind=\"user.info.first_name || user.email || translations.accountHeaderNoNamePhrase\"]"))
                        .getText().equals("Привет!"));


        String usernameActual = driver.findElement(By.xpath("//span[@ng-bind=\"user.info.first_name || user.email || translations.accountHeaderNoNamePhrase\"]")).getText();

        Assert.assertEquals(usernameActual, "Ignot");
    }

    @Test
    public void loginPositiveTest() {
        String[][] data = CsvReader.getLoginData();

        String usernameActual = new LandingPage(driver, "https://i-store.by/")
                .openPage()
                .closeAnnoyingAd()
                .goToLogin()
                .enterUsername(data[0][0])
                .confirmUsername()
                .enterPassword(data[0][1])
                .confirmPassword().getProfileButtonName();

        Assert.assertEquals(usernameActual, "Ignot");
    }

    @Test
    public void addToCartTest() {
        ItemPage itemPage = new ItemPage(driver, "https://i-store.by/ipad/ipad-air-109/ipad-air-4-64-gb-wi-fi-seryy-kosmos-myfm2rka");

        itemPage.openPage();

        String vendorCodeExpected = itemPage.getVendorCode();

        itemPage.addToCart()
                .goToCart();

        String vendorCodeActual = new CartPage(driver, "https://i-store.by/cart").getItemVendorCode();

        Assert.assertEquals(vendorCodeActual, vendorCodeExpected);
    }


    @AfterMethod
    public void quitWebDriver(){
        driver.quit();
    }
}

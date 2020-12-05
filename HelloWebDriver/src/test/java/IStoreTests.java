import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import page.CartPage;
import page.ItemPage;
import page.LandingPage;
import reader.CsvReader;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class IStoreTests {

    WebDriver driver;

    @BeforeClass
    public void setUpEnvironment(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("disable-gpu");
        chromeOptions.addArguments("window-size=1920,1080");

        driver = new ChromeDriver(chromeOptions);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void loginPositiveTest() {
        LandingPage landingPage = new LandingPage(driver, "https://i-store.by/");

        landingPage.openPage();

        String[][] data = CsvReader.getLoginData();

        landingPage.closeAnnoyingAd();

        landingPage.goToLogin();

        landingPage.enterUsername(data[0][0]);

        landingPage.confirmUsername();

        landingPage.enterPassword(data[0][1]);

        landingPage.confirmPassword();

        new WebDriverWait(driver, Duration.ofSeconds(10).getSeconds())
                .until(d -> !d.findElement(By.xpath("//span[@ng-bind=\"user.info.first_name || user.email || translations.accountHeaderNoNamePhrase\"]"))
                        .getText().equals("Привет!"));


        String usernameActual = driver.findElement(By.xpath("//span[@ng-bind=\"user.info.first_name || user.email || translations.accountHeaderNoNamePhrase\"]")).getText();

        Assert.assertEquals(usernameActual, "Ignot");

    }

    @Test
    public void addToCartTest() {
        ItemPage itemPage = new ItemPage(driver, "https://i-store.by/ipad/ipad-air-109/ipad-air-4-64-gb-wi-fi-seryy-kosmos-myfm2rka");
        CartPage cartPage = new CartPage(driver);

        itemPage.openPage();

        new WebDriverWait(driver, Duration.ofSeconds(10).getSeconds())
                .until(driver -> !itemPage.getVendorCode().isEmpty());

        String vendorCodeExpected = itemPage.getVendorCode();

        itemPage.addToCart();

        itemPage.goToCart();

        new WebDriverWait(driver, Duration.ofSeconds(10).getSeconds())
                .until(driver -> cartPage.getItemVendorCode().split(" ").length == 2);

        String vendorCodeActual = cartPage.getItemVendorCode().split(" ")[1];

        Assert.assertEquals(vendorCodeActual, vendorCodeExpected);
    }


    @AfterClass
    public void quitWebDriver(){
        driver.quit();
    }
}

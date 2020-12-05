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

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class IStoreTests {

    WebDriver driver;
    LandingPage landingPage;
    ItemPage itemPage;
    CartPage cartPage;

    @BeforeClass
    public void setUpEnvironment(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("disable-gpu");
        chromeOptions.addArguments("window-size=1920,1080");

        driver = new ChromeDriver(chromeOptions);
        landingPage = new LandingPage(driver, "https://i-store.by/");
        itemPage = new ItemPage(driver, "https://i-store.by/ipad/ipad-air-109/ipad-air-4-64-gb-wi-fi-seryy-kosmos-myfm2rka");
        cartPage = new CartPage(driver);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void loginPositiveTest() {
        landingPage.openPage();

        String[][] data = CsvReader.getLoginData();

        landingPage.clickCloseAnnoyingAdButton();

        landingPage.clickToLoginButton();

        landingPage.enterUsername(data[0][0]);

        landingPage.clickConfirmUsernameButton();

        landingPage.enterPassword(data[0][1]);

        landingPage.clickConfirmPasswordButton();

        new WebDriverWait(driver, Duration.ofSeconds(10).getSeconds())
                .until(d -> !d.findElement(By.xpath("//span[@ng-bind=\"user.info.first_name || user.email || translations.accountHeaderNoNamePhrase\"]"))
                        .getText().equals("Привет!"));


        String usernameActual = driver.findElement(By.xpath("//span[@ng-bind=\"user.info.first_name || user.email || translations.accountHeaderNoNamePhrase\"]")).getText();

        Assert.assertEquals(usernameActual, "Ignot");

    }

    @Test
    public void addToCartTest() {
        itemPage.openPage();

        new WebDriverWait(driver, Duration.ofSeconds(10).getSeconds())
                .until(driver -> !itemPage.getVendorCode().isEmpty());

        String vendorCodeExpected = itemPage.getVendorCode();

        itemPage.addToCart();

        itemPage.goToMyCart();

        new WebDriverWait(driver, Duration.ofSeconds(10).getSeconds())
                .until(driver -> cartPage.getVendorCodeOfItem().split(" ").length == 2);

        String vendorCodeActual = cartPage.getVendorCodeOfItem().split(" ")[1];

        Assert.assertEquals(vendorCodeActual, vendorCodeExpected);
    }


    @AfterClass
    public void quitWebDriver(){
        driver.quit();
    }
}

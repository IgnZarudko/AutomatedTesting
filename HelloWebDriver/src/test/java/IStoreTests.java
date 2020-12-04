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
import page.LoginPage;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class IStoreTests {

    WebDriver driver;
    LoginPage loginPage;
    ItemPage itemPage;
    CartPage cartPage;

    @BeforeClass
    public void setUpEnvironment(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("disable-gpu");
        chromeOptions.addArguments("window-size=1920,1080");

        driver = new ChromeDriver(chromeOptions);
        loginPage = new LoginPage(driver);
        itemPage = new ItemPage(driver);
        cartPage = new CartPage(driver);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void loginPositiveTest() {
        driver.get("https://i-store.by/");

        String[][] data = CsvReader.getLoginData();

        loginPage.clickCloseAnnoyingAdButton();

        loginPage.clickToLoginButton();

        loginPage.enterUsername(data[0][0]);

        loginPage.clickConfirmUsernameButton();

        loginPage.enterPassword(data[0][1]);

        loginPage.clickConfirmPasswordButton();

        new WebDriverWait(driver, Duration.ofSeconds(10).toSeconds())
                .until(d -> !d.findElement(By.xpath("//span[@ng-bind=\"user.info.first_name || user.email || translations.accountHeaderNoNamePhrase\"]"))
                        .getText().equals("Привет!"));


        String usernameActual = driver.findElement(By.xpath("//span[@ng-bind=\"user.info.first_name || user.email || translations.accountHeaderNoNamePhrase\"]")).getText();

        Assert.assertEquals(usernameActual, "Ignot");

    }

    @Test
    public void addToCartTest() {
        driver.get("https://i-store.by/ipad/ipad-air-109/ipad-air-4-64-gb-wi-fi-seryy-kosmos-myfm2rka");

        new WebDriverWait(driver, Duration.ofSeconds(10).toSeconds())
                .until(driver -> !itemPage.getVendorCode().isEmpty());

        String vendorCodeExpected = itemPage.getVendorCode();

        itemPage.addToCart();

        itemPage.goToMyCart();

        new WebDriverWait(driver, Duration.ofSeconds(10).toSeconds())
                .until(driver -> cartPage.getVendorCodeOfItem().split(" ").length == 2);

        String vendorCodeActual = cartPage.getVendorCodeOfItem().split(" ")[1];

        Assert.assertEquals(vendorCodeActual, vendorCodeExpected);
    }


    @AfterClass
    public void quitWebDriver(){
        driver.quit();
    }
}

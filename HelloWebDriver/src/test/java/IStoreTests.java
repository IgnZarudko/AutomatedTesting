import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
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
    public void prepareAll(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");

        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        itemPage = new ItemPage(driver);
        cartPage = new CartPage(driver);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    @Test
    public void loginPositiveTest(){
        driver.get("https://i-store.by/");

        loginPage.clickCloseAnnoyingAdButton();

        loginPage.clickToLoginButton();

        loginPage.enterUsername("+375292670264");

        loginPage.clickConfirmUsernameButton();

        loginPage.enterPassword("1TROLLFACE1_v");

        loginPage.clickConfirmPasswordButton();

        new WebDriverWait(driver, Duration.ofSeconds(10).toSeconds())
                .until(d -> !d.findElement(By.xpath("/html/body/div[1]/div[1]/header/div/div/div/div/div/div[3]/div/button/span[1]")).getText().equals("Привет!"));


        String usernameActual = driver.findElement(By.xpath("/html/body/div[1]/div[1]/header/div/div/div/div/div/div[3]/div/button/span[1]")).getText();

        Assert.assertEquals(usernameActual, "Ignot");

    }

    @Test
    public void addToCartTest() {
        driver.get("https://i-store.by/ipad/ipad-air-109/ipad-air-4-64-gb-wi-fi-seryy-kosmos-myfm2rka");

        new WebDriverWait(driver, Duration.ofSeconds(10).toSeconds())
                .until(driver -> !itemPage.getVendorCode().isEmpty());

        String vendorCodeExpected = itemPage.getVendorCode();

        System.out.println(vendorCodeExpected);

        itemPage.addToCart();

        itemPage.goToMyCart();

        new WebDriverWait(driver, Duration.ofSeconds(10).toSeconds())
                .until(driver -> cartPage.getVendorCodeOfItem().split(" ").length == 2);

        String vendorCodeActual = cartPage.getVendorCodeOfItem().split(" ")[1];

        System.out.println(vendorCodeActual);

        Assert.assertEquals(vendorCodeActual, vendorCodeExpected);
    }


    @AfterClass
    public void quitWebDriver(){
        driver.quit();
    }
}

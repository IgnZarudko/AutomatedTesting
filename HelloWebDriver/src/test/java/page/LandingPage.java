package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {
    private WebDriver driver;

    private String pageURL;

    @FindBy(xpath = "//button[@class=\"sp-prompt-btn sp-accept-btn sp_notify_prompt\"]")
    private WebElement closeAnnoyingAdButton;

    @FindBy(xpath = "//*[@id=\"login\"]")
    private WebElement usernameField;

    @FindBy(xpath = "//*[@name=\"password\"]")
    private WebElement passwordField;

    @FindBy(xpath = "//div[@class=\"block-item\"]/button[@ng-if=\"!isLoggedIn\"]")
    private WebElement goToLoginButton;

    @FindBy(xpath = "//button[@ng-click=\"account.onSetModalIndex(1, signInForm)\"]")
    private WebElement confirmUsernameButton;

    @FindBy(xpath = "//button[@ng-bind=\"translations.accountLoginBtnText\"]")
    private WebElement confirmPasswordButton;

    public LandingPage(WebDriver driver, String pageURL){
        PageFactory.initElements(driver, this);
        this.pageURL = pageURL;
        this.driver = driver;
    }

    public void openPage(){
        driver.get(pageURL);
    }

    public void closeAnnoyingAd(){
        closeAnnoyingAdButton.click();
    }

    public void goToLogin(){
        goToLoginButton.click();
    }

    public void enterUsername(String username){
        usernameField.sendKeys(username);
    }

    public void enterPassword(String password){
        passwordField.sendKeys(password);
    }

    public void confirmUsername(){
        confirmUsernameButton.click();
    }

    public void confirmPassword(){
        confirmPasswordButton.click();
    }
}

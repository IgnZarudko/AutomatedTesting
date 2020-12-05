package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LandingPage extends Page{

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

    private final String profileButtonXpath = "//span[@ng-bind=\"user.info.first_name || user.email || translations.accountHeaderNoNamePhrase\"]";

    @FindBy(xpath = profileButtonXpath)
    private WebElement profileButton;

    public LandingPage(WebDriver driver, String pageURL){
        super(driver, pageURL);
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

    public void confirmUsername() {
        confirmUsernameButton.click();
    }

    public void confirmPassword(){
        confirmPasswordButton.click();

        WebDriverWait wait = new WebDriverWait(driver, 10);

        wait.until(
                    ExpectedConditions.not(
                            ExpectedConditions.textToBe(By.xpath(profileButtonXpath), "Привет!")
                    )
        );
    }


    public String getProfileButtonName(){
        return profileButton.getText();
    }
}

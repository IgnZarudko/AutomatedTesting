package by.ignot.automation.qa.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends CommonPage{
    @FindBy(xpath = "//button[@class=\"sp-prompt-btn sp-disallow-btn\"]")
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

    public LoginPage(WebDriver driver){
        super(driver);
    }

    public LoginPage(WebDriver driver, String pageURL){
        super(driver, pageURL);
    }

    @Override
    @SuppressWarnings("unchecked")
    public LoginPage openPage(){
        driver.get(pageURL);
        return this;
    }

    public LoginPage closeAnnoyingAd(){
        closeAnnoyingAdButton.click();
        return this;
    }

    public LoginPage goToLogin(){
        goToLoginButton.click();
        return this;
    }

    public LoginPage enterUsername(String username) {
        usernameField.sendKeys("\b\b\b\b" + username); //to delete automatically inserted 4 symbols
        return this;
    }

    public LoginPage confirmUsername() {
        confirmUsernameButton.click();
        return this;
    }

    public String getUsernameFieldClass(){
        return usernameField.getAttribute("class");
    }

    public LoginPage enterPassword(String password){
        passwordField.sendKeys(password);
        return this;
    }

    public LoginPage confirmPassword() {
        confirmPasswordButton.click();

        new WebDriverWait(driver, WAIT_TIMEOUT).until(
                ExpectedConditions.not(
                        ExpectedConditions.textToBe(By.xpath(profileButtonXpath), "Привет!")
                )
        );
        return this;
    }

    public String getPasswordFieldClass(){
        return passwordField.getAttribute("class");
    }

    public String getProfileButtonName(){
        return profileButton.getText();
    }
}

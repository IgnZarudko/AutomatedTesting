package by.ignot.automation.qa.page;

import by.ignot.automation.qa.util.LogProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
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

    @FindBy(xpath = "//a[@ng-href=\"account/favorites\"]//div[@class=\"tab-heading-name ng-binding\"]")
    private WebElement goToFavouritesButton;

    public LoginPage(WebDriver driver){
        super(driver, LogProvider.getLog());
        PageFactory.initElements(driver, this);
    }

    public LoginPage(WebDriver driver, String pageURL){
        super(driver, LogProvider.getLog(), pageURL);
        PageFactory.initElements(driver, this);
    }

    @Override
    @SuppressWarnings("unchecked")
    public LoginPage openPage(){
        log.info("Opening login page");
        driver.get(pageURL);
        return this;
    }

    public LoginPage closeAnnoyingAd(){
        closeAnnoyingAdButton.click();
        return this;
    }

    public LoginPage goToLogin(){
        log.info("Opening login popup");
        goToLoginButton.click();
        return this;
    }

    public boolean isLoginPageOpened(){
        log.info("Checking that popup is opened");
        return usernameField.isDisplayed();
    }

    public LoginPage enterUsername(String username) {
        log.info("Entering user login");
        usernameField.sendKeys("\b\b\b\b" + username); //to delete automatically inserted 4 symbols
        return this;
    }

    public LoginPage confirmUsername() {
        log.info("Confirming login");
        confirmUsernameButton.click();
        return this;
    }

    public String getUsernameFieldClass(){
        return usernameField.getAttribute("class");
    }

    public LoginPage enterPassword(String password){
        log.info("Entering user password");
        passwordField.sendKeys(password);
        return this;
    }

    public LoginPage confirmPassword() {
        log.info("Confirming password");
        confirmPasswordButton.click();
        return this;
    }

    public FavouritesPage goToFavourites() {
        new WebDriverWait(driver, WAIT_TIMEOUT)
                .until(ExpectedConditions.elementToBeClickable(goToFavouritesButton));

        goToFavouritesButton.click();
        return new FavouritesPage(driver);
    }

    public String getPasswordFieldClass() {
        return passwordField.getAttribute("class");
    }

    public String getProfileButtonName() {
        log.info("Getting profile name after login");
        new WebDriverWait(driver, WAIT_TIMEOUT)
                .until(_driver -> !this.profileButton.getText().equals("Привет!"));
        return profileButton.getText();
    }
}

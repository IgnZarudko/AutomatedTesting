import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    public WebDriver driver;
    public LoginPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;

    }


    @FindBy(xpath = "/html/body/div[1]/div/div[2]/div/div[2]/button")
    private WebElement closeAnnoyingAdButton;

    @FindBy(xpath = "//*[@id=\"login\"]")
    private WebElement usernameField;

    @FindBy(xpath = "//*[@id=\"input_7\"]")
    private WebElement passwordField;

    @FindBy(xpath = "/html/body/div[1]/div[1]/header/div/div/div/div/div/div[3]/button")
    private WebElement toLoginButton;

    @FindBy(xpath = "/html/body/div[1]/div/div/div[2]/div/form/button")
    private WebElement confirmUsernameButton;

    @FindBy(xpath = "/html/body/div[1]/div/div/div[2]/div/form/button")
    private WebElement confirmPasswordButton;

    public void clickCloseAnnoyingAdButton(){
        closeAnnoyingAdButton.click();
    }

    public void clickToLoginButton(){
        toLoginButton.click();
    }

    public void enterUsername(String username){
        usernameField.sendKeys(username);
    }

    public void enterPassword(String password){
        passwordField.sendKeys(password);
    }


    public void clickConfirmUsernameButton(){
        confirmUsernameButton.click();
    }

    public void clickConfirmPasswordButton(){
        confirmPasswordButton.click();
    }


}

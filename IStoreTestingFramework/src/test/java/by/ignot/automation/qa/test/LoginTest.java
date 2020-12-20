package by.ignot.automation.qa.test;

import by.ignot.automation.qa.page.LoginPage;
import by.ignot.automation.qa.util.LoginDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends CommonSetup{

    @Test(dataProvider = "getCorrectLoginCredentials", dataProviderClass = LoginDataProvider.class)
    public void loginPositiveTest(String userLogin, String userPassword, String expectedUsername) {

        String usernameActual = new LoginPage(driver, "https://i-store.by/")
                .openPage()
                .closeAnnoyingAd()
                .goToLogin()
                .enterUsername(userLogin)
                .confirmUsername()
                .enterPassword(userPassword)
                .confirmPassword().getProfileButtonName();

        Assert.assertEquals(usernameActual, expectedUsername);
    }

    @Test(dataProvider = "getIncorrectUsernameCredentials", dataProviderClass = LoginDataProvider.class)
    public void invalidUsernameTest(String userLogin){
        LoginPage loginPage = new LoginPage(driver, "https://i-store.by/");

        String usernameFieldClassBeforeInput = loginPage
                .openPage()
                .closeAnnoyingAd()
                .goToLogin()
                .getUsernameFieldClass();

        String usernameFieldClassAfterInput = loginPage
                .enterUsername(userLogin)
                .confirmUsername()
                .getUsernameFieldClass();

         Assert.assertNotEquals(usernameFieldClassBeforeInput, usernameFieldClassAfterInput);
    }

    @Test(dataProvider = "getIncorrectPasswordCredentials", dataProviderClass = LoginDataProvider.class)
    public void invalidPasswordTest(String userLogin, String userPassword) {
        LoginPage loginPage = new LoginPage(driver, "https://i-store.by/");

        String passwordFieldClassBeforeInput = loginPage
                .openPage()
                .closeAnnoyingAd()
                .goToLogin()
                .enterUsername(userLogin)
                .confirmUsername()
                .getPasswordFieldClass();

        String passwordFieldClassAfterInput = loginPage
                .enterPassword(userPassword)
                .confirmPassword()
                .getPasswordFieldClass();

        Assert.assertNotEquals(passwordFieldClassBeforeInput, passwordFieldClassAfterInput);
    }
}

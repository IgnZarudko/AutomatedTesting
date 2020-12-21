package by.ignot.automation.qa.action;

import by.ignot.automation.qa.page.LoginPage;

public class UserAction {

    private static final String DEFAULT_LOGIN = "+375292670264";
    private static final String DEFAULT_PASSWORD = "1TROLLFACE1_v";

    public static LoginPage loginWithDefaultCredentials(LoginPage loginPage){
        return loginWithGivenCredentials(loginPage, DEFAULT_LOGIN, DEFAULT_PASSWORD);
    }

    public static LoginPage loginWithGivenCredentials(LoginPage loginPage, String userLogin, String userPassword){
        return loginPage
                .openPage()
                .closeAnnoyingAd()
                .goToLogin()
                .enterUsername(userLogin)
                .confirmUsername()
                .enterPassword(userPassword)
                .confirmPassword();
    }
}
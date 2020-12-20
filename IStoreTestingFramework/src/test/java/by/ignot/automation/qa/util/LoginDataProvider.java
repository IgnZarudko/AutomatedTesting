package by.ignot.automation.qa.util;

import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class LoginDataProvider {

    private static final String CORRECT_CREDENTIALS_PATH = "src/test/resources/login/correctCredentialsData.csv";
    private static final String INCORRECT_EMAIL_PATH = "src/test/resources/login/incorrectUsernameData.csv";
    private static final String INCORRECT_PASSWORD_PATH = "src/test/resources/login/incorrectPasswordData.csv";

    @DataProvider()
    public static Object[][] getCorrectLoginCredentials(){
        return getLoginCredentials(CORRECT_CREDENTIALS_PATH);
    }

    @DataProvider
    public static Object[][] getIncorrectUsernameCredentials(){
        return getLoginCredentials(INCORRECT_EMAIL_PATH);
    }

    @DataProvider
    public static Object[][] getIncorrectPasswordCredentials(){
        return getLoginCredentials(INCORRECT_PASSWORD_PATH);
    }

    private static String[][] getLoginCredentials(String path) {
        try{
            BufferedReader reader = new BufferedReader(new FileReader(path));
            List<String[]> loginDataList = reader
                    .lines()
                    .map(line -> line.split(","))
                    .collect(Collectors.toList());

            String[][] loginData = new String[loginDataList.size()][];

            for (int i = 0; i < loginDataList.size(); i++){
                loginData[i] = loginDataList.get(i);
            }

            return loginData;
        }
        catch (IOException e){
            e.printStackTrace();
        }

        return null;
    }
}

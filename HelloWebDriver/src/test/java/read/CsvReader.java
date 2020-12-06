package read;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CsvReader {

    public static String[][] getLoginData() {
        String path ="src/main/resources/loginData.csv";

        try{
            BufferedReader reader = new BufferedReader(new FileReader(path));

            String[] loginDataLines = reader.readLine().split("\n");

            String[][] loginData = new String[loginDataLines.length][2];
            for (int i = 0; i < loginDataLines.length; i++){
                loginData[i][0] = loginDataLines[i].split(",")[0];
                loginData[i][1] = loginDataLines[i].split(",")[1];
            }

            reader.close();

            return loginData;
        } catch (IOException e){
            e.printStackTrace();
        }

        return null;
    }
}

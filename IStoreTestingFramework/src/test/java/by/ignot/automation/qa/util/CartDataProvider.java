package by.ignot.automation.qa.util;

import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class CartDataProvider {

    private static final String ITEM_URLS_PATH = "src/test/resources/cart/itemsToAddUrls.csv";
    private static final String SEVERAL_ITEM_URLS_PATH = "src/test/resources/cart/severalItemsToAddUrls.csv";


    @DataProvider()
    public static Object[][] getSeveralItemToAddUrls(){
        return readData(SEVERAL_ITEM_URLS_PATH);
    }

    @DataProvider()
    public static Object[][] getItemToAddUrls(){
        return readData(ITEM_URLS_PATH);
    }

    private static String[][] readData(String path) {
        try{
            BufferedReader reader = new BufferedReader(new FileReader(path));
            List<String[]> itemUrlsList = reader
                    .lines()
                    .map(line -> line.split(","))
                    .collect(Collectors.toList());

            String[][] itemUrls = new String[itemUrlsList.size()][];

            for (int i = 0; i < itemUrlsList.size(); i++){
                itemUrls[i] = itemUrlsList.get(i);
            }

            return itemUrls;
        }
        catch (IOException e){
            e.printStackTrace();
        }

        return null;
    }
}

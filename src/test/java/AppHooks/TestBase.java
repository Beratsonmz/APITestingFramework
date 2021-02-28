package AppHooks;

import Utilities.ExcelReader;
import io.restassured.RestAssured;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {
    public static Properties config = new Properties();
    private FileInputStream file;
    public static ExcelReader excelReader = new ExcelReader("./src/test/resources/ExcelFiles/DataSheet.xlsx");

    @BeforeSuite
    public void setup() {
        try {
            file = new FileInputStream("src/test/resources/properties/config.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            config.load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        RestAssured.baseURI = config.getProperty("baseURI");
        RestAssured.basePath = config.getProperty("basePath");
    }

    @AfterSuite
    public void tearDown() {

    }
}

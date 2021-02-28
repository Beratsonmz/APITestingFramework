package Utilities;

import AppHooks.TestBase;
import com.github.javafaker.Faker;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;
import java.util.Hashtable;

public class DataUtility extends TestBase {

    public static String [] createFakeUser(){
        Faker faker = new Faker();

        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = lastName+"."+firstName+"@mail.com";
        String description = faker.lorem().sentence(5);

        String[] userData = new String[3];

        userData[0]=firstName+" "+lastName;
        userData[1]= email.toLowerCase();
        userData[2]= description;

        return userData;
    }

    @DataProvider(name = "excelDataProvider",parallel = true)
    public Object[][] getData(Method method) {

        String sheetName = "Main";
        String testName = method.getName();

        int totalRows = TestBase.excelReader.getRowCount(sheetName);

        //Find the number of row, where test data starts with.
        int rowCount;
        for (rowCount = 1; rowCount <= totalRows; rowCount++) {
            String caseName = TestBase.excelReader.getCellData(sheetName, 0, rowCount);
            if (caseName.equalsIgnoreCase(testName))
                break;
        }

        // Find total number of rows which contains test data.

        int firstDataRow = rowCount + 2;
        int totalDataRow = 0;

        while (!TestBase.excelReader.getCellData(sheetName, 0, firstDataRow + totalDataRow).equals("")) {
            totalDataRow++;
        }

        //Find the number of columns

        int headerRow = rowCount + 1;
        int columnCount = 0;
        while (!TestBase.excelReader.getCellData(sheetName, columnCount, headerRow).equals("")) {
            columnCount++;
        }

        // Creating data object,

        Object[][] data = new Object[totalDataRow][1];

        int c = 0;
        for (int i = firstDataRow; i < (firstDataRow + totalDataRow); i++) {

            Hashtable<String, String> table = new Hashtable<>();

            for (int r = 0; r < columnCount; r++) {
                String testData = TestBase.excelReader.getCellData(sheetName, r, i);
                String colName = TestBase.excelReader.getCellData(sheetName, r, headerRow);

                table.put(colName, testData);

            }
            data[c][0] = table;
            c++;
        }
        return data;
    }
}

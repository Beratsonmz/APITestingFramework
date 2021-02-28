package TestCases;


import AppHooks.TestBase;
import Requests.CreateCustomer;
import Utilities.DataUtility;
import Utilities.ExtentListener;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class CreateCustomerTest extends TestBase {

    @Test(dataProviderClass = DataUtility.class, dataProvider = "excelDataProvider")
    public void createValidateCustomerWithValidKey(Hashtable<String, String> data) {

        Response response = CreateCustomer.withValidKey(data);
        ExtentListener.testReport.get().info(data.toString());
        response.prettyPrint();
        Assert.assertEquals(response.statusCode(), 200);

    }

    @Test(dataProviderClass = DataUtility.class, dataProvider = "excelDataProvider")
    public void createValidateCustomerWithInvalidKey(Hashtable<String, String> data) {

        Response response = CreateCustomer.withInvalidKey(data);
        ExtentListener.testReport.get().info(data.toString());
        Assert.assertEquals(response.statusCode(), 401);

    }
    @Test
    public void createValidateCustomerWithFaker() {

        Response response = CreateCustomer.withFaker();
        Assert.assertEquals(response.statusCode(), 200);

    }

}

package TestCases;

import AppHooks.TestBase;
import Requests.DeleteCustomer;
import Utilities.DataUtility;
import Utilities.ExtentListener;
import Utilities.TestUtility;
import io.restassured.response.Response;

import org.junit.Assert;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class DeleteCustomerTest extends TestBase {

    @Test(dataProviderClass = DataUtility.class, dataProvider = "excelDataProvider")
    public void deleteCustomer(Hashtable<String, String> data) {
        Response response = DeleteCustomer.withId(data);
        String sResponse = response.asString();

        ExtentListener.testReport.get().info(data.toString());

        Assert.assertTrue(TestUtility.jsonHasKey(sResponse, "id"));

        Assert.assertEquals(TestUtility.getJsonKey(sResponse, "id"), data.get("id"));
        Assert.assertEquals(200, response.statusCode());
        //ExtentListener.testReport.get().info(data.toString());


    }
}

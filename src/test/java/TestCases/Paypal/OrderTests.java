package TestCases.Paypal;

import AppHooks.TestBase;
import Requests.PayPalSandBox.Order;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OrderTests extends TestBase {

    @Test
    public void createOrder() {
        String accessToken = Order.getAccessToken();
        Response response = Order.createOrder(accessToken);
        Assert.assertEquals(response.jsonPath().get("status"), "CREATED");
    }

    @Test(dependsOnMethods = "createOrder")
    public void getOrder() {
        String accessToken = Order.getAccessToken();
        Response response = Order.getOrder(accessToken);
        Assert.assertEquals(response.statusCode(),200);
    }
}

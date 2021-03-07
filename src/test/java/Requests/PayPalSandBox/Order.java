package Requests.PayPalSandBox;

import AppHooks.TestBase;
import Pojo.Orders;
import Pojo.PurchaseUnits;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class Order extends TestBase {

    static String accessToken;
    static String orderId;
    static String client_id = config.getProperty("payPalClientId");
    static String secret = config.getProperty("payPalSecret");


    public static String getAccessToken() {

        Response response =  given().param("grant_type", "client_credentials")
                .auth().preemptive().basic(client_id, secret)
                .post("/v1/oauth2/token");

        accessToken =response.jsonPath().get("access_token").toString();

        return accessToken;

    }

    public static Response createOrder(String accessToken) {

        ArrayList<PurchaseUnits> purchaseUnit = new ArrayList<PurchaseUnits>();
        purchaseUnit.add(new PurchaseUnits("USD", "500.00"));
        Orders order = new Orders("CAPTURE", purchaseUnit);

        Response response = given().contentType(ContentType.JSON)
                .auth().oauth2(accessToken).body(order)
                .post("/v2/checkout/orders");
        orderId= response.jsonPath().get("id").toString();
        System.out.println(orderId);
        return response;
    }

    public static Response getOrder(String accessToken) {
        System.out.println(orderId);
        Response response = given().contentType(ContentType.JSON)
                .auth().oauth2(accessToken).get("/v2/checkout/orders" + "/" + orderId);
        return response;
    }
}

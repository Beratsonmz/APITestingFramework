package Requests;

import AppHooks.TestBase;
import io.restassured.response.Response;

import java.util.Hashtable;

import static io.restassured.RestAssured.given;

public class DeleteCustomer extends TestBase {

    public static Response withId(Hashtable<String, String> data) {
        Response response = given().auth().basic(config.getProperty("validSecretKey"), "")
                .delete(config.getProperty("customerEndPoint") + "/" + data.get("id"));

        response.prettyPrint();

        return response;
    }
}

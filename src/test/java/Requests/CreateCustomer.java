package Requests;


import AppHooks.TestBase;
import Utilities.DataUtility;
import io.restassured.response.Response;

import java.util.Hashtable;

import static io.restassured.RestAssured.given;


public class CreateCustomer extends TestBase {

    public static Response withValidKey(Hashtable<String, String> data) {

        Response response = given().auth().basic(config.getProperty("validSecretKey"), "")
                .formParam("name", data.get("name"))
                .formParam("email", data.get("email"))
                .formParam("description", data.get("description"))
                .post(config.getProperty("customerEndPoint"));

        response.prettyPrint();

        return response;
    }
    public static Response withFaker() {
        String[] data = DataUtility.createFakeUser();

        Response response = given().auth().basic(config.getProperty("validSecretKey"), "")
                .formParam("name", data[0])
                .formParam("email", data[1])
                .formParam("description", data[2])
                .post(config.getProperty("customerEndPoint"));

        response.prettyPrint();

        return response;
    }

    public static Response withInvalidKey(Hashtable<String, String> data) {

        Response response = given().auth().basic(config.getProperty("invalidSecretKey"), "")
                .formParam("name", data.get("name"))
                .formParam("email", data.get("email"))
                .formParam("description", data.get("description"))
                .post(config.getProperty("customerEndPoint"));

        response.prettyPrint();

        return response;

    }
}

package Utilities;

import org.json.JSONObject;

public class TestUtility {
    public static boolean jsonHasKey(String response, String key) {

        JSONObject jsonObject = new JSONObject(response);
        ExtentListener.testReport.get().info("Validate presence of : "+key);
        return jsonObject.has(key);
    }

    public static String getJsonKey(String response, String key) {

        JSONObject jsonObject = new JSONObject(response);
        ExtentListener.testReport.get().info("Key Value : "+key);
        return jsonObject.get(key).toString();
    }
}

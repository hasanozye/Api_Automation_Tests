package apiTests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static org.testng.Assert.*;

import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.baseURI;

public class _07_SprinGularTestWithJsonPath {

    String accessToken = ConfigurationReader.get("accessTokenSpringGular");

    @BeforeClass
    public void beforeClass() {
        baseURI = ConfigurationReader.get("springularURL");
    }


    @Test
    public void test() {
        Response response = given().header("Authorization", accessToken)
                .when().get("/version");
        response.prettyPrint();

        String version = response.path("version");
        int major = response.path("major");
        int minor = response.path("minor");
        int patch = response.path("patch");

        System.out.println("version = " + version);
        System.out.println("major = " + major);
        System.out.println("minor = " + minor);
        System.out.println("patch = " + patch);

        assertEquals(version, "1.0.0");
        assertEquals(major, 1);
        assertEquals(minor, 0);
        assertEquals(patch, 0);

        JsonPath jsonPath = response.jsonPath();

        String versionJson = jsonPath.getString("version");
        int majorJson = jsonPath.getInt("major");
        int minorJson = jsonPath.getInt("minor");
        int patchJson = jsonPath.getInt("patch");

        System.out.println("versionJson = " + versionJson);
        System.out.println("majorJson = " + majorJson);
        System.out.println("minorJson = " + minorJson);
        System.out.println("patchJson = " + patchJson);


    }

    @Test
    public void test2() {
        Response response = given().header("Authorization", accessToken)
                .when().get("/user");

        JsonPath jsonPath = response.jsonPath();

        String operationStatus = jsonPath.getString("operationStatus");
        String operationMessage = jsonPath.getString("operationMessage");
        String data = jsonPath.getString("data");

        System.out.println("operationStatus = " + operationStatus);
        System.out.println("operationMessage = " + operationMessage);
        System.out.println("data = " + data);


    }

    @Test
    public void jsonObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", 1);
        jsonObject.put("title", "GuiderSoft");
        jsonObject.put("body", "Merhaba");
        System.out.println(jsonObject);
    }

    @Test
    public void jsusObject() {
        Response response = given().header("Authorization", accessToken)
                .when().get("/booking/10");
        //response.prettyPrint();

        JsonPath jsonPath = response.jsonPath();

        String firstnameJson = jsonPath.getString("firstname");
        String lastnameJson = jsonPath.getString("lastname");
        String totalpriceJson = jsonPath.getString("totalprice");
        String depositpaidJson = jsonPath.getString("depositpaid");

        JSONObject jsonObjectInner = new JSONObject();
        jsonObjectInner.put("checkin", "2022-02-25");
        jsonObjectInner.put("checkout", "2022-06-15");

        JSONObject body = new JSONObject();
        body.put("firstname", "Susan");
        body.put("lastname", "Wilson");
        body.put("totalprice", 121);
        body.put("depositpaid", true);
        body.put("bookingdates", jsonObjectInner);

        System.out.println(body);


        assertEquals(firstnameJson, "Susan");
        assertEquals(lastnameJson, "Wilson");
        assertEquals(totalpriceJson, "121");
        assertEquals(depositpaidJson, "true");
    }

    /*
    {
    "operationStatus": "SUCCESS",
    "operationMessage": null,
    "data": {
        "userId": "guidersoft",
        "password": "quality_hunter",
        "company": "Abshire Inc",
        "firstName": "Mrinmoy",
        "lastName": "Majumdar",
        "email": "arivera2@joomla.org",
        "role": "USER",
        "fullName": "MrinmoyMajumdar"
    }
}
     */

    @Test
    public void jsonObject2() {
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObjectInner = new JSONObject();

        jsonObjectInner.put("userId", "guidersoft");
        jsonObjectInner.put("password", "quality_hunter");
        jsonObjectInner.put("company", "Abshire Inc");
        jsonObjectInner.put("firstName", "Mrinmoy");
        jsonObjectInner.put("lastName", "Majumdar");
        jsonObjectInner.put("email", "arivera2@joomla.org");
        jsonObjectInner.put("role", "USER");
        jsonObjectInner.put("fullName", "MrinmoyMajumdar");


        jsonObject.put("operationStatus", "SUCCESS");
        jsonObject.put("operationMessage", "null");
        jsonObject.put("data",jsonObjectInner);

        System.out.println("jsonObject = " + jsonObject);
    }


}

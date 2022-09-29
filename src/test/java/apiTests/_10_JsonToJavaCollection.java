package apiTests;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.baseURI;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.*;

public class _10_JsonToJavaCollection {
    String accessToken = ConfigurationReader.get("accessTokenSpringGular");

    @BeforeClass
    public void beforeClass() {
        baseURI = ConfigurationReader.get("springularURL");
    }

    //De-Serialization --> Json to Java Collection for example List, Set, Map
    /*
    {
    "version": "1.0.0",
    "major": 1,
    "minor": 0,
    "patch": 0
}
     */

    @Test
    public void versionToMap() {
        Response response = given().header("Authorization", accessToken)
                .when().get("/version");
        assertEquals(response.statusCode(), 200);

        //we will convert json to java map
        Map<String, Object> jsonDataMap = response.body().as(Map.class);
        System.out.println(jsonDataMap);

        String version = (String) jsonDataMap.get("version");
        double major = (double) jsonDataMap.get("major");
        double minor = (double) jsonDataMap.get("minor");
        double patch = (double) jsonDataMap.get("patch");

        System.out.println("version = " + version);
        System.out.println("major = " + major);
        System.out.println("minor = " + minor);
        System.out.println("patch = " + patch);

        assertEquals(version, "1.0.0");
        assertEquals(major, 1.0);
        assertEquals(minor, 0.0);
        assertEquals(patch, 0.0);
    }

    @Test
    public void allEmployeeListOfMap() {
        Response response = given().header("Authorization", accessToken)
                .when().get("/api/employees");
        assertEquals(response.statusCode(), 200);

//        We need to de-serialize JSON RESPONSE TO lİST oF mAPS
        JsonPath jsonPath = response.jsonPath();
//        jsonPath.prettyPrint();

//        String items = jsonPath.getString("items");
//        System.out.println("items = " + items);

        List<Map<String, Object>> allEmployeeList = response.body().as(List.class);
        System.out.println("allEmployeeList = " + allEmployeeList);

    }

}

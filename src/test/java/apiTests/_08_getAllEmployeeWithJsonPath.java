package apiTests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.baseURI;

public class _08_getAllEmployeeWithJsonPath {
    String accessToken = ConfigurationReader.get("accessTokenSpringGular");

    @BeforeClass
    public void beforeClass() {
        baseURI = ConfigurationReader.get("springularURL");
    }

    @Test
    public void getAllEmployeeWithJsonPath() {
        Response response = given().header("Authorization", accessToken)
                .and().queryParam("size", 50)
                .when().get("/api/employees");
        JsonPath jsonPath = response.jsonPath();

        String items = jsonPath.getString("items");
        String ids = jsonPath.getString("items.id");
        int firstEmployeeId = jsonPath.getInt("items.id[0]");
        String lastName = jsonPath.getString("items.lastName");
        long firstEmployeePhone = jsonPath.getLong("items.phone[0]");
        String phone = jsonPath.getString("items.phone");

        System.out.println("items = " + items);
        System.out.println("ids = " + ids);
        System.out.println("firstEmployeeId = " + firstEmployeeId);
        System.out.println("lastName = " + lastName);
        System.out.println("firstEmployeePhone = " + firstEmployeePhone);
        System.out.println("phone = " + phone);
    }

}

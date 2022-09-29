package apiTests;

import io.restassured.response.Response;

import static org.testng.Assert.*;

import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class _04_TestWithParametre {
    String sprinGularURL = "http://142.93.110.12:9119";
    String petStoreURL = "https://petstore.swagger.io/v2";
    String accessToken = ConfigurationReader.get("accessTokenSpringGular");

    @Test
    public void getPetStore_Positive() {
        Response response = given().header("Authorization", accessToken)
                .pathParam("id", 12)
                .when().get(petStoreURL + "/pet/{id}");
        response.prettyPrint();

        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json");
        assertTrue(response.body().asString().contains("doggie"));


    }

    //orderID 4003 query parameter
    @Test
    public void positiveTestWithQueryParam1() {
        Response response = given().header("Authorization", accessToken)
                .and().queryParam("orderid", "4003")
                .when().get(sprinGularURL + "/api/orders");

        response.prettyPrint();

        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json;charset=UTF-8");
    }

    @Test
    public void positiveTestWithQueryParam2() {
        Response response = given().header("Authorization", accessToken)
                .and().queryParam("orderid", "4003")
                .and().queryParam("employeeid", 218)
                .and().queryParam("customerid", 54)
                .and().queryParam("shipName", "Jerry Frazier")
                .when().get(sprinGularURL + "/api/orders");

        response.prettyPrint();

        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json;charset=UTF-8");
        assertTrue(response.body().asString().contains("Baby"));
        assertTrue(response.body().asString().contains("Jenkins"));


    }

    @Test
    public void positiveTestWithQueryParamWithMap() {
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("orderId", "4003");
        queryMap.put("employeeid", 218);
        queryMap.put("customerid", 54);
        queryMap.put("orderDate", 1471564800000L);
        queryMap.put("shippedDate", 1481155200000L);
        queryMap.put("paidDate", 1474934400000L);
        queryMap.put("shipName", "Jerry Frazier");
        queryMap.put("shipAddress1", "23 Sundown Junction");
        queryMap.put("shipAddress2", "Obodivka");
        queryMap.put("shipCountry", "Ukraine");

        Response response = given().header("Authorization", accessToken)
                .and().queryParam("orderid", "4003")
                .when().get(sprinGularURL + "/api/orders");

        response.prettyPrint();

        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json;charset=UTF-8");
        assertTrue(response.body().asString().contains("Baby"));
        assertTrue(response.body().asString().contains("Jenkins"));
    }


}

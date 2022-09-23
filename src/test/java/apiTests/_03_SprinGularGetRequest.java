package apiTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class _03_SprinGularGetRequest {

    String accessToken = "Bearer " +
            "eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2NjM5NTU2MTgsInN1YiI6Ik1yaW5tb3lNYWp1bWRhciIsInVzZXJJZCI6Imd1aWRlcnNvZnQiLCJyb2xlIjoiVVNFUiJ9.PJ7JZf5vpsRM13ZMQBdKPc3EEQaXWFTAITakloRR2Ms";
    String springularUrl = "http://142.93.110.12:9119";

    @Test
    public void test01() {
        Response response = given().header("Authorization", accessToken)
                .when().get(springularUrl + "/api/orders");

        response.prettyPrint();

        Assert.assertEquals(response.statusCode(),200);

    }

    @Test
    public void test02(){
        Response response = given().header("Authorization", accessToken)
                .when().get(springularUrl + "/api/orders?orderid=4003");
        response.prettyPrint();

    }

    @Test
    public void test03(){
        Response response = given().header("Authorization", accessToken)
                .when().get(springularUrl + "/api/orders?orderid=4500");
        response.prettyPrint();

        Assert.assertEquals(response.statusCode(),200);


    }


}

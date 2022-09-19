package apiTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;


public class _01_SimpleGetRequest {

    String herokuURL = "https://restful-booker.herokuapp.com/booking";

    @Test
    public void test01() {
        Response response = RestAssured.get(herokuURL);

        //print the status code BRUH
        System.out.println("StatusCode() = " + response.statusCode());

    }

    @Test
    public void test02() {
        Response response = RestAssured.get(herokuURL);

        //print the json body
        response.prettyPrint();


    }

    @Test
    public void test03() {
        Response response = RestAssured.get(herokuURL);

        Assert.assertEquals(response.statusCode(), 200);

        System.out.println("ContentType = " + response.contentType());

        Assert.assertEquals(response.contentType(), "application/json; charset=utf-8");


    }

    @Test
    public void test04() {
        given().accept(ContentType.JSON)
                .when().get(herokuURL)
                .then().assertThat().statusCode(200)
                .and().contentType("application/json; charset=utf-8");

    }

    @Test
    public void test05() {
        Response response = given().accept("application/json")
                .when().get(herokuURL + "/150");
        response.prettyPrint();

        Assert.assertEquals(response.statusCode(), 200);

        System.out.println("response.statusCode() = " + response.statusCode());

        Assert.assertTrue(response.body().asString().contains("Edgar"),"its not equal!");

    }

}

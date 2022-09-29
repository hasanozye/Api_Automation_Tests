package apiTests;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.baseURI;
import static org.hamcrest.Matchers.*;

public class _09_HamcrestMatchersTest {
    String accessToken = ConfigurationReader.get("accessTokenSpringGular");

    @BeforeClass
    public void beforeClass() {
        baseURI = ConfigurationReader.get("springularURL");
    }

    @Test
    public void test1() {
        given().accept(ContentType.JSON)
                .when().get(ConfigurationReader.get("herokuURL") + "/booking")
                .then().assertThat().statusCode(200)
                .and().contentType("application/json; charset=utf-8")
                .and().header("Server", "Cowboy")
                .statusLine("HTTP/1.1 200 OK");
    }

    @Test
    public void test2() {
        given()
                .header("Authorization", accessToken)
                .and().queryParam("size", "1000")
                .when().get("/api/customers")
                .then().statusCode(200)
                .and().assertThat().contentType(equalTo("application/json;charset=UTF-8"))
                .and().assertThat().body("items.id[0]",equalTo(1),
                        "items.firstName[0]",equalTo("Clarence"),
                        "items.lastName[0]",equalTo("Gray"),
                        "items.email[0]",equalTo("cgray0@rambler.ru"),
                        "items.company[0]",equalTo("Jetpulse"),
                        "items.phone[0]",equalTo("1-(260)615-5114"),
                        "items.city[0]",equalTo("Fort Wayne"),
                        "items.state[0]",equalTo("Indiana"),
                        "items.postalCode[0]",equalTo("46805"),
                        "items.country[0]",equalTo("United States"));

    }

    @Test
    public void test3(){
        given()
                .header("Authorization", accessToken)
                .queryParam("size","1000")
                .when().get("/user")
                .then().statusCode(200)
                .and().contentType("application/json;charset=utf-8")
                .and().header("X-Content-Type-Options","nosniff")
                .and().header("Pragma","no-cache")
                .and().header("X-Frame-Options","DENY")
                .and().assertThat().body("data.userId", equalTo("guidersoft"),
                        "data.password", equalTo("quality_hunter"),
                        "data.company", equalTo("Abshire Inc"),
                        "data.firstName", equalTo("Mrinmoy"),
                        "data.lastName", equalTo("Majumdar"),
                        "data.email", equalTo("arivera2@joomla.org"),
                        "data.role", equalTo("USER"),
                        "data.fullName", equalTo("MrinmoyMajumdar"))
                .log().all()



        ;
    }


}

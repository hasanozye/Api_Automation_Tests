package apiTests;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class _02_Token {

    String accessToken = "Bearer " +
            "eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2NjM3OTE5MTAsInN1YiI6Ik1yaW5tb3lNYWp1bWRhciIsInVzZXJJZCI6Imd1aWRlcnNvZnQiLCJyb2xlIjoiVVNFUiJ9.O50vgt3hUP4yV_3QpRmybgmhte5rONdDmNG_pmOLWT0";
    String springularUrl = "http://142.93.110.12:9119";

    @Test
    public void test01() {
        Response response = given().header("Authorization", accessToken)
                            .when().get(springularUrl + "/api/orders");

        response.prettyPrint();
    }


}

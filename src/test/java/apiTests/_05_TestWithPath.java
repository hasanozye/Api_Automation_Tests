package apiTests;

import io.restassured.response.Response;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.baseURI;

public class _05_TestWithPath {

    String petStoreURL = "https://petstore.swagger.io/v2";

    @BeforeClass
    public void beforeClass() {
        baseURI = petStoreURL;
    }

    @Test
    public void getOnePet_path() {

        Response response = given().pathParam("id", 12)
                .when().get("/pet/{id}");

        response.prettyPrint();
        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json");
        //assertTrue(response.body().asString().contains("doggie"));

        //printing each key values in the jsonbody/payload
        System.out.println(response.path("id").toString());
        System.out.println(response.path("name").toString());
        System.out.println(response.path("status").toString());
        System.out.println(response.path("category").toString());
        System.out.println(response.path("tags").toString());

        int id = response.path("id");
        String name = response.path("name");
        String status = response.path("status");





        System.out.println("id = " + id);
        System.out.println("name = " + name);
        System.out.println("status = " + status);

        assertEquals(id,12);
        assertEquals(name,"doggie");
        assertEquals(status,"available");


    }


}

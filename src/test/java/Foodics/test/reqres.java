package Foodics.test;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static utils.JsonUtils.getJsonDataAsMap;

public class reqres {
    static String userID;

    @BeforeClass
    public void setup() {
        RestAssured.filters(new AllureRestAssured());
    }

    @Test(description = "Create a User")
    public void createUser() throws IOException {
        Response res = RestAssured.given().contentType(ContentType.JSON)
                .log().all()
                .body(getJsonDataAsMap("createUser.json"))
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .log().all()
                .extract().response();

        userID = res.jsonPath().get("id").toString();
        System.out.println("User ID: " + userID);
    }

    @Test(description = "Update a User", dependsOnMethods = "createUser")
    public void updateUser() throws IOException {
        Response res = RestAssured.given().contentType(ContentType.JSON)
                .log().all()
                .body(getJsonDataAsMap("updateUser.json"))
                .put("https://reqres.in/api/users/" + userID)
                .then()
                .statusCode(200)
                .log().all()
                .extract().response();
        Assert.assertEquals(res.jsonPath().get("name").toString(), ("Ahmed"));
        Assert.assertEquals(res.jsonPath().get("job").toString(), ("QA"));
    }

    @Test(description = "Retrieve a User", dependsOnMethods = "updateUser")
    public void retrieveUser() {
        RestAssured.given()
                .log().all()
                .get("https://reqres.in/api/users/" + userID)
                .then()
                .statusCode(404)
                .statusLine("HTTP/1.1 404 Not Found")
                .log().all()
                .extract().response();
    }
}

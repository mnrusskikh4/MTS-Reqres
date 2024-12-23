package tests;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PutTest {

    @Test
    @Description("Тест успешного обновления данных пользователя")
    void testUpdateUserSuccessfully() {
        Response response = RestAssured
                .given()
                .contentType("application/json")
                .body("{\"name\": \"morpheus\", \"job\": \"zion resident\"}")
                .put("https://reqres.in/api/users/2");

        assertEquals(200, response.statusCode());
        assertEquals("morpheus", response.jsonPath().getString("name"));
        assertEquals("zion resident", response.jsonPath().getString("job"));
    }

    @Test
    @Description("Тест обновления данных не найденного пользователя")
    void testUpdateUserNotFound() {
        Response response = RestAssured
                .given()
                .contentType("application/json")
                .body("{\"name\": \"morpheus\", \"job\": \"zion resident\"}")
                .put("https://reqres.in/api/users/99999");
        assertEquals(404, response.statusCode());
        assertEquals("", response.body().asString());
    }
}

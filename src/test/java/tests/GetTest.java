package tests;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetTest {

    @Test
    @Description("Тест успешного запроса данных пользователя")
    void testGetUserSuccessfully() {
        Response response = RestAssured
                .get("https://reqres.in/api/users/2");

        assertEquals(200, response.statusCode());
        assertEquals("Janet", response.jsonPath().getString("data.first_name"));
        assertEquals("Weaver", response.jsonPath().getString("data.last_name"));
    }

    @Test
    @Description("Тест запроса не найденного пользователя")
    void testGetUserNotFound() {
        Response response = RestAssured
                .get("https://reqres.in/api/users/99999");

        assertEquals(404, response.statusCode());
    }
}


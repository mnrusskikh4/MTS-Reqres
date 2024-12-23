package tests;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostTest {

    @Test
    @Description("Тест успешной регистрации пользователя")
    void testSuccessfulRegistration() {
        Response response = RestAssured
                .given()
                .contentType("application/json")
                .body("{\"email\": \"eve.holt@reqres.in\", \"password\": \"pistol\"}")
                .post("https://reqres.in/api/register");

        assertEquals(200, response.statusCode());
        assertEquals("QpwL5tke4Pnpja7X4", response.jsonPath().getString("token"));
    }

    @Test
    @Description("Тест регистрации пользователя без пароля")
    void testRegistrationWithoutPassword() {
        Response response = RestAssured
                .given()
                .contentType("application/json")
                .body("{\"email\": \"eve.holt@reqres.in\"}")
                .post("https://reqres.in/api/register");

        assertEquals(400, response.statusCode());
        assertEquals("Missing password", response.jsonPath().getString("error"));
    }
}


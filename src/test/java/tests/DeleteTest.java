package tests;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteTest {

    @Test
    @Description("Тест успешного удаления пользователя")
    void testDeleteUserSuccessfully() {
        int statusCode = RestAssured
                .delete("https://reqres.in/api/users/2")
                .statusCode();

        assertEquals(204, statusCode);
    }

    @Test
    @Description("Тест удаления не найденного пользователя")
    void testDeleteUserNotFound() {
        Response response = RestAssured
                .delete("https://reqres.in/api/users/99999");

        assertEquals(404, response.statusCode());
        assertEquals("", response.body().asString());
    }
}


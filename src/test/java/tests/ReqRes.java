package tests;

import models.CreateUserResponseModel;
import models.GetUserResponseModel;
import models.GetUsersResponseModel;
import models.UpdateUserResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static specs.BaseSpecs.*;

public class ReqRes extends TestBase {
    TestData testData = new TestData();

    String[] userId = {"1", "2", "3", "4", "5", "6"};
    int randomIndex = ThreadLocalRandom.current().nextInt(userId.length);
    String randomUserId = userId[randomIndex];

    @Test
    @Tag("Positive")
    @DisplayName("Получение списка пользователей")
    void successfulGetListUsersTest() {
        GetUsersResponseModel response = step("Получение списка пользователей", () ->
                given(requestSpec)
                        .when()
                        .get("/users")
                        .then()
                        .spec(responseSpec(200))
                        .extract().as(GetUsersResponseModel.class));
        step("Проверка данных ответа для списка пользователей", () -> {
            assertEquals("1", response.getPage());
            assertEquals("6", response.getPerPage());
            assertEquals("12", response.getTotal());
            assertEquals("2", response.getTotalPages());
            assertNotNull(response.getData());
            assertNotNull(response.getSupport());
            assertNotNull(response.get_meta());
        });
    }

    @Test
    @Tag("Positive")
    @DisplayName("Получение пользователя по id")
    void successfulGetUserIdTest() {
        GetUserResponseModel response = step("Получение пользователя по ID", () ->
                given(requestSpec)
                        .when()
                        .get("/users/1")
                        .then()
                        .spec(responseSpec(200))
                        .extract().as(GetUserResponseModel.class));
        step("Проверка данных ответа для одного пользователя", () -> {
            assertEquals(1, response.getData().getId());
            assertEquals("george.bluth@reqres.in", response.getData().getEmail());
            assertEquals("George", response.getData().getFirstName());
            assertEquals("Bluth", response.getData().getLastName());
            assertNotNull(response.getData().getAvatar());
            assertNotNull(response.getSupport());
            assertNotNull(response.get_meta());
        });
    }

    @Test
    @Tag("Negative")
    @DisplayName("Невозможно создание нового пользователя")
    void NotSuccessfulCreateNewUserTest() {
        CreateUserResponseModel response = step("Попытка создания пользователя", () ->
                given(requestSpec)
                        .body(testData.generateDataUserJson())
                        .when()
                        .post("/register")
                        .then()
                        .spec(responseSpec(400))
                        .extract().as(CreateUserResponseModel.class));
        step("Проверка невозможности создания пользователя", () -> {
            assertEquals("Note: Only defined users succeed registration", response.getError());
        });
    }

    @Test
    @Tag("Positive")
    @DisplayName("Обновление пользователя методом PUT")
    void successfulUpdateUserMethodPutTest() {
        Map<String, String> emailAndFirstNameAndLastName = new HashMap<>();
        emailAndFirstNameAndLastName.put("email", testData.email);
        emailAndFirstNameAndLastName.put("first_name", testData.firstName);
        emailAndFirstNameAndLastName.put("last_name", testData.lastName);

        UpdateUserResponseModel response = step("Обновление пользователя методом PUT", () ->
                given(requestSpec)
                        .body(emailAndFirstNameAndLastName)
                        .when()
                        .put("/users/" + randomUserId)
                        .then()
                        .spec(responseSpec(200))
                        .extract().as(UpdateUserResponseModel.class));
        step("Проверка обновления пользователя", () -> {
            assertEquals((testData.lastName), response.getLastName());
            assertEquals((testData.firstName), response.getFirstName());
            assertEquals((testData.email), response.getEmail());
            assertNotNull(response.getUpdatedAt());
        });
    }

    @Test
    @Tag("Positive")
    @DisplayName("Обновление пользователя методом PATCH")
    void successfulUpdateUserMethodPatchTest() {
        Map<String, String> emailAndFirstNameAndLastName = new HashMap<>();
        emailAndFirstNameAndLastName.put("email", testData.email);
        emailAndFirstNameAndLastName.put("first_name", testData.firstName);
        emailAndFirstNameAndLastName.put("last_name", testData.lastName);

        UpdateUserResponseModel response = step("Обновление пользователя методом PATCH", () ->
                given(requestSpec)
                        .body(emailAndFirstNameAndLastName)
                        .when()
                        .patch("/users/" + randomUserId)
                        .then()
                        .spec(responseSpec(200))
                        .extract().as(UpdateUserResponseModel.class));
        step("Проверка обновления пользователя", () -> {
            assertEquals((testData.lastName), response.getLastName());
                assertEquals((testData.firstName), response.getFirstName());
            assertEquals((testData.email), response.getEmail());
            assertNotNull(response.getUpdatedAt());
        });
    }

    @Test
    @Tag("Positive")
    @DisplayName("Удаление пользователя")
    void successfulDeleteUserTest() {
        step("Проверка удаления пользователя", () ->
                given(requestSpec)
                        .when()
                        .delete("/users/" + randomUserId)
                        .then()
                        .spec(responseSpec(204)));
    }
}

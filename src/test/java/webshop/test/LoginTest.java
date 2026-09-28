package webshop.test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import webshop.page.RegistrationPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.prompt;
import static webshop.config.Config.WEBSHOP_LOGIN_HOST;
import static webshop.util.TestData.*;


public class LoginTest {

    @BeforeEach
    void beforeEach() {
        String password = randomPassword();
        String email = randomEmail();
        String firstName = randomFirstName();
        String lastName = randomLastName();

        open(WEBSHOP_LOGIN_HOST, RegistrationPage.class)
                .registration(firstName, lastName, email, password)
                .checkHappyRegistration();
    }

    @Test
    @DisplayName("Успешна авторизация зарегестрированного пользователя")
    void loginAuthTest() {
        open(WEBSHOP_LOGIN_HOST);


    }
}

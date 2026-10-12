package webshop.test;

import org.junit.jupiter.api.*;
import webshop.TestBase;
import webshop.page.LoginPage;
import webshop.page.RegistrationPage;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;
import static webshop.config.Config.WEBSHOP_HOST;
import static webshop.config.Config.WEBSHOP_LOGIN_HOST;
import static webshop.util.TestData.*;


public class LoginTest extends TestBase {

    String password = randomPassword();
    String email = randomEmail();
    String firstName = randomFirstName();
    String lastName = randomLastName();

    @BeforeEach
    void beforeEach() {
        open(WEBSHOP_HOST, RegistrationPage.class)
                .registration(firstName, lastName, email, password)
                .checkHappyRegistration();

        clearBrowserCookies();
        clearBrowserLocalStorage();
    }

    @Test
    @Tag("positive")
    @DisplayName("Успешна авторизация зарегестрированного пользователя")
    void loginAuthTest() {
        open(WEBSHOP_LOGIN_HOST, LoginPage.class)
                .enterEmail(email)
                .enterPassword(password)
                .rememberMeCheckboxClick()
                .submitLogin()
                .checkHeaderEmailLink(email)
                .checkHeaderUserLogin();
    }
}

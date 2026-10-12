package webshop.test;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import webshop.TestBase;
import webshop.page.RegistrationPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static webshop.config.Config.WEBSHOP_HOST;
import static webshop.util.TestData.*;

public class RegistrationTest extends TestBase {

    private String email = randomEmail();
    private String password = randomPassword();
    private String lastName = randomLastName();
    private String firtName = randomFirstName();

    @Test
    @Tag("positive")
    @DisplayName("Успешная регистрация нового пользователя")
    void RegistrationTest() {

        open(WEBSHOP_HOST, RegistrationPage.class)
                .registrationButton()
                .checkRegistrationPage()
                .enterGenderFemale()
                .enterFirstName(firtName)
                .enterLastName(lastName)
                .enterEmail(email)
                .enterPassword(password)
                .enterConfirmedPassword(password)
                .enterRegistrationButton()
                .checkHappyRegistration();
    }
}

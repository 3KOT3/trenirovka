import com.github.javafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import page.RegistrationPage;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static config.Config.WEBSHOP_HOST;

public class RegistrationTest {

    private static final Faker faker = new Faker();
    String password = faker.gameOfThrones().character() + faker.number().randomNumber();
    String email = faker.internet().emailAddress();

    @Test
    @DisplayName("Успешная регистрация нового пользователя")
    void RegistrationTest() {

        open(WEBSHOP_HOST, RegistrationPage.class)
                .registrationButton()
                .checkRegistrationPage("Register")
                .enterGenderFemale()
                .enterFirstName(faker.name().firstName())
                .enterLastName(faker.name().lastName())
                .enterEmail(email)
                .enterPassword(password)
                .enterConfirmedPassword(password)
                .enterRegistrationButton()
                .checkHappyRegistration("Your registration completed");
    }
}

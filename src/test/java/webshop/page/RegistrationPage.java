package webshop.page;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static webshop.config.Config.WEBSHOP_HOST;

public class RegistrationPage {

    private final SelenideElement registerButton = $(".ico-register");
    private final SelenideElement checkRegisterPage = $("div.registration-page .page-title");
    private final SelenideElement selectGender = $("#gender-female");
    private final SelenideElement FirstNameInput = $("#FirstName");
    private final SelenideElement LastNameInput = $("#LastName");
    private final SelenideElement emailInput = $("#Email");
    private final SelenideElement passwordInput = $("#Password");
    private final SelenideElement confirmPasswordInput = $("#ConfirmPassword");
    private final SelenideElement clickRegistrationButton = $("#register-button");
    private final SelenideElement HappyRegistration = $("div.registration-result-page .page-body");

    public RegistrationPage registration(String firstName, String lastName, String email, String password) {
        registrationButton()
                .checkRegistrationPage()
                .enterGenderFemale()
                .enterFirstName(firstName)
                .enterLastName(lastName)
                .enterEmail(email)
                .enterPassword(password)
                .enterConfirmedPassword(password)
                .enterRegistrationButton()
                .checkHappyRegistration();
        return this;
    }


    @Step("Открыть страницу регистрации")
    public RegistrationPage registrationButton() {
        registerButton.click();
        return this;
    }

    @Step("Проверка нахождения на странице регистрации")
    public RegistrationPage checkRegistrationPage() {
        checkRegisterPage.shouldHave(exactText("Register"));
        return this;
    }

    @Step("Выбрать женский гендер")
    public RegistrationPage enterGenderFemale() {
        selectGender.click();
        return this;
    }

    @Step("Ввести имя")
    public RegistrationPage enterFirstName(String firstName) {
        FirstNameInput.setValue(firstName);
        return this;
    }

    @Step("Ввести фамилию")
    public RegistrationPage enterLastName(String lastName) {
        LastNameInput.setValue(lastName);
        return this;
    }

    @Step("Ввести электронную почту")
    public RegistrationPage enterEmail(String email) {
        emailInput.setValue(email);
        return this;
    }

    @Step("Ввести пароль")
    public RegistrationPage enterPassword(String password) {
        passwordInput.setValue(password);
        return this;
    }

    @Step("Повторно ввести пароль")
    public RegistrationPage enterConfirmedPassword(String confirmedPassword) {
        confirmPasswordInput.setValue(confirmedPassword);
        return this;
    }

    @Step("Нажать кнопку регистрации")
    public RegistrationPage enterRegistrationButton() {
        clickRegistrationButton.click();
        return this;
    }

    @Step("Проверка успешной регистрации")
    public RegistrationPage checkHappyRegistration() {
        HappyRegistration.shouldHave(exactText("Your registration completed"));
        return this;
    }
}

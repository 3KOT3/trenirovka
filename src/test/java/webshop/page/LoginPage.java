package webshop.page;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    private final SelenideElement emailInput = $("#Email");
    private final SelenideElement passwordInput = $("#Password");
    private final SelenideElement rememberMeCheckbox = $("#RememberMe");
    private final SelenideElement lohinButton = $(".login-button");
    private final SelenideElement checkHeaderShowsEmailLink = $(".header-links a[href='/customer/info']");
    private final SelenideElement checkHeaderShowsLogoutLink = $(".ico-logout");

    @Step("Ввести электронную почту")
    public LoginPage enterEmail(String email) {
        emailInput.setValue(email);
        return this;
    }

    @Step("Ввести пароль")
    public LoginPage enterPassword (String password) {
        passwordInput.setValue(password);
        return this;
    }

    @Step("Нажать кнопку запомнить меня")
    public LoginPage rememberMeCheckboxClick() {
        rememberMeCheckbox.click();
        return this;
    }

    @Step("Нажать внопка входа  аккаунт")
    public LoginPage submitLogin() {
        lohinButton.click();
        return this;
    }

    @Step("Проверка электронной почты в хедере")
    public LoginPage checkHeaderEmailLink(String email) {
        checkHeaderShowsEmailLink.shouldHave(exactText(email));
        return this;
    }

    @Step("Проерка, что пользователь авторизовался")
    public LoginPage checkHeaderUserLogin() {
        checkHeaderShowsLogoutLink.shouldHave(exactText("Log out"));
        return this;
    }
}

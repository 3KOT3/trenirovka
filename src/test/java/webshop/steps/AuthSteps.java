package webshop.steps;

import com.github.javafaker.Faker;
import webshop.page.RegistrationPage;

import static com.codeborne.selenide.Selenide.open;
import static webshop.config.Config.WEBSHOP_HOST;
import static webshop.util.TestData.*;
import static webshop.util.TestData.randomLastName;


public class AuthSteps {
    public static final Faker faker = new Faker();

    String password = randomPassword();
    String email = randomEmail();
    String firstName = randomFirstName();
    String lastName = randomLastName();

    public void RegistrationNewUser(){
        open(WEBSHOP_HOST, RegistrationPage .class)
                .registration(firstName, lastName, email, password)
                .checkHappyRegistration();
    }
}

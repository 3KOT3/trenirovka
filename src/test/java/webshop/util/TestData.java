package webshop.util;

import com.github.javafaker.Faker;


public class TestData {

    public static final Faker faker = new Faker();

    public static String randomLastName() {
        return faker.name().lastName();
    }

    public static String randomFirstName() {
        return faker.name().firstName();
    }

    public static String randomEmail() {
        return faker.internet().emailAddress();
    }

    public static String randomPassword(){
        return faker.gameOfThrones().character() + faker.number().randomNumber();
    }
}

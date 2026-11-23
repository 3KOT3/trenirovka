package booking.util;

import com.github.javafaker.Faker;

public class TestData {
    public static final Faker faker = new Faker();

    public static String randomLastName() {
        return faker.name().lastName();
    }

    public static String randomFirstName() {
        return faker.name().firstName();
    }

    public static Integer randomTotalPrice() {
        return faker.number().numberBetween(100, 10000);
    }

    public static boolean randomBoolean() {
        return faker.bool().bool();
    }

    public static String randomAdditionalNeeds() {
        return faker.food().fruit();
    }
}

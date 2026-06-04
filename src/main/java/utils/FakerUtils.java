package utils;

import com.github.javafaker.Faker;

public class FakerUtils {

    private static final Faker faker = new Faker();
    public static String getEmail() {
         return faker.regexify("[a-z]{6,10}") + faker.number().digits(3) + "@example.com";
    }

    public static String getPassword() {
        return faker.regexify("[A-Z][a-z]{5,8}") + "@123";
    }

    public static String getFirstName() {
        return faker.regexify("[A-Z][a-z]{4,10}");
    }

    public static String getLastName() {
        return faker.regexify("[A-Z][a-z]{5,12}");
    }

    public static String getPhone() {
        return "+2507" + faker.number().digits(8);
    }
}
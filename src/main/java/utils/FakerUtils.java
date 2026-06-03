package utils;

import com.github.javafaker.Faker;

public class FakerUtils {

    public static String getEmail() {
        Faker faker = new Faker();
        return faker.regexify("[A-Z][a-z]{6,10}");
    }





}
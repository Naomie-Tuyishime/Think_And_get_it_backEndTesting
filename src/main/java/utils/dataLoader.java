package utils;

import java.io.FileInputStream;
import java.util.Properties;

public class dataLoader {
    private static Properties properties;

    static {
        try {
            FileInputStream fis = new FileInputStream("src/main/resources/data.properties");
            properties = new Properties();
            properties.load(fis);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config file");
        }

    }
    public static String getProductSlug (){
        return properties.getProperty("SLUG");
    }
    public static String getProductId (){
        return properties.getProperty ("id");
    }

}

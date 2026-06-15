package payload;

import utils.FakerUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static utils.FakerUtils.getPhone;

public class ProductPayload {

    public static Map<String, Object> createProduct(String categoryId) {

        Map<String, Object> payload = new HashMap<>();

        payload.put("name", FakerUtils.getFirstName());
        payload.put("description", FakerUtils.getDescription());
        payload.put("price", 100);
        payload.put("comparePrice", 120);
        payload.put("categoryId", categoryId);

        payload.put("tags", List.of("electronics", "sale"));
        payload.put("isFeatured", true);
        payload.put("isFlashSale", true);
        payload.put("flashSalePrice", 80);

        payload.put("variants", defaultVariant());

        return payload;
    }


    public static List<Map<String, Object>> defaultVariant() {

        Map<String, Object> variant = new HashMap<>();

        variant.put("size", "M");
        variant.put("color", "Black");
        variant.put("colorHex", "#000000");
        variant.put("sku", "SKU-" + getPhone());
        variant.put("stock", 10);
        variant.put("price", 100);

        return List.of(variant);
    }

    public static Map<String, Object> minimalProduct() {

        Map<String, Object> payload = new HashMap<>();

        payload.put("name", FakerUtils.getFirstName());
        payload.put("description", FakerUtils.getDescription());
        payload.put("price", 100);

        return payload;
    }


    public static Map<String, Object> missingName(String categoryId) {

        Map<String, Object> payload = createProduct(categoryId);
        payload.remove("name");

        return payload;
    }


    public static Map<String, Object> missingVariants(String categoryId) {

        Map<String, Object> payload = createProduct(categoryId);
        payload.remove("variants");

        return payload;
    }
}
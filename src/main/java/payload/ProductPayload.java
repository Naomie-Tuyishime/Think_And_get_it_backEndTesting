package payload;

import utils.FakerUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static routes.Routes.DEFAULT_SIZE;
import static routes.Routes.DEFAULT_COLOR;
import static utils.FakerUtils.*;

public class ProductPayload {

    public static Map<String, Object> createProduct(String categoryId) {

        Map<String, Object> payload = new HashMap<>();

        payload.put("name", getProductName());
        payload.put("description",getDescription());
        payload.put("price", price());
        payload.put("comparePrice", price());
        payload.put("categoryId", categoryId);

        payload.put("tags", List.of("electronics", "sale"));
        payload.put("isFeatured", true);
        payload.put("isFlashSale", true);
        payload.put("flashSalePrice", price());

        payload.put("variants", defaultVariant());

        return payload;
    }


    public static List<Map<String, Object>> defaultVariant() {

        Map<String, Object> variant = new HashMap<>();

        variant.put("size", DEFAULT_SIZE);
        variant.put("color", DEFAULT_COLOR);
        variant.put("colorHex", DEFAULT_COLOR);
        variant.put("sku", "SKU-" + getPhone());
        variant.put("stock", price());
        variant.put("price", price());

        return List.of(variant);
    }
    public static  Map<String, Object> updateProduct (String categoryId){


        Map<String, Object> payload = new HashMap<>();

        payload.put("name", getProductName());
        payload.put("description",getDescription());
        payload.put("price", price());
        payload.put("comparePrice", price());
        payload.put("categoryId", categoryId);

        payload.put("tags", List.of("electronics", "sale"));
        payload.put("isFeatured", true);
        payload.put("isFlashSale", true);
        payload.put("flashSalePrice", price());

        return payload;


    }

}
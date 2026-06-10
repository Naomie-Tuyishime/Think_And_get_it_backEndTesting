package utils.payloads;

import java.util.HashMap;
import java.util.Map;

import static utils.FakerUtils.*;

public class AddressPayload {

    public static Map<String, Object> createAddress() {

        Map<String, Object> payload = new HashMap<>();

        payload.put("label", "Home");
        payload.put("firstName", getFirstName());
        payload.put("lastName", getLastName());
        payload.put("phone", getPhone());
        payload.put("street", getStreet());
        payload.put("city", getCity());
        payload.put("state", getState());
        payload.put("country", getCountry());
        payload.put("postalCode", getPostalCode());
        payload.put("isDefault", true);

        return payload;
    }
}
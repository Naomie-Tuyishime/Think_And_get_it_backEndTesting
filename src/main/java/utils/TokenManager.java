package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;

public class TokenManager {

    private static final String FILE_PATH = "src/test/resources/tokens.json";
    private static final ObjectMapper mapper = new ObjectMapper();


    public static void saveTokens(String type, String token, String refreshToken) {
        try {
            File file = new File(FILE_PATH);

            ObjectNode root;

            if (file.exists() && file.length() > 0) {
                root = (ObjectNode) mapper.readTree(file);
            } else {
                root = mapper.createObjectNode();
            }

            ObjectNode tokenNode = mapper.createObjectNode();
            tokenNode.put("token", token);
            tokenNode.put("refreshToken", refreshToken);

            root.set(type, tokenNode);

            mapper.writerWithDefaultPrettyPrinter().writeValue(file, root);

        } catch (IOException e) {
            throw new RuntimeException("Failed to save tokens", e);
        }
    }

    public static String getToken(String type) {
        try {
            File file = new File(FILE_PATH);

            if (!file.exists()) {
                throw new RuntimeException("Token file not found!");
            }

            JsonNode root = mapper.readTree(file);

            return root.path(type).path("token").asText();

        } catch (IOException e) {
            throw new RuntimeException("Failed to read token", e);
        }
    }

    public static String getRefreshToken(String type) {
        try {
            File file = new File(FILE_PATH);

            if (!file.exists()) {
                throw new RuntimeException("Token file not found!");
            }

            JsonNode root = mapper.readTree(file);

            return root.path(type).path("refreshToken").asText();

        } catch (IOException e) {
            throw new RuntimeException("Failed to read refresh token", e);
        }
    }
}
package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.File;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import static utils.ConfigLoader.getBaseUrl;

public class TokenManager {
    private static final String TOKENS_PATH = "src/test/resources/tokens.json";
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final HttpClient client = HttpClient.newHttpClient();

    public static void saveTokens(String type, String token, String refresh) {
        if (token == null || token.isBlank() || refresh == null || refresh.isBlank()) return;
        try {
            File f = new File(TOKENS_PATH);
            ObjectNode root = (f.exists() && f.length() > 0) ? (ObjectNode) mapper.readTree(f) : mapper.createObjectNode();
            long exp = System.currentTimeMillis() + (7L * 24 * 60 * 60 * 1000);
            root.set(type, mapper.createObjectNode().put("token", token).put("refreshToken", refresh).put("expiresAt", exp));
            mapper.writerWithDefaultPrettyPrinter().writeValue(f, root);
        } catch (Exception e) { throw new RuntimeException(e); }
    }

    public static String getToken(String type) {
        try {
            File f = new File(TOKENS_PATH);
            if (!f.exists() || f.length() == 0) throw new RuntimeException("Tokens store is empty. Please run LoginTest first.");

            JsonNode node = mapper.readTree(f).path(type);
            String token = node.path("token").asText("").trim();
            String refresh = node.path("refreshToken").asText("").trim();

            if (token.isEmpty() || refresh.isEmpty()) throw new RuntimeException("Tokens are blank inside profile schema.");

            if (System.currentTimeMillis() + 30000 >= node.path("expiresAt").asLong(0)) {
                renewToken(type, refresh);
                node = mapper.readTree(new File(TOKENS_PATH)).path(type);
            }
            return node.path("token").asText();
        } catch (Exception e) { throw new RuntimeException(e); }
    }

    private static void renewToken(String type, String refresh) throws Exception {
        String body = mapper.writeValueAsString(Map.of("refreshToken", refresh));
        String url = getBaseUrl().replaceAll("/+$", "") + "/api/v1/auth/refresh";

        HttpRequest req = HttpRequest.newBuilder().uri(URI.create(url)).header("Content-Type", "application/json").POST(HttpRequest.BodyPublishers.ofString(body)).build();
        HttpResponse<String> res = client.send(req, HttpResponse.BodyHandlers.ofString());

        JsonNode data = mapper.readTree(res.body()).path("data");
        saveTokens(type, data.path("token").asText(), data.path("refreshToken").asText());
    }

    public static String getRefreshToken(String type) {
        try { return mapper.readTree(new File(TOKENS_PATH)).path(type).path("refreshToken").asText(); }
        catch (Exception e) { throw new RuntimeException(e); }
    }
}

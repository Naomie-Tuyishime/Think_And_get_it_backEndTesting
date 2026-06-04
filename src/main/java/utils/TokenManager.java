package utils;

import java.time.Instant;

public class TokenManager {

    private static String accessToken;
    private static String refreshToken;
    private static Instant expiryTime;

    public synchronized static void setToken(String token, String refresh, Integer expiresInSeconds) {
        accessToken = token;
        refreshToken = refresh;

        if (expiresInSeconds != null) {
            expiryTime = Instant.now().plusSeconds(expiresInSeconds - 60);
        } else {
            expiryTime = Instant.now().plusSeconds(600);
        }
    }

    public static String getToken(String type) {
        if (accessToken == null || isExpired()) {
            throw new RuntimeException("Token not initialized. Call login/register test first.");
        }
        return accessToken;
    }

    public static String getRefreshToken() {
        return refreshToken;
    }

    private static boolean isExpired() {
        return expiryTime == null || Instant.now().isAfter(expiryTime);
    }

    public static void clearToken() {
        accessToken = null;
        refreshToken = null;
        expiryTime = null;
    }
}
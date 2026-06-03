package routes;

public class Routes {

    public static final String USER_REGISTER = "auth/register";
    public static final String USER_LOGIN = "auth/login";
    public static final String VERIFY_EMAIL = "auth/verify-email/{token}";
    public static final String FORGOT_PASSWORD = "auth/forgot-passowrd";
    public static final String RESERT_PASSWORD = "auth/reset-password/{token}";
    public static final String GET_SINGLE_USER = "auth/me";
    public static final String REFRESH_ACCESS_TOKEN = "auth/refresh";
}
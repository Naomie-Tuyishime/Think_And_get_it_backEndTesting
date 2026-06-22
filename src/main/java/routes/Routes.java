package routes;

public class Routes {


    public static final String USER_REGISTER = "auth/register";
    public static final String USER_LOGIN = "auth/login";
    public static final String VERIFY_EMAIL = "auth/verify-email/{token}";
    public static final String FORGOT_PASSWORD = "auth/forgot-passowrd";
    public static final String RESERT_PASSWORD = "auth/reset-password/{token}";
    public static final String GET_SINGLE_USER = "auth/me";
    public static final String REFRESH_ACCESS_TOKEN = "auth/refresh";
    public static final String  USER_PROFILE = "users/profile";
    public static final String USER_CHANGE_PASSWORD = "users/change-password";
    public static final String UsersAddress = "users/addresses";
    public static final String CATEGORIES = "/categories";
    public static final String SINGLECATEGORY = "/categories/{slug}";
    public static final String SINGLEPRODUCTID= "categories/{id}";
    public static final String PRODUCTS = "/products";
   public static final String DEFAULT_SIZE = "M";
    public static final String DEFAULT_COLOR = "Black";
    public static final String DEFAULT_COLOR_HEX = "#000000";
   public static final String PRODUCT_ID = "ddb77290-34ef-4e20-a19b-febca2c5c9d1";
   public static final String SINGLEPRODUCT ="b1320cf8-e781-4ed0-ade6-85f0a16cdc08";
   public static final String PRODUCT = "products/{id}";
   public static final Integer NEGATIVENUMBERS=-23;
    public static final String FAKEPRODUCTID = "9999999999999";


}
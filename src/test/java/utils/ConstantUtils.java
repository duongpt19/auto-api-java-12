package utils;

public class ConstantUtils {
    public static final String HOST = "http://localhost";
    public static final int PORT = 3000;
    public static final String LOGIN_API= "/api/login";
    public static final String CREATE_USER_API= "/api/user";
    public static final String GET_USER_API= "/api/user/{userId}";
    public static final String GET_COUNTRIES_API = "/api/v1/countries";
    public static final String GET_COUNTRY_API = "/api/v1/countries/{code}";
    public static final String GET_COUNTRY_WITH_FILTER_API = "/api/v3/countries";
    public static final String GET_COUNTRY_WITH_PAGINATION_API = "/api/v4/countries";
    public static final String GET_COUNTRY_WITH_FILTER_HEADER_API = "/api/v5/countries";
    public static final String X_POWERED_BY_HEADER = "X-Powered-By";
    public static final String X_POWERED_BY_HEADER_VALUE = "Express";
    public static final String CONTENT_TYPE_HEADER = "Content-Type";
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String CONTENT_TYPE_HEADER_VALUE = "application/json; charset=utf-8";
    public static final String API_KEY_HEADER = "api-key";
    public static final String API_KEY_HEADER_VALUE = "private";
    public static final String GDP_FILTER = "gdp";
    public static final String OPERATOR_FILTER = "operator";
    public static final String PAGE = "page";
    public static final String SIZE = "size";
    public static final String RESPONSE_CONTENT_TYPE_HEADER_VALUE = "application/json; charset=utf-8";
    public static final String REQUEST_CONTENT_TYPE_HEADER_VALUE = "application/json";
}

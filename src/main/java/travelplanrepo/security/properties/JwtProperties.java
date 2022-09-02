package travelplanrepo.security.properties;

public interface JwtProperties {
    String SECRET = "TravelPlan";
    int EXPIRATION_TIME = 60000 * 60;
    String TOKEN_PREFIX = "Bearer";
    String HEADER_STRING = "Authorization";
}

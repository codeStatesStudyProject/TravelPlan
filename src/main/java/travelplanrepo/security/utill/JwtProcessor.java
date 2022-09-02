package travelplanrepo.security.utill;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;
import travelplanrepo.security.authentication.UserAccount;
import travelplanrepo.security.properties.JwtProperties;

import java.util.Date;

@Component
public class JwtProcessor {

    public String createAuthJwtToken(UserAccount userAccount) {
        return JWT.create()
                .withSubject(userAccount.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
                .withClaim("id", userAccount.getAccount().getId())
                .withClaim("username", userAccount.getUsername())
                .sign(Algorithm.HMAC512(JwtProperties.SECRET));
    }

    public String decodeJwtToken(String jwtToken, String secretKey, String claim) {
        return JWT.require(Algorithm.HMAC512(secretKey)).build()
                .verify(jwtToken)
                .getClaim(claim)
                .asString();
    }

    public String extractBearer(String jwtHeader) {
        int pos = jwtHeader.lastIndexOf(" ");
        return jwtHeader.substring(pos + 1);
    }
}

package travelplanrepo.global.security.utill;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import travelplanrepo.global.security.authentication.UserAccount;
import travelplanrepo.global.security.properties.JwtProperties;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class JwtProcessor {

    public String createAuthJwtToken(UserAccount userAccount) {

        Map<String, Object> claims = createClaims(userAccount);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userAccount.getAccount().getId().toString())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
                .signWith(Keys.hmacShaKeyFor(JwtProperties.SECRET.getBytes()))
                .compact();
    }

    private Map<String, Object> createClaims(UserAccount userAccount) {
        Map<String, Object> claims = new HashMap<>();
        List<String> roleList = userAccount.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        claims.put("username", userAccount.getUsername());
        claims.put("role", roleList);
        return claims;
    }

    public List<GrantedAuthority> getAuthorities(List<String> roleList) {
        return roleList.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    public Claims verifyJwtToken(String jwtToken) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(JwtProperties.SECRET.getBytes()))
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();
    }

    public String extractBearer(String jwtHeader) {
        int pos = jwtHeader.lastIndexOf(" ");
        return jwtHeader.substring(pos + 1);
    }
}

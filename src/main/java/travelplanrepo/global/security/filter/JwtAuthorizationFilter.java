package travelplanrepo.global.security.filter;

import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import travelplanrepo.global.security.authentication.Principal;
import travelplanrepo.global.security.properties.JwtProperties;
import travelplanrepo.global.security.utill.JwtProcessor;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private final JwtProcessor jwtProcessor;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager,
                                  JwtProcessor jwtProcessor) {
        super(authenticationManager);
        this.jwtProcessor = jwtProcessor;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String jwtHeader = request.getHeader(JwtProperties.HEADER_STRING);

        if (jwtHeader == null || !jwtHeader.startsWith(JwtProperties.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        String jwtToken = jwtProcessor.extractBearer(jwtHeader);
        Claims claims = jwtProcessor.verifyJwtToken(jwtToken);
        Long id = Long.valueOf(claims.getSubject());
        List<GrantedAuthority> authorities = jwtProcessor.getAuthorities((List<String>) claims.get("role"));

        Principal principal = new Principal(id, (String) claims.get("username"));

        Authentication authentication =
                new UsernamePasswordAuthenticationToken(principal, null, authorities);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }
}

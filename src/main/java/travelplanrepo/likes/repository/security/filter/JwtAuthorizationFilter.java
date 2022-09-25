package travelplanrepo.likes.repository.security.filter;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import travelplanrepo.account.entity.Account;
import travelplanrepo.account.repository.AccountRepository;
import travelplanrepo.likes.repository.security.authentication.UserAccount;
import travelplanrepo.likes.repository.security.properties.JwtProperties;
import travelplanrepo.likes.repository.security.utill.JwtProcessor;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private final AccountRepository accountRepository;
    private final JwtProcessor jwtProcessor;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, AccountRepository accountRepository,
                                  JwtProcessor jwtProcessor) {
        super(authenticationManager);
        this.accountRepository = accountRepository;
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
        String email = jwtProcessor.decodeJwtToken(jwtToken, JwtProperties.SECRET, "username");

        if (email != null) {
            Account account = accountRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("해당 email을 갖는 Account를 찾을 수 없습니다."));

            UserAccount userAccount = new UserAccount(account);
            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(userAccount, null, userAccount.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);
    }
}

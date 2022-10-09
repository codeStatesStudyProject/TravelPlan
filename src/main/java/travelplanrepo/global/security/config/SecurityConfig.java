package travelplanrepo.global.security.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import travelplanrepo.global.security.filter.AccountAccessDeniedHandler;
import travelplanrepo.global.security.filter.AccountAuthenticationEntryPoint;
import travelplanrepo.global.security.filter.JwtAuthenticationFilter;
import travelplanrepo.global.security.filter.JwtAuthorizationFilter;
import travelplanrepo.global.security.utill.JwtProcessor;


@Slf4j
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtProcessor jwtProcessor;
    private final AuthenticationConfiguration authenticationConfiguration;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationManager authenticationManager = authenticationConfiguration.getAuthenticationManager();

        http
                .csrf().disable()
                .formLogin().disable()
                .httpBasic().disable();

        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http
                .addFilter(new JwtAuthorizationFilter(authenticationManager, jwtProcessor))
                .addFilter(new JwtAuthenticationFilter(authenticationManager, jwtProcessor));

        http
                .authorizeRequests()
                .mvcMatchers(HttpMethod.POST, "/myLogin").permitAll()
                .mvcMatchers(HttpMethod.POST, "/account").permitAll()
//                .anyRequest().authenticated();
                .anyRequest().permitAll();

        http
                .exceptionHandling()
                .accessDeniedHandler(new AccountAccessDeniedHandler())
                .authenticationEntryPoint(new AccountAuthenticationEntryPoint());


        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityConfig() {
        return (web) -> web
                .ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}

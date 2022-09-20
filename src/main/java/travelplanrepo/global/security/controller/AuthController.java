package travelplanrepo.global.security.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import travelplanrepo.global.security.dto.form.LoginForm;
import travelplanrepo.global.security.properties.JwtProperties;
import travelplanrepo.global.security.sevice.AuthService;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/myLogin")
    public String login(@RequestBody LoginForm loginForm, HttpServletResponse response) {
        String jwtToken = authService.login(loginForm.getEmail(), loginForm.getPassword());

        response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + " " + jwtToken);

        return "success login";
    }
}

package travelplanrepo.global.security.filter;

import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import travelplanrepo.global.exception.dto.ErrorResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AccountAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {

        ErrorResponse authenticationEx =
                new ErrorResponse(HttpStatus.FORBIDDEN.value(), "Forbidden", "접근권한이 없습니다.");

        String authenticationExJson = new Gson().toJson(authenticationEx);
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(authenticationExJson);
    }
}

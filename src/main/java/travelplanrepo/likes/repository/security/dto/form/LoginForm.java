package travelplanrepo.likes.repository.security.dto.form;

import lombok.Data;

@Data
public class LoginForm {
    String email;
    String password;
}

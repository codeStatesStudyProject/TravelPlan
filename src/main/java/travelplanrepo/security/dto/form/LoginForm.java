package travelplanrepo.security.dto.form;

import lombok.Data;

@Data
public class LoginForm {
    String email;
    String password;
}

package travelplanrepo.domain.account.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import travelplanrepo.global.common.auditing.BaseTime;
import travelplanrepo.domain.File.File;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Account extends BaseTime {
    @Id @GeneratedValue
    private Long id;
    private String email;
    private String password;
    private String nickName;
    private File img;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private Integer age;
    private String mobile;

    @Enumerated(EnumType.STRING)
    private Role role;

    public Account(String email, String password, Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }
}

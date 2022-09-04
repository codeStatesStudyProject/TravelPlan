package travelplanrepo.account.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import travelplanrepo.common.auditing.BaseTime;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Account extends BaseTime {
    @Id @GeneratedValue
    @Column(name = "account_id")
    private Long id;

    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public Account(String email, String password, Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }
}

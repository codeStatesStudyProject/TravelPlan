package travelplanrepo.domain.account.entity;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import travelplanrepo.domain.File.domain.File;
import travelplanrepo.global.common.auditing.BaseTime;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    private String nickName;
    private File img;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private Integer age;
    private String mobile;
    private boolean emailAuth;
    private String authToken;

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.LAZY)
    private List<Role> roleList = new ArrayList<>();

    public void emailVerifiedSuccess() {
        this.emailAuth = true;
    }
}

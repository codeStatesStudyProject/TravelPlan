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
//    @CollectionTable(
//            name = "roleList",
//            joinColumns = @JoinColumn(name = "account_id")
//    )
    private List<Role> roleList = new ArrayList<>();


//    public Account(String email, String password, List<Role> roleList, Boolean emailAuth, String authToken) {
//        this.email = email;
//        this.password = password;
//        this.roleList = roleList;
//        this.emailAuth = emailAuth;
//        this.authToken = authToken;
//    }

    public void emailVerifiedSuccess() {
        this.emailAuth = true;
    }
}

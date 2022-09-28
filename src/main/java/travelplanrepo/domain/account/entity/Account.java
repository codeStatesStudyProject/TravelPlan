package travelplanrepo.domain.account.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import travelplanrepo.domain.File.domain.File;
import travelplanrepo.global.common.auditing.BaseTime;

import javax.persistence.*;
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

    @Enumerated(EnumType.STRING)
    @ElementCollection
    private List<Role> roleList;

    public Account(String email, String password, List<Role> roleList) {
        this.email = email;
        this.password = password;
        this.roleList = roleList;
    }
}

package travelplanrepo.account.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import travelplanrepo.account.entity.Account;
import travelplanrepo.account.entity.Gender;
import travelplanrepo.utill.File.File;

@Data
public class AccountRes {
    private String email;
    private String nickName;
    private File img;
    private Gender gender;
    private Integer age;
    private String mobile;

    public AccountRes(Account account) {
        this.email = account.getEmail();
        this.nickName = account.getNickName();
        this.img = account.getImg();
        this.gender = account.getGender();
        this.age = account.getAge();
        this.mobile = account.getMobile();
    }
}

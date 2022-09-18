package travelplanrepo.account.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import travelplanrepo.account.entity.Account;
import travelplanrepo.account.entity.Gender;
import travelplanrepo.utill.File.File;

@Data
public class PostAccountDto {

    private String email;
    private String password;
    private String nickName;
    private MultipartFile img;
    private Gender gender;
    private Integer age;
    private String mobile;

    public Account toAccount() {
        Account account = new Account();
        account.setEmail(email);
        account.setPassword(password);
        account.setNickName(nickName);
        account.setGender(gender);
        account.setAge(age);
        account.setMobile(mobile);

        return account;
    }
}

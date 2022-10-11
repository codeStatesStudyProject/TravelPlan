package travelplanrepo.domain.account.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import travelplanrepo.domain.account.entity.Account;
import travelplanrepo.domain.account.entity.Gender;

@Data
public class PatchAccountDto {

    private Long id;
    private String password;
    private String nickName;
    private MultipartFile img;
    private Gender gender;
    private Integer age;
    private String mobile;

    public Account toAccount() {
        Account account = new Account();
        account.setId(id);
        account.setPassword(password);
        account.setNickName(nickName);
        account.setGender(gender);
        account.setAge(age);
        account.setMobile(mobile);

        return account;
    }
}

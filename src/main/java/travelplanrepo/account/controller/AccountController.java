package travelplanrepo.account.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import travelplanrepo.account.dto.PostAccountDto;
import travelplanrepo.account.entity.Account;
import travelplanrepo.account.service.AccountService;
import travelplanrepo.security.argumentresolver.LoginAccountId;
import travelplanrepo.utill.File.File;
import travelplanrepo.utill.File.FileProcessor;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class AccountController {

    @Value("${file.profileImg}")
    private String profileImgPath;
    private final AccountService accountService;
    private final FileProcessor fileProcessor;

    @PostMapping("/account")
    public String signUp(@ModelAttribute PostAccountDto postAccountDto) throws IOException {
        Account account = postAccountDto.toAccount();
        File profileImg = fileProcessor.storeFile(postAccountDto.getImg(), profileImgPath);
        account.setImg(profileImg);
        accountService.signUp(account);

        return "success account created";
    }
}

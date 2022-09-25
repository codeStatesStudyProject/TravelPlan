package travelplanrepo.account.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import travelplanrepo.account.dto.AccountRes;
import travelplanrepo.account.dto.PostAccountDto;
import travelplanrepo.account.entity.Account;
import travelplanrepo.account.service.AccountService;
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

    @GetMapping("/account/profile/{accountId}")
    public ResponseEntity<AccountRes> getAccount(@PathVariable long accountId) {
        Account account = accountService.getAccount(accountId);
        AccountRes accountRes = new AccountRes(account);

        return new ResponseEntity<>(accountRes, HttpStatus.OK);
    }
}

package travelplanrepo.domain.account.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import travelplanrepo.domain.account.dto.AccountRes;
import travelplanrepo.domain.account.dto.PostAccountDto;
import travelplanrepo.domain.account.entity.Account;
import travelplanrepo.domain.account.service.AccountService;
import travelplanrepo.domain.File.File;
import travelplanrepo.domain.File.FileProcessor;

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

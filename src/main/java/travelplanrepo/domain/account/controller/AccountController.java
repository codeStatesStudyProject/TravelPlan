package travelplanrepo.domain.account.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import travelplanrepo.domain.account.dto.AccountRes;
import travelplanrepo.domain.account.dto.PatchAccountDto;
import travelplanrepo.domain.account.dto.PostAccountDto;
import travelplanrepo.domain.account.entity.Account;
import travelplanrepo.domain.account.service.AccountService;
import travelplanrepo.domain.File.domain.File;
import travelplanrepo.domain.File.service.FileService;
import travelplanrepo.global.security.argumentresolver.LoginAccountId;
import travelplanrepo.global.security.authentication.Principal;
import travelplanrepo.global.security.authentication.UserAccount;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class AccountController {

    @Value("${file.profileImg}")
    private String profileImgPath;
    private final AccountService accountService;
    private final FileService fileService;

    @PostMapping("/account")
    public String signUp(@ModelAttribute PostAccountDto postAccountDto) throws IOException {
        Account account = postAccountDto.toAccount();
        File profileImg = fileService.storeFile(postAccountDto.getImg(), profileImgPath);
        account.setImg(profileImg);
        accountService.signUp(account);

        return "success account created";
    }
    @PatchMapping("/account/profile")
    public ResponseEntity<AccountRes> patchAccount(@LoginAccountId Long accountId,
                                                   @ModelAttribute PatchAccountDto patchAccountDto) throws IOException {
        patchAccountDto.setId(accountId);

        Account account = accountService.updateAccount(patchAccountDto.toAccount());
        File profileImg = fileService.storeFile(patchAccountDto.getImg(), profileImgPath);
        account.setImg(profileImg);
        AccountRes accountRes = new AccountRes(account);

        return new ResponseEntity<>(accountRes, HttpStatus.OK);
    }

    @GetMapping("/account/profile/{accountId}")
    public ResponseEntity<AccountRes> getAccount(@PathVariable long accountId) {
        Account account = accountService.getAccount(accountId);
        AccountRes accountRes = new AccountRes(account);

        return new ResponseEntity<>(accountRes, HttpStatus.OK);
    }

    @GetMapping("/account/MyPage")
    public ResponseEntity<AccountRes> getMyPage(@LoginAccountId long accountId) {
        Account account = accountService.getAccount(accountId);
        AccountRes accountRes = new AccountRes(account);

        return new ResponseEntity<>(accountRes, HttpStatus.OK);
    }

    @DeleteMapping("/account/profile")
    public String deleteAccount(@LoginAccountId long accountId) {
        accountService.deleteAccount(accountId);

        return "success account deleted";
    }
}

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
import travelplanrepo.domain.email.EmailService;
import travelplanrepo.global.security.argumentresolver.LoginAccountId;
import travelplanrepo.global.security.authentication.Principal;
import travelplanrepo.global.security.authentication.UserAccount;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class AccountController {

    @Value("${file.profileImg}")
    private String profileImgPath;
    private final AccountService accountService;
    private final EmailService emailService;
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

        File profileImg = fileService.storeFile(patchAccountDto.getImg(), profileImgPath);
        Account accountImg = patchAccountDto.toAccount();
        accountImg.setImg(profileImg);

        Account account = accountService.updateAccount(accountImg);
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

    @PostMapping("/account/checkout-email")
    public String viewConfirmEmail(@Valid @RequestParam String email,
                                   @RequestParam String token) {
        // 구현 필요
        emailService.confirmEmail(email, token);

        return "email verification successful";
    }

    @DeleteMapping("/account/profile/{accountId}")
    public String deleteAccount(@PathVariable long accountId) {
        accountService.deleteAccount(accountId);

        return "success account deleted";
    }
}
































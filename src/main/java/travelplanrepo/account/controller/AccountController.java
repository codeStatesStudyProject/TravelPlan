package travelplanrepo.account.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import travelplanrepo.account.entity.Account;
import travelplanrepo.account.service.AccountService;
import travelplanrepo.security.argumentresolver.LoginAccountId;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/account")
    public Account signUp(@RequestBody Account account) {
        return accountService.signUp(account);
    }

    @GetMapping("/account/test")
    public String accountTest(@LoginAccountId Long loginId) {
        return String.valueOf(loginId);
    }
}

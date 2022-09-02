package travelplanrepo.account.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import travelplanrepo.account.entity.Account;
import travelplanrepo.account.entity.Role;
import travelplanrepo.account.repository.AccountRepository;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Account signUp(Account account) {

        String encodePassword = bCryptPasswordEncoder.encode(account.getPassword());
        Account madeAccount = new Account(account.getEmail(), encodePassword, Role.User);
        return accountRepository.save(madeAccount);
    }
}

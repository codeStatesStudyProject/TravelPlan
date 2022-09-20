package travelplanrepo.domain.account.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.NoSuchMessageException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import travelplanrepo.domain.account.entity.Account;
import travelplanrepo.domain.account.entity.Role;
import travelplanrepo.domain.account.repository.AccountRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Account signUp(Account account) {

        String encodePassword = bCryptPasswordEncoder.encode(account.getPassword());
        account.setPassword(encodePassword);
        account.setRole(Role.USER);
        return accountRepository.save(account);
    }

    public Account getAccount(long accountId) {
        return findVerifiedAccount(accountId);
    }

    public Account findVerifiedAccount(long accountId) {
        Optional<Account> optionalMember =
                accountRepository.findById(accountId);
        Account findAccount =
                optionalMember.orElseThrow(() ->
                        new NoSuchMessageException("회원을 찾을 수 없습니다."));
        return findAccount;
    }
}

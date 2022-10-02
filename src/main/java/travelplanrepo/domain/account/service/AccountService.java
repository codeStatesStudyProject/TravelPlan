package travelplanrepo.domain.account.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.NoSuchMessageException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import travelplanrepo.domain.account.entity.Account;
import travelplanrepo.domain.account.entity.Role;
import travelplanrepo.domain.account.repository.AccountRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountService {

    private final AccountRepository accountRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public Account signUp(Account account) {

        String encodePassword = bCryptPasswordEncoder.encode(account.getPassword());
        account.setPassword(encodePassword);
        account.setRoleList(List.of(Role.USER));
        return accountRepository.save(account);
    }

    public Account getAccount(long accountId) {
        return findVerifiedAccount(accountId);
    }

    public void deleteAccount(long accountId) {
        Account account = findVerifiedAccount(accountId);
        accountRepository.delete(account);
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

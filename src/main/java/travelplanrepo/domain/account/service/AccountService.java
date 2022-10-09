package travelplanrepo.domain.account.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.NoSuchMessageException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
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

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public Account updateAccount(Account account) {
        // DTO로 전달받은 요청으로 DB에서 Account 조회
        Account findAccount = findVerifiedAccount(account.getId());

        // Account 에 담긴 요소 확인 -> 수정사항이 존재하면 Setter 로 값 저장.
        Optional.ofNullable(account.getPassword())
                .ifPresent(password -> findAccount.setPassword(bCryptPasswordEncoder.encode(account.getPassword())));
        Optional.ofNullable(account.getNickName())
                .ifPresent(name -> findAccount.setNickName(name));
        Optional.ofNullable(account.getImg())
                .ifPresent(img -> findAccount.setImg(img));
        Optional.ofNullable(account.getGender())
                .ifPresent(gender -> findAccount.setGender(gender));
        Optional.ofNullable(account.getAge())
                .ifPresent(age -> findAccount.setAge(age));
        Optional.ofNullable(account.getMobile())
                .ifPresent(mobile -> findAccount.setMobile(mobile));

        return findAccount;
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

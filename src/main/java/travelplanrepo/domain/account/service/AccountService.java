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
import travelplanrepo.domain.email.EmailAuth;
import travelplanrepo.domain.email.EmailService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountService {

    private final AccountRepository accountRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final EmailService emailService;
    @Transactional
    public void signUp(Account account) {

        validateDuplicated(account.getEmail());

        account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
        account.setEmailAuth(false);
        account.setAuthToken(UUID.randomUUID().toString());
        account.setRoleList(List.of(Role.USER));

        accountRepository.save(account);

        emailService.sendEmail(account.getEmail(), account.getAuthToken());
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

    public void validateDuplicated(String email) {
        if (accountRepository.findByEmail(email).isPresent())
            throw new NoSuchMessageException("중복된 이메일입니다.");
    }


}

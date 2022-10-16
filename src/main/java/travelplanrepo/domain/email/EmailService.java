package travelplanrepo.domain.email;

import lombok.RequiredArgsConstructor;
import org.springframework.context.NoSuchMessageException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import travelplanrepo.domain.account.entity.Account;
import travelplanrepo.domain.account.repository.AccountRepository;

import java.util.Optional;

@Service
@EnableAsync
@RequiredArgsConstructor
// JavaMailSender 객체를 사용하여 Async 방식으로 이메일을 보낸다.
public class EmailService {
    private final JavaMailSender javaMailSender;
    private final AccountRepository accountRepository;

    @Async
    public void sendEmail(String email, String authToken) {
        SimpleMailMessage smm = new SimpleMailMessage();
        smm.setTo(email); // 메일 수신자
        smm.setSubject("회원가입 이메일 인증"); // 메일 제목
        smm.setText("http://localhost:8080/account/checkout-email?email="+email+"&authToken="+authToken); // 메일 내용

        javaMailSender.send(smm);
    }
    @Transactional
    public void confirmEmail(String email, String token) {
        Optional<Account> optionalAccount = accountRepository.findByEmail(email);
        Account account = optionalAccount.orElseThrow(() ->
                        new NoSuchMessageException("인증이 실패했습니다"));

        if (account.getAuthToken().equals(token)) {
            account.emailVerifiedSuccess(); // 이메일 인증 완료
        }
        else {
            throw new NoSuchMessageException("인증이 실패했습니다.");
        }

    }
}

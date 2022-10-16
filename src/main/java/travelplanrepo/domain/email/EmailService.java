package travelplanrepo.domain.email;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

@Service
@EnableAsync
@RequiredArgsConstructor
// JavaMailSender 객체를 사용하여 Async 방식으로 이메일을 보낸다.
public class EmailService {
    private final JavaMailSender javaMailSender;

    @Async
    public void sendEmail(String email, String authToken) {
        SimpleMailMessage smm = new SimpleMailMessage();
        smm.setTo(email); // 메일 수신자
        smm.setSubject("회원가입 이메일 인증"); // 메일 제목
        smm.setText("http://localhost:8080/account/checkout-email?email="+email+"&authToken="+authToken); // 메일 내용

        javaMailSender.send(smm);
    }
}

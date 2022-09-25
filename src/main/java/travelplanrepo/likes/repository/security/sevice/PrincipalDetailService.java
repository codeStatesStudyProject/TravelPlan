package travelplanrepo.likes.repository.security.sevice;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import travelplanrepo.account.entity.Account;
import travelplanrepo.account.repository.AccountRepository;
import travelplanrepo.likes.repository.security.authentication.UserAccount;

@Service
@RequiredArgsConstructor
public class PrincipalDetailService implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("해당 Email을 갖는 Account를 찾을 수 없습니다."));
        return new UserAccount(account);
    }
}

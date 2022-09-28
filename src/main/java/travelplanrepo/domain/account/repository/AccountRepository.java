package travelplanrepo.domain.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import travelplanrepo.domain.account.entity.Account;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByEmail(String email);

    @Query("select account from Account account " +
            "join fetch account.roleList where account.email = :email")
    Optional<Account> findByEmailWithRole(@Param("email") String email);
}

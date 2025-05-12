package dev.imran.user_authentication.Repository;

import dev.imran.user_authentication.Models.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

    Optional<Token> findByValueAndIsDeleted(String value, boolean isDeleted);

    Optional<Token> findByValueAndIsDeletedAndExpiryDateGreaterThan(String value, boolean isDeleted, Date date);


}

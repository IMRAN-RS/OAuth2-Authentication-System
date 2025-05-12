package dev.imran.user_authentication.security.Repository;

import java.util.Optional;

import dev.imran.user_authentication.security.Models.Client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {
    Optional<Client> findByClientId(String clientId);
}

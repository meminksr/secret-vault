package com.secretvault.vault.repository;

import com.secretvault.vault.entity.SecretMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface SecretMessageRepository extends JpaRepository<SecretMessage, String> {
    void deleteByExpiresAtBefore(LocalDateTime now);
}

package com.secretvault.vault.repository;

import com.secretvault.vault.entity.SecretMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecretMessageRepository extends JpaRepository<SecretMessage, String> {
}

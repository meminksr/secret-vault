package com.secretvault.vault.service;

import com.secretvault.vault.repository.SecretMessageRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

@Service
public class CleanupService {

    private final SecretMessageRepository repository;

    public CleanupService(SecretMessageRepository repository) {
        this.repository = repository;
    }

    // Her 60 saniyede bir (60000 ms) çalışır
    @Scheduled(fixedRate = 60000)
    @Transactional
    public void cleanupExpiredMessages() {
        LocalDateTime now = LocalDateTime.now();
        repository.deleteByExpiresAtBefore(now);
        System.out.println("Cleanup completed: Expired messages have been deleted.");
    }
}
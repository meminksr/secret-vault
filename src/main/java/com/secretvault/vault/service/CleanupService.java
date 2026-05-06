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
    public void deleteExpiredMessages() {
        System.out.println("The trash-collecting robot is at work: It's scanning expired messages...");

        // Veritabanındaki tüm mesajları çekip kontrol edebiliriz
        // veya Repository'e özel bir silme metodu yazabiliriz.
        // Şimdilik en basit mantık:
        repository.findAll().forEach(msg -> {
            if (msg.getExpiresAt() != null && msg.getExpiresAt().isBefore(LocalDateTime.now())) {
                repository.delete(msg);
                System.out.println("Destroyed: " + msg.getId());
            }
        });
    }
}
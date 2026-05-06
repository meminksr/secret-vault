package com.secretvault.vault.service;

import com.secretvault.vault.entity.SecretMessage;
import com.secretvault.vault.repository.SecretMessageRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class SecretMessageService {

    private final SecretMessageRepository repository;

    public SecretMessageService(SecretMessageRepository repository) {
        this.repository = repository;
    }

    public SecretMessage saveMessage(SecretMessage message) {
        message.setCreatedAt(LocalDateTime.now());
        // Mesajın ömrünü şu andan itibaren 30 dakika sonrasına ayarla
        message.setExpiresAt(LocalDateTime.now().plusMinutes(1));
        return repository.save(message);
    }

    public Optional<SecretMessage> getMessageById(String id) {
        return repository.findById(id);
    }
}
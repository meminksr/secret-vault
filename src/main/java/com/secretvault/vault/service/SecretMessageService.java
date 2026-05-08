package com.secretvault.vault.service;

import com.secretvault.vault.entity.SecretMessage;
import com.secretvault.vault.repository.SecretMessageRepository;
import com.secretvault.vault.util.AesUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class SecretMessageService {

    private final SecretMessageRepository repository;

    public SecretMessageService(SecretMessageRepository repository) {
        this.repository = repository;
    }

    public SecretMessage saveMessage(SecretMessage message) {
        // Mesajı veritabanına gitmeden önce şifreliyoruz
        String encrypted = AesUtil.encrypt(message.getEncryptedContent());
        message.setEncryptedContent(encrypted);

        message.setCreatedAt(LocalDateTime.now());
        message.setExpiresAt(LocalDateTime.now().plusMinutes(2)); // Mesaj 2 dakika sonra silinecek
        return repository.save(message);
    }

    public Optional<SecretMessage> getMessage(UUID id) {
        Optional<SecretMessage> message = repository.findById(String.valueOf(id));

        // Mesajı bulursak, kullanıcıya göstermeden önce şifresini çözüyoruz
        message.ifPresent(m -> {
            String decrypted = AesUtil.decrypt(m.getEncryptedContent());
            m.setEncryptedContent(decrypted);
        });

        return message;
    }
}
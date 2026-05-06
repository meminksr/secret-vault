package com.secretvault.vault.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "secret_messages") // Veritabanındaki tablonun adı bu olacak
public class SecretMessage {

    @Id // Bu sütunun Primary Key (Birincil Anahtar) olduğunu belirtir
    private String id = UUID.randomUUID().toString(); // Mesajlara 1,2,3 yerine karmaşık bir ID vereceğiz

    @Column(nullable = false, length = 5000) // Boş olamaz ve uzun bir metin alabilir
    private String encryptedContent; // Şifrelenmiş anlamsız metnimiz buraya gelecek

    private LocalDateTime createdAt = LocalDateTime.now(); // Oluşturulma tarihi

    private LocalDateTime expiresAt; // Silinme tarihi (TTL için kullanacağız)

    // --- GETTER VE SETTER METOTLARI ---

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getEncryptedContent() { return encryptedContent; }
    public void setEncryptedContent(String encryptedContent) { this.encryptedContent = encryptedContent; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getExpiresAt() { return expiresAt; }
    public void setExpiresAt(LocalDateTime expiresAt) { this.expiresAt = expiresAt; }
}
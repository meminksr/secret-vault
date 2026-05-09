package com.secretvault.vault.controller;
import com.secretvault.vault.entity.SecretMessage;
import com.secretvault.vault.service.SecretMessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/messages")
@CrossOrigin(origins = "*")
public class SecretMessageController {

    private final SecretMessageService service;

    public SecretMessageController(SecretMessageService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<SecretMessage> createMessage(@Valid @RequestBody SecretMessage message) {
        SecretMessage savedMessage = service.saveMessage(message);
        return ResponseEntity.ok(savedMessage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SecretMessage> readMessage(@PathVariable String id) {
        return service.getMessage(UUID.fromString(id))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
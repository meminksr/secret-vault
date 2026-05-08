# Secret Vault  🔒

Bu proje, Spring Boot ve SQL Server kullanılarak geliştirilmiş, askeri düzeyde güvenlik (AES-128) ve otonom veri imha sistemi barındıran bir gizli mesajlaşma uygulamasıdır. Mesajlar veritabanına şifrelenmiş olarak kaydedilir ve belirlenen süre sonunda sistem tarafından otomatik olarak kalıcı olarak silinir.

## 🚀 Öne Çıkan Özellikler

* **Katmanlı Mimari (Layered Architecture):** Controller, Service ve Repository katmanları ile modüler ve sürdürülebilir kod yapısı.
* **AES-128 Şifreleme:** Veriler (at-rest) veritabanında şifreli olarak saklanır; böylece veritabanına sızılsa dahi mesaj içeriği okunamaz.
* **Otonom Temizlik (Self-Destruct):** `@Scheduled` görevleri ile süresi dolan (1 dakika) mesajlar veritabanından otomatik olarak silinir.
* **Modern Teknoloji Yığını:** Spring Boot 3+, Spring Data JPA ve SQL Server entegrasyonu.

## 🛠️ Kullanılan Teknolojiler

* **Dil:** Java 17+
* **Framework:** Spring Boot 3.x
* **Veritabanı:** Microsoft SQL Server
* **Güvenlik:** AES (Advanced Encryption Standard) - javax.crypto
* **Yapı Aracı:** Maven
* **ORM:** Hibernate / Spring Data JPA

## 📂 Proje Yapısı

```text
src/main/java/com/secretvault/vault/
├── controller/     # API Uç noktaları (Request/Response yönetimi)
├── service/        # İş mantığı ve Şifreleme kontrolü
├── repository/     # Veritabanı erişim katmanı
├── model/          # Veritabanı tabloları (Entities)
└── util/           # Şifreleme yardımcı sınıfı (AesUtil)
```

## ⚙️ Kurulum

1.  Projeyi klonlayın: `git clone https://github.com/kullaniciadi/secret-vault.git`
2.  `src/main/resources/application.properties` dosyasındaki veritabanı bağlantı bilgilerini güncelleyin:
    ```properties
    spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=SecretVault;encrypt=true;trustServerCertificate=true
    spring.datasource.username=${DB_USERNAME}
    spring.datasource.password=${DB_PASSWORD}
    ```
3.  Maven bağımlılıklarını yükleyin: `mvn clean install`
4.  Uygulamayı çalıştırın: `mvn spring-boot:run`

## 📡 API Uç Noktaları

| Metot | Uç Nokta | Açıklama |
| :--- | :--- | :--- |
| `POST` | `/api/messages` | Yeni bir gizli mesaj oluşturur (Şifrelenerek kaydedilir). |
| `GET` | `/api/messages/{id}` | Belirtilen ID'ye sahip mesajın şifresini çözer ve getirir. |

## 🚀 Canlı Uygulama
Uygulama şu an **Render** üzerinde aktif olarak çalışmaktadır.
- **Canlı Swagger UI:** https://secret-vault-api-nqfb.onrender.com/swagger-ui/index.html

## 📖 API Kullanımı

### 1. Mesaj Şifreleme ve Kaydetme
- **Endpoint:** `POST /api/messages`
- **İstek:**
```json
{
  "encryptedContent": "Gizli Mesaj İçeriği"
}

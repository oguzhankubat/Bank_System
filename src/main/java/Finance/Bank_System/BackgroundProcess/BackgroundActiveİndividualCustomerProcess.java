package Finance.Bank_System.BackgroundProcess;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Value; // YENİ EKLENDİ
import org.springframework.stereotype.Service;

import Finance.Bank_System.core.MessageService;
import lombok.RequiredArgsConstructor; // DEĞİŞİKLİK: AllArgs yerine Required

@Service
@RequiredArgsConstructor
public class BackgroundActiveİndividualCustomerProcess {
    
    // final ekledik ki RequiredArgsConstructor bunu constructora dahil etsin
    private final MessageService messageService;
    
    // --- YENİ EKLENEN KISIM ---
    // application.properties dosyasından okur (Local: localhost, Docker: fast-system)
    @Value("${service.fast.url}")
    private String fastServiceBaseUrl;
    // ---------------------------
    
    public String activateIndividualAccount(String iban, String token) {
        try {
            // --- URL DEĞİŞİKLİĞİ ---
            // Sabit localhost yerine properties'den gelen dinamik adres + endpoint yolu
            String fullUrl = fastServiceBaseUrl + "/api/civilAccount/enableAccount";
            URI uri = URI.create(fullUrl);
            // ------------------------

            String requestBody = String.format("{\"accountIBAN\": \"%s\", \"accountToken\": \"%s\"}", 
                    iban, 
                    token);
            
            HttpClient client = HttpClient.newHttpClient();
     
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(uri)
                    .method("PATCH", HttpRequest.BodyPublishers.ofString(requestBody))
                    .header("Content-Type", "application/json")
                    .build();
            
            HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            return response.body();

        } catch (Exception e) {
            e.printStackTrace();
            return  messageService.getMessage("external.service.fast.system.error") + e.getMessage() ;
        }
    }
}
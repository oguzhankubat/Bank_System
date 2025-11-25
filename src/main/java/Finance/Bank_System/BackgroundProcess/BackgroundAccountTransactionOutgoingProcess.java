package Finance.Bank_System.BackgroundProcess;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Value; // YENİ IMPORT
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import Finance.Bank_System.DTO_pojo_records.ExternalFastSystemTransactionResponse;
import Finance.Bank_System.DTO_pojo_records.WrapperAccountTransactionFromFastSystem;
import Finance.Bank_System.business.requests.CommonRequests.AccountTransactionToFastSystemRequest;
import Finance.Bank_System.core.MessageService;
import Finance.Bank_System.dataRepositories.Accounts.AccountRepository;
import Finance.Bank_System.entities.Account.CustomerEntityAccount;
import lombok.RequiredArgsConstructor; // AllArgs yerine bunu kullandık

@Service
@RequiredArgsConstructor // DEĞİŞİKLİK: @Value alanını constructor dışı tutmak için bunu kullandık.
public class BackgroundAccountTransactionOutgoingProcess {

    private final AccountRepository accountRepository;
    private final MessageService messageService;
    
    // ObjectMapper final ve initialize edildiği için Lombok bunu constructor'a katmaz, bu doğru.
    private final ObjectMapper objectMapper = new ObjectMapper();

    // --- YENİ EKLENEN KISIM ---
    // application.properties dosyasındaki "service.fast.url" değerini buraya çeker.
    // Localde: http://localhost:8088
    // Dockerda: http://fast-system:8088 gelir.
    @Value("${service.fast.url}")
    private String fastServiceBaseUrl;
    // ---------------------------

    public WrapperAccountTransactionFromFastSystem redirectTransaction(AccountTransactionToFastSystemRequest request) {

        try {
            CustomerEntityAccount account = accountRepository.findByAccountIban(request.getAccountIBAN())
                    .orElseThrow(() -> new RuntimeException(messageService.getMessage("iban.is.not.found") + request.getAccountIBAN()));

            BigDecimal currentBalance = account.getAccountBalance();
            BigDecimal transactionAmount = request.getTransactionAmount();

            if (currentBalance.compareTo(transactionAmount) < 0) {
                throw new RuntimeException(messageService.getMessage("account.balance.is.not.enough"));
            }

            // --- URL OLUŞTURMA DEĞİŞİKLİĞİ ---
            // Enum yerine properties'den gelen dinamik URL'i kullanıyoruz.
            // Endpoint yolunu (/api/transaction/process) buraya ekliyoruz.
            String fullUrl = fastServiceBaseUrl + "/api/transaction/process";
            URI uri = URI.create(fullUrl);
            // ----------------------------------

            String requestBody = String.format("""
                    {
                        "accountIBAN": "%s",
                        "accountToken": "%s",
                        "transactionAmount": "%s",
                        "transactionDescription": "%s",
                        "receiptBankAccountIBAN": "%s",
                        "transactionType": "%s"
                    }
                    """,
                    request.getAccountIBAN(),
                    account.getAccountToken(),
                    request.getTransactionAmount(),
                    request.getTransactionDescription(),
                    request.getReceiptBankAccountIBAN(),
                    request.getTransactionType()
            );


            HttpClient client = HttpClient.newHttpClient();

            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(uri)
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .header("Content-Type", "application/json")
                    .build();


            HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());


            if (response.statusCode() != 200) {
                throw new RuntimeException(messageService.getMessage("external.service.fast.system.error") + response.statusCode() + " - Response Body: " + response.body());
            }

            String responseBody = response.body();

            JsonNode jsonNode = objectMapper.readTree(responseBody);
            if (jsonNode.has("message")) {
                String errorMessage = jsonNode.get("message").asText();
                throw new RuntimeException(errorMessage);
            }

            ExternalFastSystemTransactionResponse transactionResponse = objectMapper.readValue(response.body(), ExternalFastSystemTransactionResponse.class);


            return new WrapperAccountTransactionFromFastSystem(account, transactionResponse);

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(messageService.getMessage("external.service.fast.system.error"), e);
        }
    }
}
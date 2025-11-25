package Finance.Bank_System.BackgroundProcess;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Value; // YENİ EKLENDİ
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import Finance.Bank_System.BankConstants.BankConstants;
import Finance.Bank_System.DTO_pojo_records.ExternalFastSystemResponse;
import Finance.Bank_System.DTO_pojo_records.WrapperİndividualCustomerAccount;
import Finance.Bank_System.business.requests.İndividualCustomer.CreateİndividualCustomerAccountRequest;
import Finance.Bank_System.core.MessageService;
import Finance.Bank_System.dataRepositories.CustomerTypes.IndividualCustomerRepository;
import Finance.Bank_System.entities.CustomerEntity.CustomerEntity;
import Finance.Bank_System.utilities.AccountNumberGenerator;
import lombok.RequiredArgsConstructor; // AllArgs yerine bu geldi

@Service
@RequiredArgsConstructor // DEĞİŞİKLİK: @Value ile çakışmaması için bunu kullanıyoruz
public class BackgroundCreateIndividualCustomerAccount {

    private final MessageService messageService;
    private final IndividualCustomerRepository individualCustomerRepository;

    // --- YENİ EKLENEN KISIM ---
    // application.properties dosyasından Fast Sisteminin adresini okur.
    // Local: http://localhost:8088
    // Docker: http://fast-system:8088
    @Value("${service.fast.url}")
    private String fastServiceBaseUrl;
    // ---------------------------
    
    public WrapperİndividualCustomerAccount createİndividualAccount(CreateİndividualCustomerAccountRequest createİndividualCustomerAccountRequest) {
        
        try {
            
            // --- URL DEĞİŞİKLİĞİ ---
            // Hard-coded localhost yerine dinamik url + endpoint yolu
            String fullUrl = fastServiceBaseUrl + "/api/civilAccount/createAccount";
            URI uri = URI.create(fullUrl);
            // ------------------------

            String accountNumber = AccountNumberGenerator.generateAccountNumber();
            
            // Not: İleride String.format yerine ObjectMapper kullanmanı öneririm ama şimdilik çalışır.
            String requestBody = String.format("{\"tcKimlikNumber\":\"%s\", \"accountType\":\"%s\", \"accountCurrency\":\"%s\", \"branchCode\":\"%s\", \"accountNumber\":\"%s\", \"bankOwnershipToken\":\"%s\"}",
                    createİndividualCustomerAccountRequest.getTcKimlikNumber(),
                    createİndividualCustomerAccountRequest.getAccountType(),
                    createİndividualCustomerAccountRequest.getAccountCurrency(),
                    createİndividualCustomerAccountRequest.getBranchCode(),
                    accountNumber,
                    BankConstants.ACCOUNT_OWNERSHIP_BANK.getValue());

            HttpClient client = HttpClient.newHttpClient();

            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(uri)
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody)) 
                    .header("Content-Type", "application/json") 
                    .build();

            
            
            HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            
            
            if (response.statusCode() != 200) {
                throw new RuntimeException(messageService.getMessage("external.service.fast.system.error") + " Status Code: " + response.statusCode());
            }
            
            String responseBody = response.body();

            ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
            
            JsonNode jsonNode = objectMapper.readTree(responseBody);
            if (jsonNode.has("message")) {
                String errorMessage = jsonNode.get("message").asText();
                throw new RuntimeException(errorMessage);
            }

            ExternalFastSystemResponse externalFastSystemResponse = objectMapper.readValue(response.body(), ExternalFastSystemResponse.class);            
        
            CustomerEntity individualCustomer = individualCustomerRepository.findByCustomerTcKimlikNumber(createİndividualCustomerAccountRequest.getTcKimlikNumber());

            return new WrapperİndividualCustomerAccount(individualCustomer, externalFastSystemResponse,accountNumber);

        } catch (IOException | InterruptedException e) {

            throw new RuntimeException(messageService.getMessage("external.service.fast.system.error"), e);
        }
    
    }
}
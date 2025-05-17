package Finance.Bank_System.BackgroundProcess;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import Finance.Bank_System.BankConstants.BankConstants;
import Finance.Bank_System.DTO_pojo.ExternalFastSystemResponse;
import Finance.Bank_System.DTO_pojo.WrapperİndividualCustomerAccount;
import Finance.Bank_System.business.requests.İndividualCustomer.CreateİndividualCustomerAccountRequest;
import Finance.Bank_System.core.MessageService;
import Finance.Bank_System.dataRepositories.CustomerEntity.CustomerEntityRepository;
import Finance.Bank_System.entities.CustomerEntity.CustomerEntity;
import Finance.Bank_System.utilities.AccountNumberGenerator;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BackgroundCreateIndividualCustomerAccount {

    private final MessageService messageService;
    private final CustomerEntityRepository customerEntityRepository;
    public WrapperİndividualCustomerAccount createİndividualAccount(CreateİndividualCustomerAccountRequest createİndividualCustomerAccountRequest) {

        try {
           
            URI uri = URI.create("http://localhost:8088/api/civilAccount/createAccount");
            String accountNumber=AccountNumberGenerator.generateAccountNumber();
            
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
            
            String responseBody = response.body();
            
            System.out.println(responseBody);
            if (responseBody == null || responseBody.isBlank()) {
                throw new RuntimeException(messageService.getMessage("input.is.wrong"));
            }

           
            if (responseBody.contains("TC Kimlik Numarası Bulunamadı")) {
                throw new RuntimeException(messageService.getMessage("tc.kimlik.number.is.not.exist") + ": " + createİndividualCustomerAccountRequest.getTcKimlikNumber());
            }
            
            if (response.statusCode() != 200) {
                throw new RuntimeException(messageService.getMessage("external.service.fast.system.error") + " Status Code: " + response.statusCode());
            }

         
            ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
            ExternalFastSystemResponse externalFastSystemResponse = objectMapper.readValue(response.body(), ExternalFastSystemResponse.class);
            
            
        
            CustomerEntity individualCustomer = customerEntityRepository.findByCustomerEntity_TcKimlikNumber(createİndividualCustomerAccountRequest.getTcKimlikNumber());

            return new WrapperİndividualCustomerAccount(individualCustomer, externalFastSystemResponse,accountNumber);

        } catch (IOException | InterruptedException e) {

            throw new RuntimeException(messageService.getMessage("tc.kimlik.number.is.not.exist"), e);
        } catch (RuntimeException e) {
            
            throw e;
        }
        
    }
    
}

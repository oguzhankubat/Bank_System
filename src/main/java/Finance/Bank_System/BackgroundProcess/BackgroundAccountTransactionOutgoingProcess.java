package Finance.Bank_System.BackgroundProcess;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import Finance.Bank_System.BankConstants.BankConstants;
import Finance.Bank_System.DTO_pojo.ExternalFastSystemTransactionResponse;
import Finance.Bank_System.DTO_pojo.WrapperAccountTransactionFromFastSystem;
import Finance.Bank_System.business.requests.CommonRequests.AccountTransactionToFastSystemRequest;
import Finance.Bank_System.core.MessageService;
import Finance.Bank_System.dataRepositories.CustomerEntity.AccountRepository;
import Finance.Bank_System.entities.Account.CustomerEntityAccount;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BackgroundAccountTransactionOutgoingProcess {
	private final AccountRepository accountRepository;
	private final ObjectMapper objectMapper = new ObjectMapper();
	private final MessageService messageService;
	
	public WrapperAccountTransactionFromFastSystem redirectTransaction(AccountTransactionToFastSystemRequest request) {
	    
	    try {
	      
	    	CustomerEntityAccount account = accountRepository.findByAccountIban(request.getAccountIBAN())
	    		    .orElseThrow(() -> new RuntimeException(messageService.getMessage("iban.is.not.found") + request.getAccountIBAN()));
	        
	    	double currentBalance = account.getAccountBalance();
	    	double transactionAmount = request.getTransactionAmount();

	    	if (currentBalance - transactionAmount < 0) {
	    	    throw new RuntimeException(messageService.getMessage("account.balance.is.not.enough"));
	    	}
	    	
	        URI uri = URI.create(BankConstants.FAST_SYSTEM_API.getValue());

	        String requestBody = String.format(
	        	    "{\"accountIBAN\":\"%s\","
	        	    + "\"accountToken\":\"%s\","
	        	    + "\"transactionAmount\":\"%s\","
	        	    + "\"transactionDescription\":\"%s\","
	        	    + "\"receiptBankAccountIBAN\":\"%s\"}",
	        	    request.getAccountIBAN(),
	        	    account.getAccountToken(),
	        	    request.getTransactionAmount(),
	        	    request.getTransactionDescription(),
	        	    request.getReceiptBankAccountIBAN()
	        	);

	 
	        HttpClient client = HttpClient.newHttpClient();

	
	        HttpRequest httpRequest = HttpRequest.newBuilder()
	                .uri(uri)
	                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
	                .header("Content-Type", "application/json")
	                .build();

	  
	        HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
	        
	
	        if (response.statusCode() != 200) {
	            throw new RuntimeException("İstek Başarısız! Status Code: " + response.statusCode() + " - Response Body: " + response.body());
	        }
	        ExternalFastSystemTransactionResponse transactionResponse = objectMapper.readValue(response.body(), ExternalFastSystemTransactionResponse.class);
	        
	        
	        return new WrapperAccountTransactionFromFastSystem(account,transactionResponse);
	        
	    } catch (IOException | InterruptedException e) {
	        throw new RuntimeException("İstek gönderilemedi!", e);
	    }
	}

}

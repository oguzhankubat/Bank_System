package Finance.Bank_System.BackgroundProcess;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.stereotype.Service;

import Finance.Bank_System.core.MessageService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BackgroundActiveİndividualCustomerProcess {
	private MessageService messageService;
	
	
	public String activateIndividualAccount(String iban, String token) {
        try {
        		Thread.sleep(5000);
        		URI uri = URI.create("http://localhost:8088/api/civilAccount/enableAccount");

          
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


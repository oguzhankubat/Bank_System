package Finance.Bank_System.rules;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import Finance.Bank_System.BankConstants.BankConstants;
import Finance.Bank_System.DTO_pojo_records.ExternalAPICivilSystenCivilResponse;
import Finance.Bank_System.business.requests.İndividualCustomer.CreateİndividualCustomerRequest;
import Finance.Bank_System.core.MessageService;
import Finance.Bank_System.core.ModelMapperServices;
import Finance.Bank_System.dataRepositories.Customer.CustomerRepository;
import Finance.Bank_System.entities.Customer.Customer;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CheckTcKimlikNumberRule {

    private CustomerRepository customerRepository;
    private ModelMapperServices modelMapperServices;
    private MessageService messageService;
    private CheckBeforeCreateIndividualCustomer checkBeforeCreateIndividualCustomer;
    private CheckTcKimlikNumberRuleUpdateCustomerIfChanged checkTcKimlikNumberRuleUpdateCustomerIfChanged;
    
    public Customer fetchCustomerFromCivilSystem(CreateİndividualCustomerRequest request) {
    	
        Optional<Customer> existingCustomerOpt = customerRepository.findByTcKimlikNumber(request.getTcKimlikNumber());

        try {
            URI uri = UriComponentsBuilder
                    .newInstance()
                    .scheme("http")
                    .host("localhost")
                    .port(8086)
                    .path("/api/person/check")
                    .queryParam("tcKimlikNumber", request.getTcKimlikNumber())
                    .queryParam("corporationVkn", BankConstants.BANK_VKN.getValue())
                    .build()
                    .toUri();

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.newBuilder().uri(uri).GET().build();
            HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                throw new RuntimeException(messageService.getMessage("external.service.civil.registry.error") + response.statusCode());
            }
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            
	        String responseBody = response.body();

	    	
	        JsonNode jsonNode = objectMapper.readTree(responseBody);
	        if (jsonNode.has("message")) {
	            String errorMessage = jsonNode.get("message").asText();
	            throw new RuntimeException(errorMessage);
	        }

            ExternalAPICivilSystenCivilResponse civilCustomer =
                    objectMapper.readValue(response.body(), ExternalAPICivilSystenCivilResponse.class);
            
            if (existingCustomerOpt.isPresent()) {
                Customer existingCustomer = existingCustomerOpt.get();
                

                if (checkTcKimlikNumberRuleUpdateCustomerIfChanged.updateCustomerIfChanged(existingCustomer, civilCustomer)) {
                    customerRepository.save(existingCustomer);
                }
                checkBeforeCreateIndividualCustomer.check(existingCustomer, request);
                return existingCustomer;
                
            } else {
                checkBeforeCreateIndividualCustomer.check(civilCustomer, request);

                Customer newCustomer = modelMapperServices.forRequest().map(civilCustomer, Customer.class);
                newCustomer.setCreatedTime(LocalDateTime.now());
                newCustomer.setCustomerStatu("Active");

                customerRepository.save(newCustomer);
                return newCustomer;
            }

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(messageService.getMessage("external.service.civil.registry.error"), e);
        }
        
    }
}
    
    


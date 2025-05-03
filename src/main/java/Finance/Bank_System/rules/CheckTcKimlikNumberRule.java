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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import Finance.Bank_System.BankConstants.BankConstants;
import Finance.Bank_System.DTO_pojo.ExternalAPICivilSystenCivilResponse;
import Finance.Bank_System.business.requests.CreateİndividualCustomerRequest;
import Finance.Bank_System.core.ModelMapperServices;
import Finance.Bank_System.dataRepositories.Customer.CustomerRepository;
import Finance.Bank_System.entities.Customer.Customer;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CheckTcKimlikNumberRule {

    private CustomerRepository customerRepository;
    private ModelMapperServices modelMapperServices;

    public Customer fetchCustomerFromCivilSystem(CreateİndividualCustomerRequest createİndividualCustomerRequest) {


        Optional<Customer> existingCustomer = customerRepository.findByTcKimlikNumber(createİndividualCustomerRequest.getTcKimlikNumber());

        if (existingCustomer.isPresent()) {
    
            return existingCustomer.get();
        }

        try {

        	URI uri = UriComponentsBuilder
                    .newInstance()
                    .scheme("http")
                    .host("localhost")
                    .port(8086)
                    .path("/api/person/check")
                    .queryParam("tcKimlikNumber", createİndividualCustomerRequest.getTcKimlikNumber())
                    .queryParam("corporationVkn", BankConstants.BANK_VKN)  
                    .build()
                    .toUri();

            
            HttpClient client = HttpClient.newHttpClient();

           
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

  
            if (response.statusCode() == 200) {
            
            	
            	ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.registerModule(new JavaTimeModule());

                ExternalAPICivilSystenCivilResponse civilCustomer = objectMapper.readValue(response.body(), ExternalAPICivilSystenCivilResponse.class);
                
                Customer customer = modelMapperServices.forRequest()
                        .map(civilCustomer, Customer.class); 

                customer.setCreatedTime(LocalDateTime.now());
                customer.setCustomerStatu("Active");
        
                customerRepository.save(customer);

                return customer;

            } else {
                throw new RuntimeException("External service responded with error code: " + response.statusCode());
            }

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error while calling external service", e);
        }
    }
}

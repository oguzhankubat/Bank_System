package Finance.Bank_System.rules;

import org.springframework.stereotype.Component;

import Finance.Bank_System.DTO_pojo.ExternalAPICivilSystenCivilResponse;
import Finance.Bank_System.business.requests.İndividualCustomer.CreateİndividualCustomerRequest;
import Finance.Bank_System.core.MessageService;
import Finance.Bank_System.entities.Customer.Customer;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CheckBeforeCreateIndividualCustomer {

    private final MessageService messageService;

    public void check(Customer civilCustomer, CreateİndividualCustomerRequest createRequest) {
        if (civilCustomer == null || civilCustomer.getTcKimlikNumber() == null) {
            throw new RuntimeException(messageService.getMessage("tc.kimlik.number.is.not.exist"));
        }

        if (!civilCustomer.getBirthDate().equals(createRequest.getBirthDate())) {
            throw new RuntimeException(messageService.getMessage("input.is.wrong"));
        }
    }
    //şimdilik geçici override ettim. 
    
    public void check(ExternalAPICivilSystenCivilResponse civilResponse, CreateİndividualCustomerRequest createRequest) {
        if (civilResponse == null || civilResponse.getTcKimlikNumber() == null) {
            throw new RuntimeException(messageService.getMessage("tc.kimlik.number.is.not.exist"));
        }

        if (!civilResponse.getBirthDate().equals(createRequest.getBirthDate())) {
            throw new RuntimeException(messageService.getMessage("input.is.wrong"));
        }
    }
}
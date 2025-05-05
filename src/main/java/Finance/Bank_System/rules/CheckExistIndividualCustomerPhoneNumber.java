package Finance.Bank_System.rules;

import org.springframework.stereotype.Component;

import Finance.Bank_System.core.MessageService;
import Finance.Bank_System.dataRepositories.İndividualCustomer.İndividualCustomerRepository;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CheckExistIndividualCustomerPhoneNumber {
    private final İndividualCustomerRepository individualCustomerRepository;
    private final MessageService messageService;

    public void exists(String phoneNumber) {
    	
    	boolean exists = individualCustomerRepository.existsByIndividualPhoneNumber(phoneNumber);

        if (exists) {
            throw new RuntimeException(messageService.getMessage("phone.number.is.found"));
        }
    }
}


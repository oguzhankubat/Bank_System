package Finance.Bank_System.rules;

import org.springframework.stereotype.Component;

import Finance.Bank_System.core.MessageService;
import Finance.Bank_System.dataRepositories.CustomerTypes.CustomerEntityRepository;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CheckExistIndividualCustomerPhoneNumber {
    private final CustomerEntityRepository customerEntityRepository;;
    private final MessageService messageService;

    public void exists(String phoneNumber) {
    	
    	boolean exists = customerEntityRepository.existsByCustomerEntityPhoneNumber(phoneNumber);

        if (exists) {
            throw new RuntimeException(messageService.getMessage("phone.number.is.found"));
        }
    }
}


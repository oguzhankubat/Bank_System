package Finance.Bank_System.rules;

import org.springframework.stereotype.Component;

import Finance.Bank_System.core.MessageService;
import Finance.Bank_System.dataRepositories.İndividualCustomer.İndividualCustomerRepository;
import Finance.Bank_System.entities.Customer.Customer;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CheckExistIndividualCustomer {

    private final İndividualCustomerRepository individualCustomerRepository;
    private final MessageService messageService;

    public void exists(Customer customer) {
    	
    	boolean exists = individualCustomerRepository.existsByAssociatedİndividualCustomer(customer);

        if (exists) {
            throw new RuntimeException(messageService.getMessage("individual.customer.is.found"));
        }
    }
}
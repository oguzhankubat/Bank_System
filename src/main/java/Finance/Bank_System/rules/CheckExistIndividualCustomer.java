package Finance.Bank_System.rules;

import org.springframework.stereotype.Component;

import Finance.Bank_System.BankConstants.BankConstants;
import Finance.Bank_System.core.MessageService;
import Finance.Bank_System.dataRepositories.CustomerEntity.CustomerEntityRepository;
import Finance.Bank_System.entities.Customer.Customer;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CheckExistIndividualCustomer {

    private final CustomerEntityRepository customerEntityRepository;
    private final MessageService messageService;

    public void exists(Customer customer) {

        boolean exists = customerEntityRepository.existsByCustomerEntityAndCustomerType(customer, BankConstants.CUSTOMER_TYPE_INDIVIDUAL.getValue());

        if (exists) {
            throw new RuntimeException(messageService.getMessage("individual.customer.is.found"));
        }
    }
}
package Finance.Bank_System.rules;

import org.springframework.stereotype.Component;

import Finance.Bank_System.core.MessageService;
import Finance.Bank_System.dataRepositories.CustomerEntity.AccountRepository;
import Finance.Bank_System.entities.Account.CustomerEntityAccount;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CheckAccountTransactionIncomingAccountIBAN {
	
	private final MessageService messageService;
	private final AccountRepository accountRepository;

    public CustomerEntityAccount check(String requests) {
    		
    	CustomerEntityAccount Account = accountRepository.findByAccountIban(requests)
		        .orElseThrow(() -> new RuntimeException(messageService.getMessage("iban.is.not.found") + requests));
    	return Account;
    }

}

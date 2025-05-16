package Finance.Bank_System.rules;

import org.springframework.stereotype.Component;

import Finance.Bank_System.business.requests.CommonRequests.AccountTransactionToBankSystemRequests;
import Finance.Bank_System.core.MessageService;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CheckAccountTransactionAccountIBAN {
	
	private final MessageService messageService;

    public void check(AccountTransactionToBankSystemRequests requests) {
    	
    }

}

package Finance.Bank_System.api.AccountTransaction;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Finance.Bank_System.business.abstracts.Common.CustomerAccountTransactionService;
import Finance.Bank_System.business.requests.CommonRequests.AccountTransactionToBankSystemRequests;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/accountTransaction")
public class CustomerAccountTransactionController {
	
	private final CustomerAccountTransactionService customerAccountTransactionService;
	
	@PostMapping("/process")
	public String accountTransactionProcess(AccountTransactionToBankSystemRequests request) {
		return customerAccountTransactionService.customerAccountTransactionProcess(request);
	}

}

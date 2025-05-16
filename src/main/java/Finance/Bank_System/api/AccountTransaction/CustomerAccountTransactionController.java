package Finance.Bank_System.api.AccountTransaction;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Finance.Bank_System.business.abstracts.Common.AccountTransactionIncomingService;
import Finance.Bank_System.business.abstracts.Common.AccountTransactionOutgoingService;
import Finance.Bank_System.business.requests.CommonRequests.AccountTransactionToBankSystemRequests;
import Finance.Bank_System.business.requests.CommonRequests.AccountTransactionToFastSystemRequest;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/accountTransaction")
public class CustomerAccountTransactionController {
	
	private final AccountTransactionIncomingService accountTransactionIncomingService;
	private final AccountTransactionOutgoingService accountTransactionOutgoingService;
	
	@PostMapping("/incoming")
	public String accountTransactionIncomingProcess(@Valid @RequestBody AccountTransactionToBankSystemRequests request) {
		return accountTransactionIncomingService.accountTransactionIncomingProcess(request);
	}
	
	@PostMapping("/outgoing")
	public String accountTransactionOutgoingProcess(@Valid @RequestBody AccountTransactionToFastSystemRequest request) {
		return accountTransactionOutgoingService.accountTransactionOutgoingProcess(request);
	}

}

package Finance.Bank_System.api.İndividualCustomer;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Finance.Bank_System.business.abstracts.individualCustomer.İndividualCustomerAccountService;
import Finance.Bank_System.business.requests.İndividualCustomer.CreateİndividualCustomerAccountRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/individualCustomerAccount")
public class İndividualCustomerAccountController {
	private İndividualCustomerAccountService individualCustomerAccountService;
	
	@PostMapping
	public String createİndividualCustomerAccount(@Valid @RequestBody() CreateİndividualCustomerAccountRequest createİndividualCustomerAccountRequest) {
		return individualCustomerAccountService.createİndividualCustomerAccount(createİndividualCustomerAccountRequest);

	}
	
}

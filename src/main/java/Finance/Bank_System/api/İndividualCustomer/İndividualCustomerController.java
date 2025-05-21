package Finance.Bank_System.api.İndividualCustomer;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Finance.Bank_System.business.abstracts.individualCustomer.İndividualCustomerService;
import Finance.Bank_System.business.requests.İndividualCustomer.CreateİndividualCustomerRequest;
import Finance.Bank_System.business.responses.İndividualCustomer.AfterCreateİndividualCustomerResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/individualCustomer")
public class İndividualCustomerController {
	private final İndividualCustomerService individualCustomerService;
	
	@PostMapping("/createIndividualCustomer")
	public AfterCreateİndividualCustomerResponse afterCreateİndividualCustomer(@Valid @RequestBody() CreateİndividualCustomerRequest createİndividualCustomerRequest) {
		return individualCustomerService.createİndividualCustomer(createİndividualCustomerRequest);
	}
}

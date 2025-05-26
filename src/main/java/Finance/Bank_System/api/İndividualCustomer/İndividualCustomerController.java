package Finance.Bank_System.api.İndividualCustomer;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Finance.Bank_System.DTO_pojo_records.InternalGetAllCustomerEntity;
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
	
	/*
    @GetMapping("/getAllCustomerEntity")
    public List<InternalGetAllCustomerEntity> exportAllToJson() {
        return individualCustomerService.getAllCustomerEntity(); 
    }
	*/
}
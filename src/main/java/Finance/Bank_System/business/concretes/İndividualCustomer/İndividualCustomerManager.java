package Finance.Bank_System.business.concretes.İndividualCustomer;

import org.springframework.stereotype.Service;

import Finance.Bank_System.business.abstracts.individualCustomer.İndividualCustomerService;
import Finance.Bank_System.business.requests.CreateİndividualCustomerRequest;
import Finance.Bank_System.business.responses.AfterCreateİndividualCustomerResponse;
import Finance.Bank_System.entities.Customer.Customer;
import Finance.Bank_System.rules.CheckTcKimlikNumberRule;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class İndividualCustomerManager implements İndividualCustomerService{
	
	public final CheckTcKimlikNumberRule checkTcKimlikNumberRule;

	@Override
	public AfterCreateİndividualCustomerResponse createİndividualCustomer(
			CreateİndividualCustomerRequest createİndividualCustomerRequest) {
		Customer customer = checkTcKimlikNumberRule.fetchCustomerFromCivilSystem(createİndividualCustomerRequest);
		
		
		AfterCreateİndividualCustomerResponse message=new AfterCreateİndividualCustomerResponse("Customer is created succesfully");
		return message;
	}

}

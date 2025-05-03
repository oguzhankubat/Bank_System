package Finance.Bank_System.business.concretes.İndividualCustomer;

import org.springframework.stereotype.Service;

import Finance.Bank_System.business.abstracts.individualCustomer.İndividualCustomerService;
import Finance.Bank_System.business.requests.CreateİndividualCustomerRequest;
import Finance.Bank_System.business.responses.AfterCreateİndividualCustomerResponse;
import Finance.Bank_System.core.ModelMapperServices;
import Finance.Bank_System.entities.Customer.Customer;
import Finance.Bank_System.entities.İndividualCustomer.İndividualCustomer;
import Finance.Bank_System.rules.CheckTcKimlikNumberRule;
import Finance.Bank_System.utilities.İndividualCustomerNumberGenerator;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class İndividualCustomerManager implements İndividualCustomerService{
	
	public final CheckTcKimlikNumberRule checkTcKimlikNumberRule;
	public final ModelMapperServices modelMapperServices;
	
	@Override
	public AfterCreateİndividualCustomerResponse createİndividualCustomer(
			CreateİndividualCustomerRequest createİndividualCustomerRequest) {
		Customer customer = checkTcKimlikNumberRule.fetchCustomerFromCivilSystem(createİndividualCustomerRequest);

		İndividualCustomer individualCustomer = modelMapperServices.forRequest()
                .map(createİndividualCustomerRequest, İndividualCustomer.class);
		
		individualCustomer.setIndividualCustomerNumber(İndividualCustomerNumberGenerator.musteriNumarasiOlustur());
		
		AfterCreateİndividualCustomerResponse message=new AfterCreateİndividualCustomerResponse("Customer is created succesfully");
		return message;
	}

}

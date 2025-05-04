package Finance.Bank_System.business.concretes.İndividualCustomer;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import Finance.Bank_System.business.abstracts.individualCustomer.İndividualCustomerService;
import Finance.Bank_System.business.requests.CreateİndividualCustomerRequest;
import Finance.Bank_System.business.responses.AfterCreateİndividualCustomerResponse;
import Finance.Bank_System.core.ModelMapperServices;
import Finance.Bank_System.dataRepositories.İndividualCustomer.İndividualCustomerRepository;
import Finance.Bank_System.entities.Customer.Customer;
import Finance.Bank_System.entities.İndividualCustomer.İndividualCustomer;
import Finance.Bank_System.rules.CheckExistIndividualCustomer;
import Finance.Bank_System.rules.CheckExistIndividualCustomerPhoneNumber;
import Finance.Bank_System.rules.CheckTcKimlikNumberRule;
import Finance.Bank_System.utilities.HashGenerator;
import Finance.Bank_System.utilities.İndividualCustomerNumberGenerator;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class İndividualCustomerManager implements İndividualCustomerService{
	
	public final CheckTcKimlikNumberRule checkTcKimlikNumberRule;
	public final ModelMapperServices modelMapperServices;
	public final İndividualCustomerRepository individualCustomerRepository;
	public final CheckExistIndividualCustomer checkExistIndividualCustomer;
	public final CheckExistIndividualCustomerPhoneNumber checkExistIndividualCustomerPhoneNumber;
	public final İndividualCustomerNumberGenerator individualCustomerNumberGenerator;
	@Override
	public AfterCreateİndividualCustomerResponse createİndividualCustomer(
			CreateİndividualCustomerRequest createİndividualCustomerRequest) {
		
		Customer customer = checkTcKimlikNumberRule.fetchCustomerFromCivilSystem(createİndividualCustomerRequest);
		
		checkExistIndividualCustomer.exists(customer);
		checkExistIndividualCustomerPhoneNumber.exists(createİndividualCustomerRequest.getIndividualPhoneNumber());
		
		İndividualCustomer individualCustomer = modelMapperServices.forRequest()
                .map(createİndividualCustomerRequest, İndividualCustomer.class);
		
		individualCustomer.setIndividualCustomerNumber(individualCustomerNumberGenerator.musteriNumarasiOlustur());
		individualCustomer.setAssociatedİndividualCustomer(customer);
		individualCustomer.setCreatedTime(LocalDateTime.now());
		individualCustomer.setIndividualAccountStatu("Active");
		individualCustomer.setIndividualAccountPassword(HashGenerator.sha256Hash(createİndividualCustomerRequest.getIndividualAccountPassword()));
		
		
		individualCustomerRepository.save(individualCustomer);
		// araya başka parametreler eklenebilir. 
		
		AfterCreateİndividualCustomerResponse message=new AfterCreateİndividualCustomerResponse("Customer is created succesfully");
		return message;
	}

}

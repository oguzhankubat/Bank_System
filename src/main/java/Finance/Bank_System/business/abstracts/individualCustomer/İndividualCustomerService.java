package Finance.Bank_System.business.abstracts.individualCustomer;

import java.util.List;

import Finance.Bank_System.DTO_pojo_records.InternalGetAllCustomerEntity;
import Finance.Bank_System.business.requests.İndividualCustomer.CreateİndividualCustomerRequest;
import Finance.Bank_System.business.responses.İndividualCustomer.AfterCreateİndividualCustomerResponse;

public interface İndividualCustomerService {
	
	 AfterCreateİndividualCustomerResponse createİndividualCustomer(CreateİndividualCustomerRequest createİndividualCustomerRequest);
	 
	 List<InternalGetAllCustomerEntity> getAllCustomerEntity();
}

package Finance.Bank_System.dataRepositories.İndividualCustomer;

import org.springframework.data.jpa.repository.JpaRepository;

import Finance.Bank_System.entities.Customer.Customer;
import Finance.Bank_System.entities.İndividualCustomer.İndividualCustomer;

public interface İndividualCustomerRepository extends JpaRepository<İndividualCustomer, Long>{

	boolean existsByAssociatedİndividualCustomer(Customer customer);
	
	boolean existsByIndividualPhoneNumber(String phoneNumber);
	
	boolean existsByIndividualCustomerNumber(String individualCustomerNumber);
}

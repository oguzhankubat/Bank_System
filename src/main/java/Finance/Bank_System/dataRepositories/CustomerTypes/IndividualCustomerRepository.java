package Finance.Bank_System.dataRepositories.CustomerTypes;

import org.springframework.data.jpa.repository.JpaRepository;

import Finance.Bank_System.entities.Customer.IndividualCustomer.IndividualCustomer;

public interface IndividualCustomerRepository extends JpaRepository<IndividualCustomer, Long>{
	IndividualCustomer findByCustomerTcKimlikNumber(String tcKimlikNumber);

}

package Finance.Bank_System.dataRepositories.Customer;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import Finance.Bank_System.entities.Customer.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
	Optional<Customer> findByTcKimlikNumber(String tcKimlikNumber);
	
	boolean existsByTcKimlikNumberAndIndividualCustomerIsNotNull(String tcKimlikNumber);
	
}

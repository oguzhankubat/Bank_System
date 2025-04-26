package Finance.Bank_System.dataRepositories;

import org.springframework.data.jpa.repository.JpaRepository;

import Finance.Bank_System.entitiesAboutCustomer.Customer;

public interface PersonRepository extends JpaRepository<Customer,Long>{
	
}	

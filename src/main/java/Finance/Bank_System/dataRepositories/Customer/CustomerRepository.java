package Finance.Bank_System.dataRepositories.Customer;

import org.springframework.data.jpa.repository.JpaRepository;

import Finance.Bank_System.entities.Customer.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

}

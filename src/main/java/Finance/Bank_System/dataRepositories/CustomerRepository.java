package Finance.Bank_System.dataRepositories;

import org.springframework.data.jpa.repository.JpaRepository;

import Finance.Bank_System.entities.Customer.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

}

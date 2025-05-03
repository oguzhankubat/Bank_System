package Finance.Bank_System.dataRepositories.CorporateCustomer;

import org.springframework.data.jpa.repository.JpaRepository;

import Finance.Bank_System.entities.CorporateCustomer.CorporateCustomer;

public interface CorporateCustomerRepository extends JpaRepository<CorporateCustomer, Long>{

}

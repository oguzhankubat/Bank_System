package Finance.Bank_System.dataRepositories;

import org.springframework.data.jpa.repository.JpaRepository;

import Finance.Bank_System.entities.CorporateCustomer.CorporateCustomerAdress;

public interface CorporateCustomerAdressRepository extends JpaRepository<CorporateCustomerAdress, Long>{

}

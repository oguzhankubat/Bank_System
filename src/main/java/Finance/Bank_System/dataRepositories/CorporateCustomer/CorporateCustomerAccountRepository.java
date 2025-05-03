package Finance.Bank_System.dataRepositories.CorporateCustomer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CorporateCustomerAccountRepository extends JpaRepository<Finance.Bank_System.entities.CorporateCustomer.Account.CorporateCustomerAccount, Long>{

}

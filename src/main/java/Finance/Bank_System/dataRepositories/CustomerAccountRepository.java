package Finance.Bank_System.dataRepositories;

import org.springframework.data.jpa.repository.JpaRepository;

import Finance.Bank_System.entitiesAboutCustomer.CustomerAccount;

public interface CustomerAccountRepository extends JpaRepository<CustomerAccount, Long>{

}

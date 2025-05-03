package Finance.Bank_System.dataRepositories.CorporateCustomer;

import org.springframework.data.jpa.repository.JpaRepository;

import Finance.Bank_System.entities.CorporateCustomer.Account.CorporateAccountTransaction;

public interface CorporateCustomerAccountTransactionRepository extends JpaRepository<CorporateAccountTransaction, Long>{

}

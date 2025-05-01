package Finance.Bank_System.dataRepositories;

import org.springframework.data.jpa.repository.JpaRepository;

import Finance.Bank_System.entities.CorporateCustomer.Account.CorporateAccountTransaction;

public interface CorporateAccountTransactionRepository extends JpaRepository<CorporateAccountTransaction, Long>{

}

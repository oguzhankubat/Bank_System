package Finance.Bank_System.dataRepositories;

import org.springframework.data.jpa.repository.JpaRepository;

import Finance.Bank_System.entities.İndividualCustomer.Account.İndividualAccountTransaction;

public interface İndividualCustomerAccountTransactionRepository extends JpaRepository<İndividualAccountTransaction, Long>{

}

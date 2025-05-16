package Finance.Bank_System.dataRepositories.CustomerEntity;

import org.springframework.data.jpa.repository.JpaRepository;

import Finance.Bank_System.entities.Account.CustomerEntityAccountTransaction;

public interface CustomerEntityAccountTransactionRepository extends JpaRepository<CustomerEntityAccountTransaction, Long>{

}

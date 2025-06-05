package Finance.Bank_System.dataRepositories.Accounts;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import Finance.Bank_System.entities.Account.CustomerEntityAccount;

public interface AccountRepository extends JpaRepository<CustomerEntityAccount, Long>{
	
	
	Optional<CustomerEntityAccount> findByAccountIban(String accountIban);
	
	boolean existsByAccountToken(String accountToken);
}

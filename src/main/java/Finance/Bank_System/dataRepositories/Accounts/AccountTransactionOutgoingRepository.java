package Finance.Bank_System.dataRepositories.Accounts;

import org.springframework.data.jpa.repository.JpaRepository;

import Finance.Bank_System.entities.Account.AccountTransactionOutgoing;


public interface AccountTransactionOutgoingRepository extends JpaRepository<AccountTransactionOutgoing, Long>{

}

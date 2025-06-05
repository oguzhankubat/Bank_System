package Finance.Bank_System.business.concretes.Common;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import Finance.Bank_System.business.abstracts.Common.AccountTransactionIncomingService;
import Finance.Bank_System.business.requests.CommonRequests.AccountTransactionToBankSystemRequest;
import Finance.Bank_System.core.ModelMapperServices;
import Finance.Bank_System.dataRepositories.Accounts.AccountRepository;
import Finance.Bank_System.dataRepositories.Accounts.AccountTransactionIncomingRepository;
import Finance.Bank_System.entities.Account.AccountTransactionIncoming;
import Finance.Bank_System.entities.Account.CustomerEntityAccount;
import Finance.Bank_System.rules.CheckAccountTransactionIncomingAccountIBAN;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class AccountTransactionIncomingManager implements AccountTransactionIncomingService{
	
	private final ModelMapperServices modelMapperServices;
	private final AccountTransactionIncomingRepository accountTransactionIncomingRepository;
	private final CheckAccountTransactionIncomingAccountIBAN checkAccountTransactionIncomingAccountIBAN;
	private final AccountRepository accountRepository;
	
	@Transactional
	public String accountTransactionIncomingProcess(
	        AccountTransactionToBankSystemRequest accountTransactionToBankSystemRequest ) {

	    CustomerEntityAccount account = checkAccountTransactionIncomingAccountIBAN
	        .check(accountTransactionToBankSystemRequest);

	    AccountTransactionIncoming accountTransaction = modelMapperServices.forRequest()
	            .map(accountTransactionToBankSystemRequest, AccountTransactionIncoming.class);

	    accountTransaction.setCreatedTime(LocalDateTime.now());
	    accountTransaction.setCustomerEntityAccountTransactionIncoming(account);
	    

	    BigDecimal currentBalance = account.getAccountBalance();
	    BigDecimal transactionAmount = accountTransactionToBankSystemRequest.getTransactionAmount();


	    BigDecimal updatedBalance = currentBalance.add(transactionAmount);


	    account.setAccountBalance(updatedBalance);


	    accountRepository.save(account);
	    accountTransactionIncomingRepository.save(accountTransaction);
		
		return "success";
	}

}

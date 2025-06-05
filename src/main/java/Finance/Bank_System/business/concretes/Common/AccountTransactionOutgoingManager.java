package Finance.Bank_System.business.concretes.Common;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import Finance.Bank_System.BackgroundProcess.BackgroundAccountTransactionOutgoingProcess;
import Finance.Bank_System.DTO_pojo_records.ExternalFastSystemTransactionResponse;
import Finance.Bank_System.DTO_pojo_records.WrapperAccountTransactionFromFastSystem;
import Finance.Bank_System.business.abstracts.Common.AccountTransactionOutgoingService;
import Finance.Bank_System.business.requests.CommonRequests.AccountTransactionToFastSystemRequest;
import Finance.Bank_System.core.ModelMapperServices;
import Finance.Bank_System.dataRepositories.Accounts.AccountRepository;
import Finance.Bank_System.dataRepositories.Accounts.AccountTransactionOutgoingRepository;
import Finance.Bank_System.entities.Account.AccountTransactionOutgoing;
import Finance.Bank_System.entities.Account.CustomerEntityAccount;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class AccountTransactionOutgoingManager implements AccountTransactionOutgoingService{
	private final BackgroundAccountTransactionOutgoingProcess backgroundProcess;
	private final ModelMapperServices modelMapperServices;
	private final AccountTransactionOutgoingRepository accountTransactionOutgoingRepository;
	private final AccountRepository accountRepository;
	
	@Override
	@Transactional
	public String accountTransactionOutgoingProcess(
			AccountTransactionToFastSystemRequest accountTransactionToFastSystemRequest) {
		
		WrapperAccountTransactionFromFastSystem responseWrapper=backgroundProcess.redirectTransaction(accountTransactionToFastSystemRequest);
		CustomerEntityAccount account=responseWrapper.getAccount();
		ExternalFastSystemTransactionResponse transactionResponse=responseWrapper.getTransactionResponse();
		
		
		AccountTransactionOutgoing transaction=modelMapperServices.forRequest()
				.map(accountTransactionToFastSystemRequest, AccountTransactionOutgoing.class);
		
		transaction.setCreatedTime(LocalDateTime.now());
		transaction.setReceiptBankName(transactionResponse.getReceiptBankName());
		transaction.setReceiptPersonLastName(transactionResponse.getReceiptPersonLastName());
		transaction.setReceiptPersonName(transactionResponse.getReceiptPersonName());
		transaction.setTransactionNumber(transactionResponse.getTransactionNumber());
		transaction.setTransactionType(accountTransactionToFastSystemRequest.getTransactionType());
		
		transaction.setCustomerEntityAccountTransactionOutgoing(account);
		
		BigDecimal currentBalance = account.getAccountBalance();
		BigDecimal transactionAmount = accountTransactionToFastSystemRequest.getTransactionAmount();


		BigDecimal updatedBalance = currentBalance.subtract(transactionAmount);

		account.setAccountBalance(updatedBalance);
		
		accountTransactionOutgoingRepository.save(transaction);
		accountRepository.save(account);
		
		return "success";
	}

}

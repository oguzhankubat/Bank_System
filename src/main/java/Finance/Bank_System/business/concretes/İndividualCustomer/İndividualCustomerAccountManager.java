package Finance.Bank_System.business.concretes.İndividualCustomer;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import Finance.Bank_System.BackgroundProcess.BackgroundActiveİndividualCustomerProcess;
import Finance.Bank_System.BackgroundProcess.BackgroundCreateIndividualCustomerAccount;
import Finance.Bank_System.DTO_pojo_records.ExternalFastSystemResponse;
import Finance.Bank_System.DTO_pojo_records.WrapperİndividualCustomerAccount;
import Finance.Bank_System.business.abstracts.individualCustomer.İndividualCustomerAccountService;
import Finance.Bank_System.business.requests.İndividualCustomer.CreateİndividualCustomerAccountRequest;
import Finance.Bank_System.core.MessageService;
import Finance.Bank_System.core.ModelMapperServices;
import Finance.Bank_System.dataRepositories.Accounts.AccountRepository;
import Finance.Bank_System.entities.Account.CustomerEntityAccount;
import Finance.Bank_System.entities.CustomerEntity.CustomerEntity;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class İndividualCustomerAccountManager implements İndividualCustomerAccountService{
	private final BackgroundCreateIndividualCustomerAccount backgroundCreateIndividualCustomerAccount;
	private final AccountRepository AccountRepository;
	private final ModelMapperServices modelMapperServices;
	private final MessageService messageService;
	private final BackgroundActiveİndividualCustomerProcess backgroundActiveİndividualCustomerProcess;
	
	
	@Override
	@Transactional
	public String createİndividualCustomerAccount(
			CreateİndividualCustomerAccountRequest createİndividualCustomerAccountRequest) {
		
		WrapperİndividualCustomerAccount wrapperObject= backgroundCreateIndividualCustomerAccount.createİndividualAccount(createİndividualCustomerAccountRequest);
		CustomerEntity individualCustomerAccountRequestFromFrontEnd=wrapperObject.getCustomerEntityNumber();
		ExternalFastSystemResponse fastSystemResponse=wrapperObject.getExternalFastSystemResponse();
		
		CustomerEntityAccount account=modelMapperServices.forRequest()
				.map(createİndividualCustomerAccountRequest, CustomerEntityAccount.class);
		account.setAccountBalance(BigDecimal.ZERO);
		account.setAccountIban(fastSystemResponse.getAccountIBAN());
		account.setAccountToken(fastSystemResponse.getAccountToken());
		account.setAccountStatu("Active");
		account.setCreatedTime(LocalDateTime.now());
		account.setAccountNumber(wrapperObject.getAccountNumber() );
		account.setCustomerEntityAccount(individualCustomerAccountRequestFromFrontEnd);
		
		AccountRepository.save(account);
		
		backgroundActiveİndividualCustomerProcess.activateIndividualAccount(account.getAccountIban(), account.getAccountToken());
		
		return messageService.getMessage("individual.account.created");
	}

}

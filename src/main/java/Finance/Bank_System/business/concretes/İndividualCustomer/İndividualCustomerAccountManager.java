package Finance.Bank_System.business.concretes.İndividualCustomer;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import Finance.Bank_System.BackgroundProcess.BackgroundActiveİndividualCustomerProcess;
import Finance.Bank_System.BackgroundProcess.BackgroundCreateIndividualCustomerAccount;
import Finance.Bank_System.DTO_pojo.ExternalFastSystemResponse;
import Finance.Bank_System.DTO_pojo.WrapperİndividualCustomerAccount;
import Finance.Bank_System.business.abstracts.individualCustomer.İndividualCustomerAccountService;
import Finance.Bank_System.business.requests.İndividualCustomer.CreateİndividualCustomerAccountRequest;
import Finance.Bank_System.core.MessageService;
import Finance.Bank_System.core.ModelMapperServices;
import Finance.Bank_System.dataRepositories.İndividualCustomer.İndividualCustomerAccountRepository;
import Finance.Bank_System.entities.İndividualCustomer.İndividualCustomer;
import Finance.Bank_System.entities.İndividualCustomer.Account.İndividualCustomerAccount;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class İndividualCustomerAccountManager implements İndividualCustomerAccountService{
	private final BackgroundCreateIndividualCustomerAccount backgroundCreateIndividualCustomerAccount;
	private final İndividualCustomerAccountRepository individualCustomerAccountRepository;
	private final ModelMapperServices modelMapperServices;
	private final MessageService messageService;
	private final BackgroundActiveİndividualCustomerProcess backgroundActiveİndividualCustomerProcess;
	
	@Override
	@Transactional
	public String createİndividualCustomerAccount(
			CreateİndividualCustomerAccountRequest createİndividualCustomerAccountRequest) {
		
		WrapperİndividualCustomerAccount wrapperObject= backgroundCreateIndividualCustomerAccount.createİndividualAccount(createİndividualCustomerAccountRequest);
		İndividualCustomer individualCustomerAccountRequestFromFrontEnd=wrapperObject.getIndividualCustomerNumber();
		ExternalFastSystemResponse fastSystemResponse=wrapperObject.getExternalFastSystemResponse();
		
		İndividualCustomerAccount account=modelMapperServices.forRequest()
				.map(createİndividualCustomerAccountRequest, İndividualCustomerAccount.class);
		account.setAccountBalance(0);
		account.setAccountIban(fastSystemResponse.getAccountIBAN());
		account.setAccountToken(fastSystemResponse.getAccountToken());
		account.setAccountStatu("Active");
		account.setCreatedTime(LocalDateTime.now());
		account.setAccountNumber(wrapperObject.getAccountNumber() );
		account.setAccountİndividualCustomerNumber(individualCustomerAccountRequestFromFrontEnd);
		
		individualCustomerAccountRepository.save(account);
		
		backgroundActiveİndividualCustomerProcess.activateIndividualAccount(account.getAccountIban(), account.getAccountToken());
		
		return messageService.getMessage("individual.account.created");
	}

}

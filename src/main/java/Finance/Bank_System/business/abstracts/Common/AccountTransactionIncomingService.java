package Finance.Bank_System.business.abstracts.Common;

import Finance.Bank_System.business.requests.CommonRequests.AccountTransactionToBankSystemRequest;

public interface AccountTransactionIncomingService {
	
	String accountTransactionIncomingProcess(AccountTransactionToBankSystemRequest externalAccountTransactionToBankSystem);

}

package Finance.Bank_System.business.abstracts.Common;

import Finance.Bank_System.business.requests.CommonRequests.AccountTransactionToBankSystemRequests;

public interface CustomerAccountTransactionService {
	
	String customerAccountTransactionProcess(AccountTransactionToBankSystemRequests accountTransactionToBankSystemRequests);
	
	
}

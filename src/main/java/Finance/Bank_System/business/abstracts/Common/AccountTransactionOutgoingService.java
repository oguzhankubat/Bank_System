package Finance.Bank_System.business.abstracts.Common;

import Finance.Bank_System.business.requests.CommonRequests.AccountTransactionToFastSystemRequest;

public interface AccountTransactionOutgoingService {
	
	String accountTransactionOutgoingProcess(AccountTransactionToFastSystemRequest accountTransactionToFastSystemRequest);

}

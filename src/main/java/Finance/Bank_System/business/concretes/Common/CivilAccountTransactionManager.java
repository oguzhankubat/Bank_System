package Finance.Bank_System.business.concretes.Common;

import Finance.Bank_System.business.abstracts.Common.CivilAccountTransactionService;
import Finance.Bank_System.business.requests.CommonRequests.AccountTransactionToBankSystemRequests;
import Finance.Bank_System.core.ModelMapperServices;

public class CivilAccountTransactionManager implements CivilAccountTransactionService{
	
	private ModelMapperServices modelMapperServices;
	
	
	@Override
	public String civilAccountTransactionProcess(
			AccountTransactionToBankSystemRequests accountTransactionToBankSystemRequests) {
		
		return null;
	}

}

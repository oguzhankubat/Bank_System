package Finance.Bank_System.business.concretes.Common;

import org.springframework.stereotype.Service;

import Finance.Bank_System.business.abstracts.Common.CustomerAccountTransactionService;
import Finance.Bank_System.business.requests.CommonRequests.AccountTransactionToBankSystemRequests;
import Finance.Bank_System.core.ModelMapperServices;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CivilAccountTransactionManager implements CustomerAccountTransactionService{
	
	private ModelMapperServices modelMapperServices;

	@Override
	public String customerAccountTransactionProcess(
			AccountTransactionToBankSystemRequests accountTransactionToBankSystemRequests) {
		
		return null;
	}
	


}

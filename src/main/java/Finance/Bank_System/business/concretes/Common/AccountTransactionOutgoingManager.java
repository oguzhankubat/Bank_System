package Finance.Bank_System.business.concretes.Common;

import org.springframework.stereotype.Service;

import Finance.Bank_System.business.abstracts.Common.AccountTransactionOutgoingService;
import Finance.Bank_System.business.requests.CommonRequests.AccountTransactionToFastSystemRequest;
import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class AccountTransactionOutgoingManager implements AccountTransactionOutgoingService{

	@Override
	public String accountTransactionOutgoingProcess(
			AccountTransactionToFastSystemRequest accountTransactionToFastSystemRequest) {
		
		return null;
	}

}

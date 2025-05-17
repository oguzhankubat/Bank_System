package Finance.Bank_System.DTO_pojo;

import Finance.Bank_System.entities.Account.CustomerEntityAccount;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WrapperAccountTransactionFromFastSystem {
	private CustomerEntityAccount account;
	private ExternalFastSystemTransactionResponse transactionResponse;

}

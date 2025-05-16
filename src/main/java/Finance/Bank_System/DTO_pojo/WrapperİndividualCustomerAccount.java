package Finance.Bank_System.DTO_pojo;

import Finance.Bank_System.entities.CustomerEntity.CustomerEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WrapperİndividualCustomerAccount {
	private final  CustomerEntity customerEntityNumber;
	private final ExternalFastSystemResponse externalFastSystemResponse;
	private final String accountNumber;
}

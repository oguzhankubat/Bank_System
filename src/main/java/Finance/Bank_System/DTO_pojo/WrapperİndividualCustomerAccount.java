package Finance.Bank_System.DTO_pojo;

import Finance.Bank_System.entities.İndividualCustomer.İndividualCustomer;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WrapperİndividualCustomerAccount {
	private final  İndividualCustomer individualCustomerNumber;
	private final ExternalFastSystemResponse externalFastSystemResponse;
	private final String accountNumber;
}

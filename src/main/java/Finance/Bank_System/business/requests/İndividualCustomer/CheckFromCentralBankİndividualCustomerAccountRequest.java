package Finance.Bank_System.business.requests.İndividualCustomer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckFromCentralBankİndividualCustomerAccountRequest {
	private String accountIban;
}

package Finance.Bank_System.business.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CreateCorporateCustomerRequest {
	private String tcKimlikNumber;
	private String corporatePhoneNumber;
	private String vknNumber;
	private String corporateAccountPassword;
}

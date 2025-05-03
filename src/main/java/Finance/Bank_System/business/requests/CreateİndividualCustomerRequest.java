package Finance.Bank_System.business.requests;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateİndividualCustomerRequest{
	
	private String tcKimlikNumber;
	private String individualPhoneNumber;
	private LocalDate birthDate;
	private String individualAccountPassword;
}

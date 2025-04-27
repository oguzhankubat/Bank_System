package Finance.Bank_System.business.requests;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateİndividualCustomerRequest{
	
	private String tcKimlikNumber;
	private String individualPhoneNumber;
	private LocalDateTime birthDate;
	private String individualAccountPassword;
}

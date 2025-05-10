package Finance.Bank_System.business.requests.İndividualCustomer;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateİndividualCustomerRequest{
	
	@NotNull
	@NotBlank
	@Size(min=11,max = 11)
	private String tcKimlikNumber;
	
	@NotNull
	@NotBlank
	@Size(min=11,max = 13)
	private String individualPhoneNumber;
	
	@NotNull
	@NotBlank
	private LocalDate birthDate;
	
	@NotNull
	@NotBlank
	@Size(min=6,max = 6)
	private String individualAccountPassword;
}

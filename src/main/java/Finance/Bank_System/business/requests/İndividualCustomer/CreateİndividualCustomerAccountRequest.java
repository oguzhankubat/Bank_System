package Finance.Bank_System.business.requests.İndividualCustomer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateİndividualCustomerAccountRequest {
	
	@NotNull
	@NotBlank
	@Size(min=11,max = 11)
    private String tcKimlikNumber;
    
	@NotNull
	@NotBlank
	@Size(max = 15)
    @JsonDeserialize(using = Finance.Bank_System.core.UpperCaseDeserializer.class)
    private String accountType;
    
	@NotNull
	@NotBlank
	@Size(min=3,max = 3)
    @JsonDeserialize(using = Finance.Bank_System.core.UpperCaseDeserializer.class)
    private String accountCurrency;  
    
	@NotNull
	@NotBlank
	@Size(min=4,max = 4)
    private String branchCode;
}

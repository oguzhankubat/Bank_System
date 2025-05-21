package Finance.Bank_System.business.requests.İndividualCustomer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import Finance.Bank_System.BankConstants.AccountCurrency;
import Finance.Bank_System.BankConstants.AccountType;
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
    private AccountType accountType;
    
	@NotNull
    private AccountCurrency accountCurrency;  
    
	@NotNull
	@NotBlank
	@Size(min=4,max = 4)
    private String branchCode;
}

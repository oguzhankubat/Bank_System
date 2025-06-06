package Finance.Bank_System.business.requests.CommonRequests;

import java.math.BigDecimal;

import Finance.Bank_System.BankConstants.TransactionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountTransactionToBankSystemRequest {
	
    @NotNull
    @NotBlank
    @Size(min = 26, max = 26)
    private String senderAccountIBAN;

    @NotNull
    private BigDecimal transactionAmount;

    @NotNull
    @NotBlank
    private String transactionDescription;
    
    @NotNull
    @NotBlank
    private String transactionNumber;
    
    @NotNull
    private TransactionType transactionType;

    @NotNull
    @NotBlank
    @Size(min = 26, max = 26)
    private String receiptBankAccountIBAN;
    
    @NotNull
    @NotBlank
    @Size(min=36,max = 36)
    private String receiptBankAccountToken;

    @NotNull
    @NotBlank
    private String senderPersonName;

    @NotNull
    @NotBlank
    private String senderPersonLastName;
    
}

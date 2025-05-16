package Finance.Bank_System.business.requests.CommonRequests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountTransactionToFastSystemRequest {

    @NotBlank(message = "Account IBAN cannot be blank")
    @Size(min = 26,max = 26)
    private String accountIBAN;


    @NotNull(message = "Transaction Amount cannot be null")
    @Positive(message = "Transaction Amount must be positive")
    private Double transactionAmount;

    @Size(max = 150)
    private String transactionDescription;

    @NotBlank(message = "Receipt Bank Account IBAN cannot be blank")
    @NotNull
    private String receiptBankAccountIBAN;
}

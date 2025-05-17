package Finance.Bank_System.DTO_pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExternalFastSystemTransactionResponse {
	
    private String transactionNumber;
    private String receiptPersonName;
    private String receiptPersonLastName;
    private String receiptBankName;

}

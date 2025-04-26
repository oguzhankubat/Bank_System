package Finance.Bank_System.entitiesAboutAccountTransaction;

import Finance.Bank_System.entitiesAboutCustomer.CustomerAccount;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "account_transactions")
public class AccountTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    @Column(name = "transaction_type",length = 7)//para gönderme alma
    private String transactionType;
    
    @Column(name = "transaction_balance")
    private String transactionBalance;
    
    @Column(name = "transaction_description",length = 50)
    private String transactionDescription;
    
    @ManyToOne
    @JoinColumn(name = "transaction_account_number", referencedColumnName = "account_number")
    private CustomerAccount accountTransaction;
}

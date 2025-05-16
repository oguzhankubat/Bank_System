package Finance.Bank_System.entities.Account;


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
public class CustomerEntityAccountTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    @Column(name = "transaction_type",length = 7)
    private String transactionType;
    
    @Column(name = "transaction_balance")
    private String transactionBalance;
    
    @Column(name = "transaction_description",length = 150)
    private String transactionDescription;
    
    @ManyToOne
    @JoinColumn(name = "account_number_transaction", referencedColumnName = "account_number")
    private CustomerEntityAccount customerEntityAccountTransaction;
} 
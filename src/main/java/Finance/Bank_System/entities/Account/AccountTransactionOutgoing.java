package Finance.Bank_System.entities.Account;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import Finance.Bank_System.BankConstants.TransactionType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
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
@Table(
	    name = "account_transactions_outgoing",
	    indexes = {
	        @Index(name = "idx_transaction_outgoing_account_number", columnList = "transaction_outgoing_account_number")
	    }
	)
public class AccountTransactionOutgoing{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    @Column(name = "transaction_description",length = 150)
    private String transactionDescription;

    @Column(name = "transaction_amount", nullable = false)
    private BigDecimal transactionAmount;

    @Column(name = "transaction_number", length = 36, nullable = false, unique = true,updatable = false)
    private String transactionNumber;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type",length = 20,updatable = false)
    private TransactionType transactionType;
    
    @Column(name = "receipt_bank_account_ıban" ,length = 26,updatable = false)
    private String receiptBankAccountIBAN;
    
    @Column(name = "receipt_person_name",length = 40,updatable = false)
    private String receiptPersonName;
    
    @Column(name = "receipt_person_last_name",length = 40,updatable = false)
    private String receiptPersonLastName;
    
    @Column(name = "receipt_bank_name",length = 40,updatable = false)
    private String receiptBankName;
    
    @Column(name = "created_time", nullable = false)
    private LocalDateTime createdTime;
    
    @ManyToOne
    @JoinColumn(name = "transaction_outgoing_account_number", referencedColumnName = "account_number",updatable = false)
    private CustomerEntityAccount customerEntityAccountTransactionOutgoing;
} 

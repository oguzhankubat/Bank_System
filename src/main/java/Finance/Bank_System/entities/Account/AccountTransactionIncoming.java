package Finance.Bank_System.entities.Account;


import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
	    name = "account_transactions",
	    indexes = {
	        @Index(name = "idx_transaction_incoming_account_number", columnList = "transaction_incoming_account_number")
	    }
	)
public class AccountTransactionIncoming {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    @Column(name = "transaction_type",length = 9)
    private String transactionType;
    
    @Column(name = "transaction_description",length = 150)
    private String transactionDescription;

    @Column(name = "transaction_amount", nullable = false)
    private double transactionAmount;

    @Column(name = "sender_person_name", length = 35, nullable = false)
    private String senderPersonName;

    @Column(name = "sender_person_last_name", length = 20, nullable = false)
    private String senderPersonLastName;

    @Column(name = "transaction_number", length = 36, nullable = false, unique = true)
    private String transactionNumber;

    @Column(name = "sender_account_iban", length = 26, nullable = false)
    private String senderAccountIBAN;

    @Column(name = "created_time", nullable = false)
    private LocalDateTime createdTime;
    
    @ManyToOne
    @JoinColumn(name = "transaction_incoming_account_number", referencedColumnName = "account_number")
    private CustomerEntityAccount customerEntityAccountTransactionIncoming;
} 
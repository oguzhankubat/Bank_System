package Finance.Bank_System.entities.Account;


import java.math.BigDecimal;
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
	    name = "account_transactions_incoming",
	    indexes = {
	        @Index(name = "idx_customer_entity_account_id_incoming", columnList = "customer_entity_account_id_incoming")
	    }
	)
public class AccountTransactionIncoming {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    
    @Column(name = "transaction_description",length = 150,updatable = false)
    private String transactionDescription;

    @Column(name = "transaction_amount", nullable = false)
    private BigDecimal transactionAmount;

    @Column(name = "sender_person_name", length = 35, nullable = false,updatable = false)
    private String senderPersonName;

    @Column(name = "sender_person_last_name", length = 20, nullable = false,updatable = false)
    private String senderPersonLastName;

    @Column(name = "transaction_number", length = 36, nullable = false, unique = true,updatable = false)
    private String transactionNumber;
    
    @Column(name = "transaction_type",length = 20,updatable = false)
    private String transactionType;

    @Column(name = "sender_account_iban", length = 26, nullable = false,updatable = false)
    private String senderAccountIBAN;

    @Column(name = "created_time")
    private LocalDateTime createdTime;
    
    @ManyToOne
    @JoinColumn(name = "customer_entity_account_id_incoming",updatable = false)
    private CustomerEntityAccount customerEntityAccountTransactionIncoming;
} 
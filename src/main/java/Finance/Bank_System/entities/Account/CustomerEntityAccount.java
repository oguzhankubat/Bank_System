	package Finance.Bank_System.entities.Account;
	
	import java.math.BigDecimal;
	import java.time.LocalDateTime;
	import java.util.List;
	
	import Finance.Bank_System.BankConstants.AccountType;
	import Finance.Bank_System.entities.CustomerEntity.CustomerEntity;
	import jakarta.persistence.CascadeType;
	import jakarta.persistence.Column;
	import jakarta.persistence.Entity;
	import jakarta.persistence.EnumType;
	import jakarta.persistence.Enumerated;
	import jakarta.persistence.GeneratedValue;
	import jakarta.persistence.GenerationType;
	import jakarta.persistence.Id;
	import jakarta.persistence.JoinColumn;
	import jakarta.persistence.ManyToOne;
	import jakarta.persistence.OneToMany;
	import jakarta.persistence.Table;
	import lombok.AllArgsConstructor;
	import lombok.Data;
	import lombok.NoArgsConstructor;
	
	@Entity
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Table(
		    name = "accounts")
	public class CustomerEntityAccount {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "id")
	    private int id;
	
	    @Column(name = "account_iban", length = 26,updatable = false,nullable = false)
	    private String accountIban;
	
	    @Column(name = "account_balance", precision = 18, scale = 2)
	    private BigDecimal accountBalance;
	
	    @Column(name = "account_token", length = 36, unique = true,updatable = false,nullable = false)
	    private String accountToken;
	
	    @Column(name = "account_branch_code", length = 4)
	    private String accountBranchCode;
	
	    @Column(name = "account_number", unique = true, length = 7,updatable = false,nullable = false)
	    private String accountNumber;
	    
	    @Enumerated(EnumType.STRING)
	    @Column(name = "account_type", length = 10,nullable = false,updatable = false)
	    private AccountType accountType;
	
	    @Column(name = "account_currency", length = 3, nullable = false,updatable = false)
	    private String accountCurrency;
	
	    @Column(name = "account_status", length = 10)
	    private String accountStatu;
	
	    @Column(name = "created_time")
	    private LocalDateTime createdTime;
	
	    @ManyToOne
	    @JoinColumn(name = "account_customer_entity_number", referencedColumnName = "customer_entity_number", updatable = false)
	    private CustomerEntity customerEntityAccount;
	
	    @OneToMany(mappedBy = "customerEntityAccountTransactionIncoming", cascade = CascadeType.ALL)
	    private List<AccountTransactionIncoming> customerEntitiesAccountTransactionsIncoming;
	    
	    @OneToMany(mappedBy = "customerEntityAccountTransactionOutgoing", cascade = CascadeType.ALL)
	    private List<AccountTransactionOutgoing> customerEntitiesAccountTransactionsOutgoing;
	
	}

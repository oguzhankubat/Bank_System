package Finance.Bank_System.entities.İndividualCustomer.Account;

import java.time.LocalDateTime;
import java.util.List;

import Finance.Bank_System.entities.İndividualCustomer.İndividualCustomer;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "individual_customer_accounts")

public class İndividualCustomerAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    @Column(name="account_ıban",length = 26)
    private String accountIban;
    
    @Column(name = "account_balance")
    private int accountBalance;
    
    @Column(name = "account_branch_code",length = 4)
    private String accountBranchCode;
    
    @Column(name = "account_number", unique = true,length = 7)
    private String accountNumber;
    
    @Column(name = "account_type",length = 10)//vadeli vadesiz
    private String accountType;
    
    @Column(name = "account_currency",length = 3,nullable = false)
    private String accountCurrency;
    
    @Column(name = "account_statu",length = 7)
    private String accountStatu;

    @Column(name="created_time")
    private LocalDateTime createdTime;
    
    @ManyToOne
    @JoinColumn(name = "customer_account_individual_customer_number", referencedColumnName = "individual_customer_number", updatable = false)
    private İndividualCustomer accountİndividualCustomerNumber;

    
    @OneToMany(mappedBy = "individualAccountTransaction")
    private List<İndividualAccountTransaction> individualAccountTransactions;
	

}

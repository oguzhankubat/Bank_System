package Finance.Bank_System.entities.Account;

import java.time.LocalDateTime;
import java.util.List;

import Finance.Bank_System.entities.CustomerEntity.CustomerEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
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
    name = "accounts",
    indexes = {
        @Index(name = "idx_account_number", columnList = "account_number")
    }
)
public class CustomerEntityAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "account_ıban", length = 26)
    private String accountIban;

    @Column(name = "account_balance")
    private int accountBalance;

    @Column(name = "account_token", length = 36, unique = true)
    private String accountToken;

    @Column(name = "account_branch_code", length = 4)
    private String accountBranchCode;

    @Column(name = "account_number", unique = true, length = 7)
    private String accountNumber;

    @Column(name = "account_type", length = 10)
    private String accountType;

    @Column(name = "account_currency", length = 3, nullable = false)
    private String accountCurrency;

    @Column(name = "account_statu", length = 10)
    private String accountStatu;

    @Column(name = "created_time")
    private LocalDateTime createdTime;

    @ManyToOne
    @JoinColumn(name = "account_customer_entity_number", referencedColumnName = "customer_entity_number", updatable = false)
    private CustomerEntity customerEntityAccount;

    @OneToMany(mappedBy = "customerEntityAccountTransaction")
    private List<CustomerEntityAccountTransaction> customerEntitiesAccountTransactions;

}

package Finance.Bank_System.entities.CustomerEntity;

import java.time.LocalDateTime;
import java.util.List;

import Finance.Bank_System.entities.Account.CustomerEntityAccount;
import Finance.Bank_System.entities.Adress.CustomerEntityAdress;
import Finance.Bank_System.entities.Customer.Customer;
import jakarta.persistence.CascadeType;
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
	    name = "customer_entities",
	    indexes = {
	        @Index(name = "idx_customer_type", columnList = "customer_type"),
	        @Index(name = "idx_customer_tc_kimlik_number", columnList = "customer_tc_kimlik_number")
	    }
	)
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "customer_entity_phone_number", length = 13, nullable = false)
    private String customerEntityPhoneNumber;

    @Column(name = "customer_entity_password", length = 64, nullable = false)
    private String customerEntityPassword;

    @Column(name = "customer_entity_statu", length = 10)
    private String customerEntityStatu;

    @Column(name = "created_time")
    private LocalDateTime createdTime;

    @Column(name = "customer_entity_number", unique = true, length = 13)
    private String customerEntityNumber;

    @Column(name = "customer_type",length = 11)
    private String customerType;

    @OneToMany(mappedBy = "customerEntityAccount", cascade = CascadeType.ALL)
    private List<CustomerEntityAccount> customerEntitiesAccounts;

    @OneToMany(mappedBy = "customerEntityAdress", cascade = CascadeType.ALL)
    private List<CustomerEntityAdress> customerEntitiesAdresses;

    @ManyToOne
    @JoinColumn(name = "customer_tc_kimlik_number", referencedColumnName = "tc_kimlik_number")
    private Customer customerEntity;
}

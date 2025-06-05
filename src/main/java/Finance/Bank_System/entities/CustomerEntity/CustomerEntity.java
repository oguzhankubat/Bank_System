package Finance.Bank_System.entities.CustomerEntity;

import java.time.LocalDateTime;
import java.util.List;

import Finance.Bank_System.entities.Account.CustomerEntityAccount;
import Finance.Bank_System.entities.Adress.CustomerEntityAdress;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
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
	    name = "customer_entities"

	)
@Inheritance(strategy = InheritanceType.JOINED)
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


    @OneToMany(mappedBy = "customerEntityAccount", cascade = CascadeType.ALL)
    private List<CustomerEntityAccount> customerEntitiesAccounts;

    @OneToMany(mappedBy = "customerEntityAdress", cascade = CascadeType.ALL)
    private List<CustomerEntityAdress> customerEntitiesAdresses;

}

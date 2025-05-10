package Finance.Bank_System.entities.İndividualCustomer;

import java.time.LocalDateTime;
import java.util.List;

import Finance.Bank_System.entities.Customer.Customer;
import Finance.Bank_System.entities.İndividualCustomer.Account.İndividualCustomerAccount;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "individual_customers")
@ToString(exclude = "associatedİndividualCustomer")
public class İndividualCustomer {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    @Column(name="individual_phone_number",length = 13,nullable = false)
    private String individualPhoneNumber;
    
    @Column(name="individual_account_password",length = 64,nullable = false)
    private String individualAccountPassword;
    
    @Column(name = "individual_account_statu",length = 10)
    private String individualAccountStatu;
    
    @Column(name="created_time")
    private LocalDateTime createdTime;
    
    @Column(name = "individual_customer_number",unique = true,length = 12)
    private String individualCustomerNumber;
    
    @OneToMany(mappedBy = "accountİndividualCustomerNumber")
    private List<İndividualCustomerAccount> individualCustomerAccounts;
    
    @OneToMany(mappedBy = "individualCustomerAdresses",cascade = CascadeType.ALL)
    private List<İndividualCustomerAdress> individualCustomerAdress;
    
    @OneToOne
    @JoinColumn(name = "individual_customer_tc_kimlik_number", referencedColumnName = "tc_kimlik_number")
    private Customer associatedİndividualCustomer;
    
    
    
}
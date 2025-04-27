package Finance.Bank_System.entitiesAboutCustomer;

import java.time.LocalDateTime;
import java.util.List;

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

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "individual_customers")
public class İndividualCustomer {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    @Column(name="individual_phone_number",length = 11,nullable = false)
    private String individualPhoneNumber;
    
    @Column(name="individual_password",length = 64,nullable = false)
    private String accountPassword;
    
    @Column(name = "customer_statu",length = 7)
    private String customerStatu;
    
    @Column(name="created_time")
    private LocalDateTime createdTime;
    
    @Column(name = "individual_customer_number",unique = true,length = 9)
    private String individualCustomerNumber;
    
    @OneToMany(mappedBy = "accountİndividualCustomerNumber")
    private List<CustomerAccount> individualCustomerAccount;
    
    @OneToMany(mappedBy = "individualCustomerAdress",cascade = CascadeType.ALL)
    private List<CustomerAdress> individualCustomerAdresses;
    
    
    @OneToOne
    @JoinColumn(name = "individual_customer_tc_kimlik_number", referencedColumnName = "tc_kimlik_number")
    private Customer associatedİndividualCustomer;
    
    
    
}
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
@Table(name = "corporate_customers")
public class CorporateCustomer {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    @Column(name="corporate_vkn_number",length = 10,nullable = false)
    private String vknNumber;
    
    @Column(name="corporate_phone_number",length = 11,nullable = false)
    private String corporatePhoneNumber;
    
    @Column(name="corporate_account_password",length = 64,nullable = false)
    private String corporateAccountPassword;
    
    @Column(name="created_time")
    private LocalDateTime createdTime;
    
    @Column(name = "corporate_account_statu",length = 7)
    private String corporateAccountStatu;
    
    @Column(name = "corporate_customer_number",unique = true,length = 12)
    private String corporateCustomerNumber;
    
    @OneToMany(mappedBy = "accountCorporateCustomerNumber")
    private List<CustomerAccount> corporateCustomerAccount;
    
    @OneToMany(mappedBy = "corporateCustomerAdress",cascade = CascadeType.ALL)
    private List<CustomerAdress> corporateCustomerAdresses;
    
    @ManyToOne
    @JoinColumn(name = "corporate_customer_tc_kimlik_number", referencedColumnName = "tc_kimlik_number")
    private Customer associatedCorporateCustomer;
    
    
    

}

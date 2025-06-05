package Finance.Bank_System.entities.Customer.CorporateCustomer;

import java.time.LocalDate;

import jakarta.persistence.*;
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
    private int id;
    
    
    @Column(name = "company_name", nullable = false,length = 100)
    private String companyName;

    @Column(name = "tax_number", unique = true, nullable = false,length = 10)
    private String taxNumber;

    @Column(name = "company_foundation_date")
    private LocalDate companyFoundationDate;

    @Column(name = "sector",length = 20)
    private String sector;

    @Column(name = "business_activity",length = 20)
    private String businessActivity;

    @Column(name = "registration_number", unique = true,length = 16)//mersis numara
    private String registrationNumber;
    
    @OneToOne(mappedBy = "corporateCustomer", cascade = CascadeType.ALL)
    private CorporateIndividualCustomer corporateIndividualCustomer;

    @OneToOne(mappedBy = "corporateCustomer", cascade = CascadeType.ALL)
    private CorporateCommercialCustomer corporateCommercialCustomer;

}
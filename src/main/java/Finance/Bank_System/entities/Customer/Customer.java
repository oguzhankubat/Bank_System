package Finance.Bank_System.entities.Customer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import Finance.Bank_System.entities.CorporateCustomer.CorporateCustomer;
import Finance.Bank_System.entities.İndividualCustomer.İndividualCustomer;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "customers")
@ToString(exclude = "individualCustomer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    @Column(name = "person_name", nullable = false,length = 35)
    private String personName;

    @Column(name = "person_lastname", nullable = false,length = 20)
    private String personLastName;

    @Column(name = "tc_kimlik_number", length = 11, nullable = false, unique = true) 
    private String tcKimlikNumber;

    @Column(name = "gender", nullable = false,length = 1)
    private String gender;
    
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;
    
    @Column(name = "birth_place",nullable = false,length = 15)
    private String birthPlace;
    
    @Column(name="created_time")
    private LocalDateTime createdTime;
    
    @Column(name = "residence_adress",nullable = false,length = 150)
    private String residenceAdress;
    
    @Column(name = "customer_statu",length = 7)
    private String customerStatu;
    
    @OneToOne(mappedBy = "associatedİndividualCustomer")
    private İndividualCustomer individualCustomer;
    
    @OneToMany(mappedBy = "associatedCorporateCustomer")
    private List<CorporateCustomer>corporateCustomer;
    
}

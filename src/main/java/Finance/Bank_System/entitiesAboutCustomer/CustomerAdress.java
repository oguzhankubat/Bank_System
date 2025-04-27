package Finance.Bank_System.entitiesAboutCustomer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer_adresses")
public class CustomerAdress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    @Column(name = "adress_statu",length =7)
    private String adressStatu;
    
    @Column(name = "adress_name",length = 7)
    private String adressName;
    
    @Column(name = "address_type",length = 10)
    private String addressType;

    
    @Column(name = "address_detail",length = 40)
    private String addressDetail;
    
    @ManyToOne
    @JoinColumn(name = "customer_individual_customer_number", referencedColumnName = "individual_customer_number")
    private İndividualCustomer individualCustomerAdress;
    
    @ManyToOne
    @JoinColumn(name = "customer_corporate_customer_number", referencedColumnName = "corporate_customer_number")
    private CorporateCustomer corporateCustomerAdress;
    
}
    


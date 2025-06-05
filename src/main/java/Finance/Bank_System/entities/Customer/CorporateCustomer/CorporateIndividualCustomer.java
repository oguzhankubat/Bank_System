package Finance.Bank_System.entities.Customer.CorporateCustomer;

import Finance.Bank_System.entities.Customer.Customer;
import Finance.Bank_System.entities.CustomerEntity.CustomerEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(
	    name = "corporate_individual_customers"
	)
@Data
public class CorporateIndividualCustomer extends CustomerEntity{

    @ManyToOne
    @JoinColumn(name = "corporate_individual_customer")
    private Customer corporateIndividualCustomer; 
    
    
    @OneToOne
    @JoinColumn(name = "corporate_customer_id")
    private CorporateCustomer corporateCustomer;

}

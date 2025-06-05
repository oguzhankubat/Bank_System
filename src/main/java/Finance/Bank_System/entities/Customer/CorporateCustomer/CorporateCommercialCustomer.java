package Finance.Bank_System.entities.Customer.CorporateCustomer;

import Finance.Bank_System.entities.CustomerEntity.CustomerEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(
	    name = "corporate_commercial_customers"
	)
@Data
public class CorporateCommercialCustomer extends CustomerEntity{
    
    @OneToOne
    @JoinColumn(name = "corporate_customer_id")
    private CorporateCustomer corporateCustomer;

}

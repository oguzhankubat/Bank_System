package Finance.Bank_System.entities.Customer.IndividualCustomer;

import Finance.Bank_System.entities.Customer.Customer;
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
@Table(name = "individual_customers")
@Data
public class IndividualCustomer extends CustomerEntity{
    
    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer; 
}

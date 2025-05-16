package Finance.Bank_System.dataRepositories.CustomerEntity;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import Finance.Bank_System.entities.Customer.Customer;
import Finance.Bank_System.entities.CustomerEntity.CustomerEntity;

public interface CustomerEntityRepository extends JpaRepository<CustomerEntity, Long> {


    boolean existsByCustomerEntity(Customer customerEntity);
    
    
    boolean existsByCustomerEntityPhoneNumber(String phoneNumber);
    
 
    boolean existsByCustomerEntityNumber(String customerEntityNumber);

    CustomerEntity findByCustomerEntity_TcKimlikNumber(String tcKimlikNumber);
    
    List<CustomerEntity> findByCustomerType(String customerType);


    Optional<CustomerEntity> findByCustomerEntityNumberAndCustomerType(String customerEntityNumber, String customerType);
}
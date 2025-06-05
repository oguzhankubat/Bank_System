package Finance.Bank_System.dataRepositories.CustomerTypes;

import org.springframework.data.jpa.repository.JpaRepository;

import Finance.Bank_System.entities.CustomerEntity.CustomerEntity;

public interface CustomerEntityRepository extends JpaRepository<CustomerEntity, Long> {
     
    boolean existsByCustomerEntityPhoneNumber(String phoneNumber);   
 
    boolean existsByCustomerEntityNumber(String customerEntityNumber);


}
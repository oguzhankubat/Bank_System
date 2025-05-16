package Finance.Bank_System.dataRepositories.CustomerEntity;

import org.springframework.data.jpa.repository.JpaRepository;

import Finance.Bank_System.entities.Adress.CustomerEntityAdress;

public interface CustomerEntityAdressesRepository extends JpaRepository<CustomerEntityAdress, Long>{

}

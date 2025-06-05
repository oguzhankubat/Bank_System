package Finance.Bank_System.dataRepositories.Accounts;

import org.springframework.data.jpa.repository.JpaRepository;

import Finance.Bank_System.entities.Adress.CustomerEntityAdress;

public interface AdressRepository extends JpaRepository<CustomerEntityAdress, Long>{

}

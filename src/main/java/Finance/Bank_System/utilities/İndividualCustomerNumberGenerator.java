package Finance.Bank_System.utilities;

import java.security.SecureRandom;

import org.springframework.stereotype.Component;

import Finance.Bank_System.dataRepositories.CustomerEntity.CustomerEntityRepository;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class İndividualCustomerNumberGenerator {

    private final CustomerEntityRepository customerEntityRepository;

    public String musteriNumarasiOlustur() {
        SecureRandom secureRandom = new SecureRandom();
        String generatedCustomerNumber;

        do {
            StringBuilder musteriNumarasi = new StringBuilder();
            for (int i = 0; i < 12; i++) {
                int rakam = secureRandom.nextInt(10);
                musteriNumarasi.append(rakam);
            }
            generatedCustomerNumber = musteriNumarasi.toString();
        } while (customerEntityRepository.existsByCustomerEntityNumber(generatedCustomerNumber));

        return generatedCustomerNumber;
    }
}

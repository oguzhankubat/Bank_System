package Finance.Bank_System.utilities;

import java.security.SecureRandom;

public class CorporateCustomerNumberGenerator {

    public static String musteriNumarasiOlustur() {
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder musteriNumarasi = new StringBuilder();

        for (int i = 0; i < 13; i++) {
            int rakam = secureRandom.nextInt(10);
            musteriNumarasi.append(rakam);
        }
        
        return musteriNumarasi.toString();
    }
    
}

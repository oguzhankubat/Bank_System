package Finance.Bank_System.utilities;

import java.security.SecureRandom;

public class İndividualCustomerNumberGenerator {
    public static String musteriNumarasiOlustur() {
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder musteriNumarasi = new StringBuilder();

        for (int i = 0; i < 12; i++) {
            int rakam = secureRandom.nextInt(10);
            musteriNumarasi.append(rakam);
        }

        return musteriNumarasi.toString();
    }

}

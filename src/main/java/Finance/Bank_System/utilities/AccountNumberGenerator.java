package Finance.Bank_System.utilities;

import java.security.SecureRandom;

public class AccountNumberGenerator {

    private static final SecureRandom secureRandom = new SecureRandom();

    public static String generateAccountNumber() {
        String accountNumber;
        do {
     
            accountNumber = String.valueOf(secureRandom.nextInt(9000000) + 1000000);
        } while (isSequentialOrSame(accountNumber));
        return accountNumber;
    }

    private static boolean isSequentialOrSame(String accountNumber) {
 
        if (accountNumber.chars().distinct().count() == 1) {
            return true;
        }

        boolean isAscending = true;
        boolean isDescending = true;

        for (int i = 1; i < accountNumber.length(); i++) {
            if (accountNumber.charAt(i) != accountNumber.charAt(i - 1) + 1) {
                isAscending = false;
            }
            if (accountNumber.charAt(i) != accountNumber.charAt(i - 1) - 1) {
                isDescending = false;
            }
        }

        return isAscending || isDescending;
    }
    
}

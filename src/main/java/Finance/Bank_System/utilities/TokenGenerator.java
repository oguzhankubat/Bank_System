package Finance.Bank_System.utilities;

import java.util.UUID;

public class TokenGenerator {
	
	public static String generateUniqueToken() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

}

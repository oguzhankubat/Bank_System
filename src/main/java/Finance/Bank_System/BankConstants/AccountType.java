package Finance.Bank_System.BankConstants;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum AccountType {
    SAVINGS,         
    CHECKING,        
    FIXED_DEPOSIT,  
    LOAN,            
    CREDIT_CARD,     
    INVESTMENT;    

    @JsonValue
    public String toValue() {
        return this.name();
    }

    @JsonCreator
    public static AccountType fromString(String value) {
        if (value == null) {
            throw new RuntimeException("Account Type cannot be null.");
        }

        value = value.trim().toUpperCase()
                     .replace("İ", "I")
                     .replace("ı", "i");

        try {
            return AccountType.valueOf(value);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid Account Type: " + value + ". Please provide a valid type.");
        }
    }
}

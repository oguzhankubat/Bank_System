package Finance.Bank_System.BankConstants;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum AccountCurrency {
    USD,
    EUR,
    TRY,
    GBP, 
    CAD,
    AUD,
    CHF,
    JPY,
    INR,
    NZD,
    XAU;

    @JsonValue
    public String toValue() {
        return this.name();
    }

    @JsonCreator
    public static AccountCurrency fromString(String value) {
        if (value == null) {
            throw new RuntimeException("Account Currency cannot be null.");
        }

        value = value.trim().toUpperCase()
                     .replace("İ", "I")
                     .replace("ı", "i");

        try {
            return AccountCurrency.valueOf(value);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid Account Currency: " + value + ". Please provide a valid currency.");
        }
    }
}

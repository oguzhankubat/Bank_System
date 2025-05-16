package Finance.Bank_System.BankConstants;

public enum BankConstants {
    BANK_VKN("1111111119"),
    ACCOUNT_OWNERSHIP_BANK("8801820c-8846-4185-a7a8-8095a97dea36"),
    CUSTOMER_TYPE_INDIVIDUAL("Individual"),
    CUSTOMER_TYPE_CORPORATE("Corporate");

    private final String value;

   
    BankConstants(String value) {
        this.value = value;
    }


    public String getValue() {
        return value;
    }
}

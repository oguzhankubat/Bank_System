package Finance.Bank_System.BankConstants;

public enum BankConstants {
    BANK_VKN("1111111119"),
    ACCOUNT_OWNERSHIP_BANK("80a98395-de37-4b81-938b-a442d9c2591a"),
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

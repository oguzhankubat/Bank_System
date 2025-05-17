package Finance.Bank_System.BankConstants;

public enum BankConstants {
    BANK_VKN("1111111119"),
    ACCOUNT_OWNERSHIP_BANK("8801820c-8846-4185-a7a8-8095a97dea36"),
    CUSTOMER_TYPE_INDIVIDUAL("Individual"),
    CUSTOMER_TYPE_CORPORATE("Corporate"),
	FAST_SYSTEM_API("http://localhost:8088/api/transaction/process"),
	BANK_ACCOUNT_TRANSACTION_CALLBACK_URL("http://localhost:8090/api/accountTransaction/incoming");

    private final String value;

   
    BankConstants(String value) {
        this.value = value;
    }


    public String getValue() {
        return value;
    }
}

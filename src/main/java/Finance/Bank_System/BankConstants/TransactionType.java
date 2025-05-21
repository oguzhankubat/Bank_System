package Finance.Bank_System.BankConstants;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum TransactionType {
    DEPOSIT,
    WITHDRAWAL,
    TRANSFER,
    PAYMENT,
    REFUND,
    INTEREST_CREDIT,
    FEE,
    CHARGEBACK,
    LOAN_DISBURSEMENT,
    LOAN_REPAYMENT,
    CASH_ADVANCE,
    FOREIGN_EXCHANGE,
    DIRECT_DEBIT,
    BILL_PAYMENT,
    ACCOUNT_OPENING,
    ACCOUNT_CLOSURE;

    @JsonCreator
    public static TransactionType fromString(String value) {
        if (value == null) {
            return null;
        }

 
        value = value.trim().toUpperCase()
                     .replace("İ", "I")
                     .replace("ı", "i");

        try {
            return TransactionType.valueOf(value);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid transaction type: " + value);
        }
    }
}
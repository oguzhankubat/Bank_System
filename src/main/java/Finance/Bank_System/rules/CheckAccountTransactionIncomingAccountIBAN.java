package Finance.Bank_System.rules;

import org.springframework.stereotype.Component;

import Finance.Bank_System.business.requests.CommonRequests.AccountTransactionToBankSystemRequest;
import Finance.Bank_System.core.MessageService;
import Finance.Bank_System.dataRepositories.CustomerEntity.AccountRepository;
import Finance.Bank_System.entities.Account.CustomerEntityAccount;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CheckAccountTransactionIncomingAccountIBAN {

    private final MessageService messageService;
    private final AccountRepository accountRepository;

    public CustomerEntityAccount check(AccountTransactionToBankSystemRequest requests) {
      
        CustomerEntityAccount account = accountRepository.findByAccountIban(requests.getReceiptBankAccountIBAN())
                .orElseThrow(() -> new RuntimeException(
                        messageService.getMessage("iban.is.not.found") + " : " + requests.getReceiptBankAccountIBAN())
                );

        if (!account.getAccountToken().equals(requests.getReceiptBankAccountToken())) {
            throw new RuntimeException(
                messageService.getMessage("account.token.is.not.validated") + " : " + requests.getReceiptBankAccountToken()
            );
        }

   
        return account;
    }
}
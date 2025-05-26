package Finance.Bank_System.rules;

import java.util.Objects;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CheckTcKimlikNumberRuleUpdateCustomerIfChanged {

    public boolean updateCustomerIfChanged(Finance.Bank_System.entities.Customer.Customer existingCustomer, Finance.Bank_System.DTO_pojo_records.ExternalAPICivilSystenCivilResponse civilCustomer) {
        boolean isUpdated = false;

        if (!Objects.equals(existingCustomer.getPersonName(), civilCustomer.getPersonName())) {
            existingCustomer.setPersonName(civilCustomer.getPersonName());
            isUpdated = true;
        }
        if (!Objects.equals(existingCustomer.getPersonLastName(), civilCustomer.getPersonLastName())) {
            existingCustomer.setPersonLastName(civilCustomer.getPersonLastName());
            isUpdated = true;
        }
        if (!Objects.equals(existingCustomer.getBirthDate(), civilCustomer.getBirthDate())) {
            existingCustomer.setBirthDate(civilCustomer.getBirthDate());
            isUpdated = true;
        }
        if (!Objects.equals(existingCustomer.getGender(), civilCustomer.getGender())) {
            existingCustomer.setGender(civilCustomer.getGender());
            isUpdated = true;
        }
        if (!Objects.equals(existingCustomer.getBirthPlace(), civilCustomer.getBirthPlace())) {
            existingCustomer.setBirthPlace(civilCustomer.getBirthPlace());
            isUpdated = true;
        }
        if (!Objects.equals(existingCustomer.getResidenceAdress(), civilCustomer.getResidenceAdress())) {
            existingCustomer.setResidenceAdress(civilCustomer.getResidenceAdress());
            isUpdated = true;
        }

        return isUpdated;
    }
}
package Finance.Bank_System.business.concretes.İndividualCustomer;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import Finance.Bank_System.BankConstants.BankConstants;
import Finance.Bank_System.DTO_pojo_records.InternalGetAllCustomerEntity;
import Finance.Bank_System.business.abstracts.individualCustomer.İndividualCustomerService;
import Finance.Bank_System.business.requests.İndividualCustomer.CreateİndividualCustomerRequest;
import Finance.Bank_System.business.responses.İndividualCustomer.AfterCreateİndividualCustomerResponse;
import Finance.Bank_System.core.ModelMapperServices;
import Finance.Bank_System.dataRepositories.CustomerEntity.CustomerEntityRepository;
import Finance.Bank_System.entities.Customer.Customer;
import Finance.Bank_System.entities.CustomerEntity.CustomerEntity;
import Finance.Bank_System.rules.CheckExistIndividualCustomer;
import Finance.Bank_System.rules.CheckExistIndividualCustomerPhoneNumber;
import Finance.Bank_System.rules.CheckTcKimlikNumberRule;
import Finance.Bank_System.utilities.HashGenerator;
import Finance.Bank_System.utilities.İndividualCustomerNumberGenerator;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class İndividualCustomerManager implements İndividualCustomerService{
	
	public final CheckTcKimlikNumberRule checkTcKimlikNumberRule;
	public final ModelMapperServices modelMapperServices;
	public final CustomerEntityRepository customerEntityRepository;
	public final CheckExistIndividualCustomer checkExistIndividualCustomer;
	public final CheckExistIndividualCustomerPhoneNumber checkExistIndividualCustomerPhoneNumber;
	public final İndividualCustomerNumberGenerator individualCustomerNumberGenerator;
	
	@Override
	public AfterCreateİndividualCustomerResponse createİndividualCustomer(
			CreateİndividualCustomerRequest createİndividualCustomerRequest) {
		
		Customer customer = checkTcKimlikNumberRule.fetchCustomerFromCivilSystem(createİndividualCustomerRequest);
		
		checkExistIndividualCustomer.exists(customer);
		checkExistIndividualCustomerPhoneNumber.exists(createİndividualCustomerRequest.getCustomerEntityPhoneNumber());
		
		CustomerEntity individualCustomer = modelMapperServices.forRequest()
                .map(createİndividualCustomerRequest, CustomerEntity.class);
		
		individualCustomer.setCustomerEntityNumber(individualCustomerNumberGenerator.musteriNumarasiOlustur());
		individualCustomer.setCustomerEntity(customer);
		individualCustomer.setCreatedTime(LocalDateTime.now());
		individualCustomer.setCustomerEntityStatu("Active");
		individualCustomer.setCustomerEntityPassword(HashGenerator.sha256Hash(createİndividualCustomerRequest.getCustomerEntityPassword()));
		individualCustomer.setCustomerType(BankConstants.CUSTOMER_TYPE_INDIVIDUAL.getValue());
		
		customerEntityRepository.save(individualCustomer);

		
		AfterCreateİndividualCustomerResponse message=new AfterCreateİndividualCustomerResponse("Customer is created succesfully");
		return message;
	}

	@Override
	public List<InternalGetAllCustomerEntity> getAllCustomerEntity() {
	    List<CustomerEntity> persons = customerEntityRepository.findAll();

	    List<InternalGetAllCustomerEntity> personDTOs = persons.stream()
	            .map(person -> modelMapperServices.forResponse().map(person, InternalGetAllCustomerEntity.class))
	            .collect(Collectors.toList());

	    savePersonsToJson(personDTOs);                        

	    return personDTOs;
	}

    private void savePersonsToJson(List<InternalGetAllCustomerEntity> persons) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        File directory = new File("data");

        if (!directory.exists()) {
            directory.mkdirs();
        }

        try {
            objectMapper.writeValue(new File(directory, "customerEntity.json"), persons);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

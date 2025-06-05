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

import Finance.Bank_System.DTO_pojo_records.InternalGetAllCustomerEntity;
import Finance.Bank_System.business.abstracts.individualCustomer.İndividualCustomerService;
import Finance.Bank_System.business.requests.İndividualCustomer.CreateİndividualCustomerRequest;
import Finance.Bank_System.business.responses.İndividualCustomer.AfterCreateİndividualCustomerResponse;
import Finance.Bank_System.core.ModelMapperServices;
import Finance.Bank_System.dataRepositories.CustomerTypes.CustomerEntityRepository;
import Finance.Bank_System.dataRepositories.CustomerTypes.IndividualCustomerRepository;
import Finance.Bank_System.entities.Customer.Customer;
import Finance.Bank_System.entities.Customer.IndividualCustomer.IndividualCustomer;
import Finance.Bank_System.entities.CustomerEntity.CustomerEntity;
import Finance.Bank_System.rules.CheckExistIndividualCustomer;
import Finance.Bank_System.rules.CheckExistIndividualCustomerPhoneNumber;
import Finance.Bank_System.rules.CheckTcKimlikNumberRule;
import Finance.Bank_System.utilities.HashGenerator;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class İndividualCustomerManager implements İndividualCustomerService{
	
	private final CheckTcKimlikNumberRule checkTcKimlikNumberRule;
	private final ModelMapperServices modelMapperServices;
	private final CustomerEntityRepository customerEntityRepository;
	private final CheckExistIndividualCustomer checkExistIndividualCustomer;
	private final CheckExistIndividualCustomerPhoneNumber checkExistIndividualCustomerPhoneNumber;
	private final Finance.Bank_System.utilities.CustomerNumberGenerator CustomerNumberGenerator;
	private final IndividualCustomerRepository individualCustomerRepository;

	
	@Override
	public AfterCreateİndividualCustomerResponse createİndividualCustomer(
			CreateİndividualCustomerRequest createİndividualCustomerRequest) {
		
		checkExistIndividualCustomerPhoneNumber.exists(createİndividualCustomerRequest.getCustomerEntityPhoneNumber());
		
		Customer customer = checkTcKimlikNumberRule.fetchCustomerFromCivilSystem(createİndividualCustomerRequest);
		
		checkExistIndividualCustomer.exists(customer);

		IndividualCustomer individualCustomer = modelMapperServices.forRequest()
                .map(createİndividualCustomerRequest, IndividualCustomer.class);
		
		individualCustomer.setCustomerEntityNumber(CustomerNumberGenerator.musteriNumarasiOlustur());
		individualCustomer.setCreatedTime(LocalDateTime.now());
		individualCustomer.setCustomerEntityStatu("Active");
		individualCustomer.setCustomerEntityPassword(HashGenerator.sha256Hash(createİndividualCustomerRequest.getCustomerEntityPassword()));
		individualCustomer.setCustomer(customer);	
	    individualCustomerRepository.save(individualCustomer);
  
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

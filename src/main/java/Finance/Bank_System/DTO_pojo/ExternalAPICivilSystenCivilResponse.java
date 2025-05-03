package Finance.Bank_System.DTO_pojo;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ExternalAPICivilSystenCivilResponse {
    private String personName;
    private String personLastName;
    private String tcKimlikNumber;
    private String gender;
    private LocalDate birthDate;
    private String birthPlace;
    private String residenceAdress;
}

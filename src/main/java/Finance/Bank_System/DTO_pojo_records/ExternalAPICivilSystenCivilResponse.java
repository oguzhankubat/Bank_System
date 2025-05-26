package Finance.Bank_System.DTO_pojo_records;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExternalAPICivilSystenCivilResponse {
    private String personName;
    private String personLastName;
    private String tcKimlikNumber;
    private String gender;
    private LocalDate birthDate;
    
    private String birthPlace;
    private String residenceAdress;
}

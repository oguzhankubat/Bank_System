package Finance.Bank_System.DTO_pojo;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ExternalAPICivilSystenCivilResponse {
    private String personName;
    private String personLastName;
    private String tcKimlikNumber;
    private String gender;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    private String birthPlace;
    private String residenceAdress;
}

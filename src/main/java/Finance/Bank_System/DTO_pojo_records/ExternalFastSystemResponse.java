package Finance.Bank_System.DTO_pojo_records;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ExternalFastSystemResponse {

    private final String accountToken;
    private final String accountIBAN;

    @JsonCreator
    public ExternalFastSystemResponse(
        @JsonProperty("accountToken") String accountToken,
        @JsonProperty("accountIBAN") String accountIBAN
    ) {
        this.accountToken = accountToken;
        this.accountIBAN = accountIBAN;
    }
}
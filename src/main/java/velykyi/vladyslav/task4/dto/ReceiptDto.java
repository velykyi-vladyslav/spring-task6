package velykyi.vladyslav.task4.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.NonNull;
import velykyi.vladyslav.task4.model.Status;

import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReceiptDto {
    @Id
    private Long id;
    @NonNull
    @JsonProperty("bill")
    @JsonSerialize(using = BillSerializer.class)
    private BigDecimal bill;
    //todo Employee
    private StatusDto parentStatus;

    public ReceiptDto() {
    }

    public ReceiptDto(Long id, @NonNull BigDecimal bill, StatusDto parentStatus) {
        this.id = id;
        this.bill = bill;
        this.parentStatus = parentStatus;
    }
}

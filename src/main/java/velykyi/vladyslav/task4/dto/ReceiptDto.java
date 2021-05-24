package velykyi.vladyslav.task4.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.NonNull;

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

    public ReceiptDto() {
    }

    public ReceiptDto(@NonNull BigDecimal bill) {
        this.bill = bill;
    }
}

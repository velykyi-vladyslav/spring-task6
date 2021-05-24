package velykyi.vladyslav.task4.controller.model;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;
import velykyi.vladyslav.task4.dto.ReceiptDto;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class ReceiptModel extends RepresentationModel<ReceiptModel> {

    @JsonUnwrapped
    private ReceiptDto receiptDto;
}

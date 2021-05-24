package velykyi.vladyslav.task4.controller.assembler;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import velykyi.vladyslav.task4.controller.ReceiptController;
import velykyi.vladyslav.task4.controller.model.ReceiptModel;
import velykyi.vladyslav.task4.dto.ReceiptDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ReceiptAssembler extends RepresentationModelAssemblerSupport<ReceiptDto, ReceiptModel> {
    public ReceiptAssembler() {
        super(ReceiptController.class, ReceiptModel.class);
    }

    @Override
    public ReceiptModel toModel(ReceiptDto entity) {
        ReceiptModel receiptModel = new ReceiptModel(entity);


        return receiptModel;
    }
}

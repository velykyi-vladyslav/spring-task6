package velykyi.vladyslav.task4.controller.assembler;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import velykyi.vladyslav.task4.controller.ReceiptController;
import velykyi.vladyslav.task4.controller.RoleController;
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

        Link get = linkTo(methodOn(ReceiptController.class)
                .getReceipt(entity.getId()))
                .withRel("get");
        Link delete = linkTo(methodOn(ReceiptController.class)
                .deleteReceipt(entity.getId()))
                .withRel("delete");
        Link update = linkTo(methodOn(ReceiptController.class)
                .updateReceipt(entity.getId(), entity))
                .withRel("update");
        Link create = linkTo(methodOn(ReceiptController.class)
                .createReceipt(entity))
                .withRel("create");

        receiptModel.add(get, delete, update, create);

        return receiptModel;
    }
}

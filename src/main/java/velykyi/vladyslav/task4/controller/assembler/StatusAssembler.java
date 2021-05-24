package velykyi.vladyslav.task4.controller.assembler;


import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import velykyi.vladyslav.task4.controller.StatusController;
import velykyi.vladyslav.task4.controller.model.StatusModel;
import velykyi.vladyslav.task4.dto.StatusDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class StatusAssembler extends RepresentationModelAssemblerSupport<StatusDto, StatusModel> {

    public StatusAssembler() {
        super(StatusController.class, StatusModel.class);
    }

    @Override
    public StatusModel toModel(StatusDto entity) {
        StatusModel statusModel = new StatusModel(entity);

        Link get = linkTo(methodOn(StatusController.class)
                .getStatus(entity.getName()))
                .withRel("get");

        statusModel.add(get);

        return statusModel;
    }
}

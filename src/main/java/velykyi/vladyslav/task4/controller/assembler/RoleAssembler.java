package velykyi.vladyslav.task4.controller.assembler;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import velykyi.vladyslav.task4.controller.RoleController;
import velykyi.vladyslav.task4.controller.model.RoleModel;
import velykyi.vladyslav.task4.dto.RoleDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RoleAssembler extends RepresentationModelAssemblerSupport<RoleDto, RoleModel> {

    public RoleAssembler() {
        super(RoleController.class, RoleModel.class);
    }

    @Override
    public RoleModel toModel(RoleDto entity) {
        RoleModel roleModel = new RoleModel(entity);

        Link get = linkTo(methodOn(RoleController.class)
                .getRole(entity.getName()))
                .withRel("get");
        Link delete = linkTo(methodOn(RoleController.class)
                .deleteRole(entity.getName()))
                .withRel("delete");

        roleModel.add(get, delete);

        return roleModel;
    }
}

package velykyi.vladyslav.task4.controller.assembler;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import velykyi.vladyslav.task4.controller.EmployeeController;
import velykyi.vladyslav.task4.controller.model.EmployeeModel;
import velykyi.vladyslav.task4.dto.EmployeeDto;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class EmployeeAssembler extends RepresentationModelAssemblerSupport<EmployeeDto, EmployeeModel> {

    public EmployeeAssembler() {
        super(EmployeeController.class, EmployeeModel.class);
    }

    @Override
    public EmployeeModel toModel(EmployeeDto entity) {
        EmployeeModel employeeModel = new EmployeeModel(entity);

        Link get = linkTo(methodOn(EmployeeController.class)
                .getEmployee(entity.getLogin()))
                .withRel("get");
        Link delete = linkTo(methodOn(EmployeeController.class)
                .deleteEmployee(entity.getLogin()))
                .withRel("delete");

        employeeModel.add(get, delete);

        return employeeModel;
    }
}

package velykyi.vladyslav.task4.service.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import velykyi.vladyslav.task4.dto.EmployeeDto;
import velykyi.vladyslav.task4.dto.RoleDto;
import velykyi.vladyslav.task4.model.Employee;
import velykyi.vladyslav.task4.model.Role;
import velykyi.vladyslav.task4.service.RoleService;


@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public abstract class EmployeeMapper {
    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleService roleService;

    abstract public EmployeeDto employeeToEmployeeDto(Employee employee);

    abstract public Employee employeeDtoToEmployee(EmployeeDto employeeDto);

    public RoleDto map(Role parentRole) {
        return new RoleDto(parentRole.getName());
    }

    public Role map(RoleDto parentRole) {
        return roleService.getRole(parentRole.getName());
    }
}

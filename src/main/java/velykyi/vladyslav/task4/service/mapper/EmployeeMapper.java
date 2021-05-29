package velykyi.vladyslav.task4.service.mapper;

import lombok.RequiredArgsConstructor;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import velykyi.vladyslav.task4.dto.EmployeeDto;
import velykyi.vladyslav.task4.dto.RoleDto;
import velykyi.vladyslav.task4.model.Employee;
import velykyi.vladyslav.task4.model.Role;
import velykyi.vladyslav.task4.service.RoleService;
import velykyi.vladyslav.task4.service.impl.RoleServiceImpl;

import java.math.BigDecimal;


@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public abstract class EmployeeMapper {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleService roleService;

    abstract public EmployeeDto employeeToEmployeeDto(Employee employee);

    abstract public Employee employeeDtoToEmployee(EmployeeDto employeeDto);

    public RoleDto mapRoleDtoToRole(Role parentRole) {
        return new RoleDto(parentRole.getName());
    }

    public Role mapRoleToRoleDto(RoleDto parentRole) {
        return roleService.getRole(parentRole.getName());
    }
}

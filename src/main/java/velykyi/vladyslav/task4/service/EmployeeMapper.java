package velykyi.vladyslav.task4.service;

import org.mapstruct.Mapper;
import velykyi.vladyslav.task4.dto.EmployeeDto;
import velykyi.vladyslav.task4.model.Employee;

@Mapper
public interface EmployeeMapper {
EmployeeDto employeeToEmployeeDto(Employee employee);
Employee employeeDtoToEmployee(EmployeeDto employeeDto);
}

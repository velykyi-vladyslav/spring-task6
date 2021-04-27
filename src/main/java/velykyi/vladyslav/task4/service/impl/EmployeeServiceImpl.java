package velykyi.vladyslav.task4.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import velykyi.vladyslav.task4.dto.EmployeeDto;
import velykyi.vladyslav.task4.model.Employee;
import velykyi.vladyslav.task4.repository.EmployeeRepository;
import velykyi.vladyslav.task4.service.EmployeeMapper;
import velykyi.vladyslav.task4.service.EmployeeService;


@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private EmployeeMapper mapper = Mappers.getMapper(EmployeeMapper.class);

    @Override
    public EmployeeDto getEmployee(String login) {
        Employee employee = employeeRepository.getEmployee(login);
        log.info("getEmployee by email: {}", employee);
        return mapEmployeeToEmployeeDto(employee);
    }

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = mapEmployeeDtoToEmployee(employeeDto);
        employee = employeeRepository.createEmployee(employee);
        log.info("createEmployee: {}", employee);
        return mapEmployeeToEmployeeDto(employee);
    }

    @Override
    public void deleteEmployee(String login) {
        log.info("deleteEmployee by login: {}", login);
        employeeRepository.deleteEmployee(login);
    }

    @Override
    public EmployeeDto updateEmployee(String login, EmployeeDto employeeDto) {
        Employee employee = mapEmployeeDtoToEmployee(employeeDto);
        employee = employeeRepository.updateEmployee(login, employee);
        log.info("updateEmployee by login: {}", login + " ; updated employee: " + employee);
        return  mapEmployeeToEmployeeDto(employee);
    }

    private EmployeeDto mapEmployeeToEmployeeDto(Employee employee) {
        log.info("Mapping [Employee] to [EmployeeDTO]");
       return mapper.employeeToEmployeeDto(employee);
//        return EmployeeDto.builder()
//                .login(employee.getLogin())
//                .password(employee.getPassword())
//                .firstName(employee.getFirstName())
//                .lastName(employee.getLastName())
//                .localeId(String.valueOf(employee.getLocaleId()))
//                .roleId(String.valueOf(employee.getRoleId()))
//                .build();
    }

    private Employee mapEmployeeDtoToEmployee(EmployeeDto employeeDto) {
        log.info("Mapping [EmployeeDTO] to [Employee]");
        return mapper.employeeDtoToEmployee(employeeDto);
//        return Employee.builder()
//                .login(employeeDto.getLogin())
//                .password(employeeDto.getPassword())
//                .firstName(employeeDto.getFirstName())
//                .lastName(employeeDto.getLastName())
//                .localeId(Integer.parseInt(employeeDto.getLocaleId()))
//                .roleId(Integer.parseInt(employeeDto.getRoleId()))
//                .build();
    }
}

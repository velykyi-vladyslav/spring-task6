package velykyi.vladyslav.task4.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;
import velykyi.vladyslav.task4.dto.EmployeeDto;
import velykyi.vladyslav.task4.exceptions.EmployeeNotFoundException;
import velykyi.vladyslav.task4.model.Employee;
import velykyi.vladyslav.task4.repository.EmployeeRepository;
import velykyi.vladyslav.task4.service.mapper.EmployeeMapper;
import velykyi.vladyslav.task4.service.EmployeeService;


@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    private  EmployeeMapper employeeMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeDto getEmployee(String login) {
        Employee employee = employeeRepository.findByLogin(login).orElseThrow(EmployeeNotFoundException::new);
        log.info("getEmployee by login: {}", employee);
        return mapEmployeeToEmployeeDto(employee);
    }

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        log.info("createEmployee: {}", employeeDto);
        Employee employee = mapEmployeeDtoToEmployee(employeeDto);

        employee = employeeRepository.save(employee);

        return mapEmployeeToEmployeeDto(employee);
    }

    @Override
    public void deleteEmployee(String login) {
        log.info("deleteEmployee by login: {}", login);

        Employee employee = employeeRepository.findByLogin(login)
                .orElseThrow(EmployeeNotFoundException::new);
        employeeRepository.delete(employee);
    }

    @Override
    public EmployeeDto updateEmployee(String login, EmployeeDto employeeDto) {
        Employee employee = mapEmployeeDtoToEmployee(employeeDto);
        log.info("updateEmployee by login: {}", login + " ; updated employee: " + employee);

        if (!employeeRepository.existsByLogin(login)) {
            log.error("employee is not exists with this login: {}", login);
            throw new EmployeeNotFoundException();
        }

        employee = employeeRepository.save(employee);
        return mapEmployeeToEmployeeDto(employee);


    }

    private EmployeeDto mapEmployeeToEmployeeDto(Employee employee) {
        log.info("Mapping [Employee] to [EmployeeDTO]");
        return employeeMapper.employeeToEmployeeDto(employee);
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
        return employeeMapper.employeeDtoToEmployee(employeeDto);
//        return Employee.builder()
//                .login(employeeDto.getLogin())
//                .password(employeeDto.getPassword())
//                .firstName(employeeDto.getFirstName())
//                .lastName(employeeDto.getLastName())
//                .localeId(Integer.parseInt(employeeDto.getLocaleId()))
//                .parentRole(getRole(employeeDto.getParentRole()))
//                .build();
    }


}

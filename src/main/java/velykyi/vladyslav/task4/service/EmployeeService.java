package velykyi.vladyslav.task4.service;

import velykyi.vladyslav.task4.dto.EmployeeDto;

public interface EmployeeService {
    EmployeeDto getEmployee(String login);

    EmployeeDto createEmployee(EmployeeDto employeeDto);

    void deleteEmployee(String login);

    EmployeeDto updateEmployee(String login, EmployeeDto employeeDto);
}

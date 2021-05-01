package velykyi.vladyslav.task4.repository.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import velykyi.vladyslav.task4.exceptions.EmployeeNotFoundException;
import velykyi.vladyslav.task4.model.Employee;
import velykyi.vladyslav.task4.repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private List<Employee> list = new ArrayList<>();

    @Override
    public Employee getEmployee(String login){
        return list.stream()
                .filter(employee -> employee.getLogin().equals(login))
                .findFirst()
                .orElseThrow(EmployeeNotFoundException::new);
    }

    @Override
    public Employee createEmployee(Employee employee) {
        list.add(employee);
        return employee;
    }

    @Override
    public Employee updateEmployee(String login, Employee employee) {
        boolean isDeleted = list.removeIf(e -> e.getLogin().equals(login));
        if (isDeleted) {
            list.add(employee);
        } else {
            throw new RuntimeException("User does not exists");
        }
        return employee;
    }

    @Override
    public void deleteEmployee(String login) {
        list.removeIf(employee -> employee.getLogin().equals(login));
    }

}

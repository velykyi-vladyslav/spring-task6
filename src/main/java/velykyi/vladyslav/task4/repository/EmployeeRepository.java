package velykyi.vladyslav.task4.repository;

import velykyi.vladyslav.task4.model.Employee;

public interface EmployeeRepository {
    Employee getEmployee(String login);

    Employee createEmployee(Employee employee);

    Employee updateEmployee(String login, Employee employee);

    void deleteEmployee(String login);
}

package velykyi.vladyslav.task4.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import velykyi.vladyslav.task4.dto.EmployeeDto;
import velykyi.vladyslav.task4.service.EmployeeService;

import javax.validation.Valid;
import javax.validation.constraints.Size;

@Slf4j
@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
@Validated
public class EmployeeController {
    private final EmployeeService employeeService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{login}")
    public EmployeeDto getEmployee(@PathVariable String login) {
        log.info("Get employee by login: " + login);
        return employeeService.getEmployee(login);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public EmployeeDto createEmployee(@Valid @RequestBody EmployeeDto employeeDto) {
        log.info("Create employee: {}", employeeDto);
        return employeeService.createEmployee(employeeDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{login}")
    public EmployeeDto updateEmployee(@PathVariable String login, @RequestBody EmployeeDto employeeDto) {
        log.info("Update employee: {}", employeeDto + " by login: " + login);
        return employeeService.updateEmployee(login, employeeDto);
    }

    //demonstration @Validated
    @DeleteMapping(value = "/{login}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable("login") @Size(min = 1, max = 2) String login) {
        log.info("Delete employee by login: " + login);
        employeeService.deleteEmployee(login);
        return ResponseEntity.noContent().build();
    }
}

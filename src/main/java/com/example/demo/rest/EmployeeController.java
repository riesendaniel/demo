package com.example.demo.rest;

import com.example.demo.model.EmployeeDto;
import com.example.demo.service.EmployeeService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("rest/employee/v1")
public class EmployeeController {

    final EmployeeService employeeService;

    EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping(value = "employee")
    public void postEmployee(@RequestBody EmployeeDto employee) {
        employeeService.createEmployee(employee);
    }

    @GetMapping(value = "/employees", produces = MediaType.APPLICATION_JSON_VALUE)
    List<EmployeeDto> getAllEmployees() {
        return employeeService.getEmployees();
    }

    //Count the number of Employees
    @GetMapping(value = "/employees/count")
    Long countEmployees() {
        return (long) employeeService.getEmployees().size();
    }

    @GetMapping(value = "/employees/average-salary")
    Long averageSalary(String department) {
        // calculate the average salary of all employees or when a department is provided, the average salary of the employees in that department
        if (department == null) {
            return Math.round(employeeService.getEmployees()
                    .stream()
                    .mapToInt(EmployeeDto::getSalary)
                    .average().orElse(0));
        }
        return Math.round(employeeService.getEmployees()
                .stream()
                .filter(e -> e.getDepartment().name().equals(department))
                .mapToInt(EmployeeDto::getSalary)
                .average().orElse(0));
    }

    // get the employee with the newest start date
    @GetMapping(value = "/employees/newest")
    EmployeeDto getEmployeeWithNewestStartDate() {
        return employeeService.getEmployees()
                .stream()
                .max(Comparator.comparing(EmployeeDto::getStartDate))
                .orElse(null);
    }

    // get alle roles of the employees
    @GetMapping(value = "/employees/roles")
    List<String> getRoles() {
        return employeeService.getEmployees()
                .stream()
                .map(EmployeeDto::getRole)
                .distinct()
                .toList();
    }

}

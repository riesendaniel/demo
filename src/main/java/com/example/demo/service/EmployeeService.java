package com.example.demo.service;

import com.example.demo.jpa.EmployeeRepository;
import com.example.demo.mapper.EmployeeMapper;
import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeService {

    EmployeeRepository employeeRepository;
    EmployeeMapper employeeMapper;

    EmployeeService(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    public List<EmployeeDto> getEmployees() {
        return convertToDTO(employeeRepository.findAllBy());
    }

    private List<EmployeeDto> convertToDTO(List<Employee> allBy) {
        return allBy.stream().map(d -> employeeMapper.mapToDto(d)).toList();
    }

    public void createEmployee(EmployeeDto employee) {
        employeeRepository.save(employeeMapper.mapToSource(employee));
    }
}

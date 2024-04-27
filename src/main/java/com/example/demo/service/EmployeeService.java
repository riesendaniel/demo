package com.example.demo.service;

import com.example.demo.jpa.EmployeeRepository;
import com.example.demo.mapper.EmployeeMapper;
import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    EmployeeMapper employeeMapper;

    public List<EmployeeDto> getEmployees() {
        return convertToDTO(employeeRepository.findAllBy());
    }

    private List<EmployeeDto> convertToDTO(List<Employee> allBy) {
        return allBy.stream().map(d -> employeeMapper.mapToDto(d)).collect(Collectors.toList());
    }

    public void createEmployee(EmployeeDto employee) {
        employeeRepository.save(employeeMapper.mapToSource(employee));
    }
}

package com.example.demo.mapper;

import com.example.demo.model.Department;
import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

@SpringBootTest
@ActiveProfiles("test")
class EmployeeMapperTest {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Test
    void mapToEmployeeDto() {
        //given
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("John");
        employee.setDepartment(Department.IT);
        employee.setRole("Developer");
        employee.setSalary(30);
        employee.setStartDate(LocalDate.parse("2021-01-01"));

        //when
        EmployeeDto dto = employeeMapper.mapToDto(employee);

        //then
        Assertions.assertNotNull(dto);
        Assertions.assertEquals(employee.getId(), dto.getId());
        Assertions.assertEquals(employee.getName(), dto.getName());
        Assertions.assertEquals(employee.getRole(), dto.getRole());
        Assertions.assertEquals(employee.getDepartment(), dto.getDepartment());
        Assertions.assertEquals(employee.getSalary(), dto.getSalary());
        Assertions.assertEquals(employee.getStartDate(), LocalDate.parse(dto.getStartDate()));
    }

    @Test
    void mapToEmployee() {
        //given
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(1L);
        employeeDto.setName("John");
        employeeDto.setDepartment(Department.IT);
        employeeDto.setRole("Developer");
        employeeDto.setSalary(30);
        employeeDto.setStartDate("2021-01-01");

        //when
        Employee employee = employeeMapper.mapToSource(employeeDto);

        //then
        Assertions.assertNotNull(employee);
        Assertions.assertEquals(employeeDto.getId(), employee.getId());
        Assertions.assertEquals(employeeDto.getName(), employee.getName());
        Assertions.assertEquals(employeeDto.getRole(), employee.getRole());
        Assertions.assertEquals(employeeDto.getDepartment(), employee.getDepartment());
        Assertions.assertEquals(employeeDto.getSalary(), employee.getSalary());
        Assertions.assertEquals(LocalDate.parse(employeeDto.getStartDate()), employee.getStartDate());
    }

}

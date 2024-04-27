package com.example.demo.mapper;

import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    @Mapping(source = "name", target = "name")
    EmployeeDto mapToDto(Employee employee);
    @Mapping(source = "name", target = "name")
    Employee mapToSource(EmployeeDto employeeDto);

}

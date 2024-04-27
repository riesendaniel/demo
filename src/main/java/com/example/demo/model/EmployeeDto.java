package com.example.demo.model;

public class EmployeeDto {

    private Long id;
    private String name;
    private String role;
    private Department department;
    private Integer salary;
    private String startDate;

    public EmployeeDto() {
    }

    public EmployeeDto(Long id, String name, String role, Department department, Integer salary, String startDate) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.department = department;
        this.salary = salary;
        this.startDate = startDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
}

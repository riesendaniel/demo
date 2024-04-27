package com.example.demo.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = Employee.TABLE_NAME)
public class Employee {
    public static final String TABLE_NAME = "Employee";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String role;

    @Enumerated(EnumType.STRING)
    private Department department;
    private Integer salary;
    private LocalDate startDate;

    public Employee(Long id, String name, String role, Department department, Integer salary, LocalDate startDate) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.department = department;
        this.salary = salary;
        this.startDate = startDate;
    }

    public Employee() {
    }

    public Employee(Long id, String name, String role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public Department getDepartment() {
        return department;
    }

    public Integer getSalary() {
        return salary;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
}

package com.socgen.employeeportal.entity;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "EMPLOYEE_TBL")
public class Employee {

    @Id
    @GeneratedValue
    private Long empId;
    private String firstName;
	private String lastName;
    private String gender;
    @JsonFormat(pattern="MM/dd/yyyy")
    private Date dob;
    private String department;

    public Employee(String firstName, String lastName, String gender, Date dob, String department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dob = dob;
        this.department = department;
    }

    public Employee() {
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return empId.equals(employee.empId) &&
                firstName.equals(employee.firstName) &&
                lastName.equals(employee.lastName) &&
                gender.equals(employee.gender) &&
                dob.equals(employee.dob) &&
                department.equals(employee.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empId, firstName, lastName, gender, dob, department);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empId=" + empId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                ", dob=" + dob +
                ", department='" + department + '\'' +
                '}';
    }
}

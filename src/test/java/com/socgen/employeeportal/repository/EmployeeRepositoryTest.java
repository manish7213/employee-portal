package com.socgen.employeeportal.repository;

import com.socgen.employeeportal.entity.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
public class EmployeeRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void testSaveNewEmployee() {
        entityManager.persist(new Employee("Manish_Test","LN_TEST","Male",new Date("01/01/2000"),"CSE"));

        Optional<Employee> employee = employeeRepository.findById(4L);

        assertThat(employee.get().getFirstName()).isEqualTo("Manish_Test");
    }

    @Test
    public void testListEmployees() {
        List<Employee> employees = (List<Employee>) employeeRepository.findAll();
        assertThat(employees).size().isGreaterThan(0);
    }

}
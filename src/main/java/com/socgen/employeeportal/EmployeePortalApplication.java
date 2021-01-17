package com.socgen.employeeportal;

import com.socgen.employeeportal.entity.Employee;
import com.socgen.employeeportal.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class EmployeePortalApplication {

	private final EmployeeRepository employeeRepository;

	public EmployeePortalApplication(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(EmployeePortalApplication.class, args);
	}


	@Bean
	CommandLineRunner runner() {
		return args -> {

			if (employeeRepository.findAll().isEmpty()) {
				Employee employee1 = new Employee("Manish1","Kumar1", "Male",new Date("01/05/1993"),"CSE");
				Employee employee2 = new Employee("Manish2","Kumar2", "Male",new Date("01/06/1993"),"ISE");
				Employee employee3 = new Employee("Manish3","Kumar3", "Male",new Date("01/07/1993"),"MECH");

				employeeRepository.save(employee1);
				employeeRepository.save(employee2);
				employeeRepository.save(employee3);
			}

		};
	}
}

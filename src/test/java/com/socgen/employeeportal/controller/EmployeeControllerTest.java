package com.socgen.employeeportal.controller;

import com.socgen.employeeportal.entity.Employee;
import com.socgen.employeeportal.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private EmployeeRepository repository;

    @Test
    public void givenEmployees_whenGetEmployees_thenReturnJsonArray()
            throws Exception {

        Employee emp = new Employee("alex","John","Male",new Date("01/01/1994"),"ISE");

        List<Employee> allEmployees = Arrays.asList(emp);

        given(repository.findAll()).willReturn(allEmployees);

        mvc.perform(get("/api/v1/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].firstName", is(emp.getFirstName())));
    }

    @Test
    public void createEmployee() throws Exception {
        Employee mockEmp = new Employee("alex","John","Male",new Date("01/01/1994"),"ISE");        // studentService.addCourse to respond back with mockCourse
        Mockito.when(repository.save(Mockito.any(Employee.class)))
                .thenReturn(mockEmp);

        String exampleJson = "{\"firstName\": \"alex\",\"lastName\": \"John\",\"gender\": \"Male\",\"dob\": \"01/01/1994\",\"department\": \"ISE\"}";
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("http://localhost:7070/api/v1/save")
                .accept(MediaType.APPLICATION_JSON).content(exampleJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());

    }

}
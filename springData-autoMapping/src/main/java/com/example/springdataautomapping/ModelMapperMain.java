package com.example.springdataautomapping;

import com.example.springdataautomapping.entities.Address;
import com.example.springdataautomapping.entities.Employee;
import com.example.springdataautomapping.entities.dto.EmployeeDTO;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

//@Component
public class ModelMapperMain implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {

        ModelMapper modelMapper = new ModelMapper();
        Address address = new Address("Varna", "Bulgaria");
        Employee employee = new Employee("Dimitar", BigDecimal.TEN, address);

        EmployeeDTO employeeDTO = modelMapper.map(employee, EmployeeDTO.class);

        System.out.println(employeeDTO);
    }
}

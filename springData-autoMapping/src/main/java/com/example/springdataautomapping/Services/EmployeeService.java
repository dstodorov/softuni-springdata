package com.example.springdataautomapping.Services;

import com.example.springdataautomapping.entities.Address;
import com.example.springdataautomapping.entities.Employee;
import com.example.springdataautomapping.entities.dto.CreateEmployeeDTO;
import com.example.springdataautomapping.entities.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
    Employee create(CreateEmployeeDTO createEmployeeDTO);

    List<EmployeeDTO> findAll();
}

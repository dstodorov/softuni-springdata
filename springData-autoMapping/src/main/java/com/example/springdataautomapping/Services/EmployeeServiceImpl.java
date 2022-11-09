package com.example.springdataautomapping.Services;

import com.example.springdataautomapping.entities.Address;
import com.example.springdataautomapping.entities.Employee;
import com.example.springdataautomapping.entities.dto.CreateEmployeeDTO;
import com.example.springdataautomapping.entities.dto.EmployeeDTO;
import com.example.springdataautomapping.repositories.AddressRepository;
import com.example.springdataautomapping.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final AddressRepository addressRepository;
    private final EmployeeRepository employeeRepository;
    private final ModelMapper mapper;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, AddressRepository addressRepository, ModelMapper mapper) {
        this.addressRepository = addressRepository;
        this.employeeRepository = employeeRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public Employee create(CreateEmployeeDTO employeeDTO) {
        ModelMapper mapper = new ModelMapper();

        Employee employee = mapper.map(employeeDTO, Employee.class);

        Optional<Address> address = this.addressRepository.findByCountryAndCity(employeeDTO.getAddress().getCountry(), employeeDTO.getAddress().getCity());
        address.ifPresent(employee::setAddress);

        return this.employeeRepository.save(employee);
    }

    @Override
    public List<EmployeeDTO> findAll() {
        return this.employeeRepository.findAll().stream().map(e -> mapper.map(e, EmployeeDTO.class)).collect(Collectors.toList());
    }
}

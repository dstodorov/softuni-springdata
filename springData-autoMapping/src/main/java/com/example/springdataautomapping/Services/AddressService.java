package com.example.springdataautomapping.Services;

import com.example.springdataautomapping.entities.Address;
import com.example.springdataautomapping.entities.dto.AddressDTO;

public interface AddressService {

    Address create(AddressDTO address);
}

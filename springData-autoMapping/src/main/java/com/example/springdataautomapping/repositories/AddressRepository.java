package com.example.springdataautomapping.repositories;

import com.example.springdataautomapping.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    Optional<Address> findByCountryAndCity(String country, String city);
}
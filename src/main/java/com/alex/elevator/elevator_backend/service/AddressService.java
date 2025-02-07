package com.alex.elevator.elevator_backend.service;

import com.alex.elevator.elevator_backend.entities.Address;
import com.alex.elevator.elevator_backend.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    private final AddressRepository addressRepository;


    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address saveAddress (Address address) {
        return addressRepository.save(address);
    }

}

package com.alex.elevator.elevator_backend.repository;

import com.alex.elevator.elevator_backend.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long>{

    List<Address> findByAreaLikeIgnoreCase(String area);
    List<Address> findByBuildingNameLikeIgnoreCase(String buildingName);
    List<Address> findByStreetAddressLikeIgnoreCase(String streetAddress);
    List<Address> findByCityLikeIgnoreCase(String city);
    List<Address> findByPostCodeLikeIgnoreCase(String postCode);
}

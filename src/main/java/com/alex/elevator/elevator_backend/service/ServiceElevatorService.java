package com.alex.elevator.elevator_backend.service;

import com.alex.elevator.elevator_backend.entities.Elevator;
import com.alex.elevator.elevator_backend.entities.ServiceElevator;
import com.alex.elevator.elevator_backend.repository.ServiceElevatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceElevatorService {

    private final ServiceElevatorRepository serviceElevatorRepository;


    @Autowired
    public ServiceElevatorService(ServiceElevatorRepository serviceElevatorRepository) {
        this.serviceElevatorRepository = serviceElevatorRepository;
    }

    public ServiceElevator saveServiceElevator (ServiceElevator serviceElevator) {
        return serviceElevatorRepository.save(serviceElevator);
    }

}

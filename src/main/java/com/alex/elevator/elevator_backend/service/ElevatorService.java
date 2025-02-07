package com.alex.elevator.elevator_backend.service;


import com.alex.elevator.elevator_backend.entities.Elevator;
import com.alex.elevator.elevator_backend.repository.ElevatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ElevatorService {

    private final ElevatorRepository elevatorRepository;


    @Autowired
    public ElevatorService(ElevatorRepository elevatorRepository) {
        this.elevatorRepository = elevatorRepository;
    }

    public Elevator saveElevator (Elevator elevator) {
        return elevatorRepository.save(elevator);
    }

}

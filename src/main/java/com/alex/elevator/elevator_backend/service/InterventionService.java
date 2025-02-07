package com.alex.elevator.elevator_backend.service;

import com.alex.elevator.elevator_backend.entities.Intervention;
import com.alex.elevator.elevator_backend.repository.InterventionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InterventionService {

    private final InterventionRepository interventionRepository;


    @Autowired
    public InterventionService(InterventionRepository interventionRepository) {
        this.interventionRepository = interventionRepository;
    }


    public Intervention saveIntervention (Intervention intervention) {
        return interventionRepository.save(intervention);
    }

}
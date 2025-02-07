package com.alex.elevator.elevator_backend.service;

import com.alex.elevator.elevator_backend.entities.SparePart;
import com.alex.elevator.elevator_backend.repository.SparePartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SparePartService {

    private final SparePartRepository sparePartRepository;


    @Autowired
    public SparePartService(SparePartRepository sparePartRepository) {
        this.sparePartRepository = sparePartRepository;
    }

    public SparePart saveSparePart (SparePart sparePart) {
        return sparePartRepository.save(sparePart);
    }

}

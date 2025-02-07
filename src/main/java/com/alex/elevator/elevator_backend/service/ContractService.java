package com.alex.elevator.elevator_backend.service;

import com.alex.elevator.elevator_backend.entities.Contract;
import com.alex.elevator.elevator_backend.repository.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContractService {

    private final ContractRepository contractRepository;


    @Autowired
    public ContractService(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    public Contract saveContract (Contract contract) {
        return contractRepository.save(contract);
    }

}

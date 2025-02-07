package com.alex.elevator.elevator_backend.service;

import com.alex.elevator.elevator_backend.entities.ContractType;
import com.alex.elevator.elevator_backend.repository.ContractTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContractTypeService {

    private final ContractTypeRepository contractTypeRepository;


    @Autowired
    public ContractTypeService(ContractTypeRepository contractTypeRepository) {
        this.contractTypeRepository = contractTypeRepository;
    }

    public ContractType saveContractType (ContractType contractType) {
        return contractTypeRepository.save(contractType);
    }

}

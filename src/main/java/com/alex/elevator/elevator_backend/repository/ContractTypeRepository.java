package com.alex.elevator.elevator_backend.repository;

import com.alex.elevator.elevator_backend.entities.ContractType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContractTypeRepository extends JpaRepository<ContractType, Long> {
//  Obtener todos los contratos según el tipo de contrato
    List<ContractType> findByContractTypeLikeIgnoreCase(String contractType);
//   Obtener el tipo de contrato buscando por el ID del contrato
    Optional<ContractType> findByContractsIdContract(Long idContract);
}

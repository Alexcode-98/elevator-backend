package com.alex.elevator.elevator_backend.repository;

import com.alex.elevator.elevator_backend.entities.Elevator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ElevatorRepository extends JpaRepository<Elevator, Long> {
//  Obtener el ascensor asociado al rae
    Optional<Elevator> findByRae(int rae);
//  Obtener los ascensores por el estado de este
    List<Elevator> findByStatusIgnoreCase(String status);
//  Obtener los ascensores por el tipo de ascensor
    List<Elevator> findByElevatorTypeIgnoreCase(String elevatorType);
//  Obtener los ascensores por el fabricante
    List<Elevator> findByMakerIgnoreCase(String maker);
//  Obtener los ascensores por el ID de contrato
    List<Elevator> findByContractIdContract(Long idContract);

}

package com.alex.elevator.elevator_backend.repository;


import com.alex.elevator.elevator_backend.entities.Contract;
import com.alex.elevator.elevator_backend.entities.Elevator;
import com.alex.elevator.elevator_backend.entities.Intervention;
import com.alex.elevator.elevator_backend.entities.ServiceElevator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ServiceElevatorRepository extends JpaRepository<ServiceElevator, Long> {
//  Obtener un servicio por su ID
    Optional<ServiceElevator> findByIdServiceElevator(Long idServiceElevator);
//  Obtener los servicios asociados a un ascensor por su rae
    List <ServiceElevator> findByElevatorRae(int rae);
//  Obtener los servicios asociados a un contrato por el ID del contrato
    List <ServiceElevator> findByContractIdContract(Long contractId);



}

package com.alex.elevator.elevator_backend.repository;


import com.alex.elevator.elevator_backend.entities.Intervention;
import org.springframework.data.jpa.repository.JpaRepository;
import com.alex.elevator.elevator_backend.entities.ServiceElevator;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface InterventionRepository extends JpaRepository<Intervention, Long> {
//  Obtener una intervencion por el ID
    Optional<Intervention> findByIdIntervention(Long idIntervention);
//  Obtener intervenciones que sean o no privadas
    List<Intervention>findByPrivateIntervention(boolean privateIntervention);
//  Obtener las intervenciones asociadas a un servicio por el ID del servicio
    List<Intervention> findByServiceElevatorIdServiceElevator(Long idServiceElevator);

//    List<Intervention> findByServiceElevator(ServiceElevator serviceElevator);

//    @Query("SELECT i FROM Intervention i WHERE i.idIntervention = :id")
//    List<Intervention> findCustom(@Param("id") Long idServiceElevator);



}

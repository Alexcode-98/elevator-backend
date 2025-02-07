package com.alex.elevator.elevator_backend.repository;


import com.alex.elevator.elevator_backend.entities.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContractRepository extends JpaRepository<Contract, Long> {
// Obtener un contrato por su ID
   Optional<Contract> findByIdContract(Long idContract);
// Obtener los contratos con que tengan o no permanencia
   List<Contract> findByPermanency(boolean permanency);
// Obtener los contratos por el usuario
   List<Contract> findByUserElevatorIdUser(Long idUser);
// Obtener el contrato de un ascensor por el rae
   Optional<Contract> findByElevatorsRae(int rae);
}

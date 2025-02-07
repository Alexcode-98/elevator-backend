package com.alex.elevator.elevator_backend.repository;


import com.alex.elevator.elevator_backend.entities.UserElevator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserElevatorRepository extends JpaRepository<UserElevator, Long> {
//  Obtener el usuario por su ID
    Optional<UserElevator> findByIdUser(Long idUser);
//  Obtener el usuario por su nombre de usuario
    Optional<UserElevator>findByUsername(String username);
//  Obterner los usuarios por su nombre completo
    List<UserElevator>findByFullNameLikeIgnoreCase(String fullName);
//  Obtener el usuario por su email
    Optional<UserElevator>findByEmail(String email);
//  Obtener el usuario por su número de teléfono
    Optional<UserElevator>findByPhone(String phone);
    // Verifica si un email ya existe en BD
    boolean existsByEmail(String email);
    // Verifica si un nombre de usuario ya existe en BD
    boolean existsByUsername(String username);
    // Verifica si un phone ya existe en BD
    boolean existsByPhone(String phone);

}

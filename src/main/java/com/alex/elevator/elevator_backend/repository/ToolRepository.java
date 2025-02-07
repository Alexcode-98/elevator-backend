package com.alex.elevator.elevator_backend.repository;


import com.alex.elevator.elevator_backend.entities.Tool;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ToolRepository extends JpaRepository<Tool, Integer> {
//  Obtener las herramientas por su estado
    List<Tool>findByStatus(boolean status);
//  Obtener las herramientas por si fecha de salida
    List<Tool>findByCheckoutDate(LocalDateTime checkoutDate);
//  Obtener las herramientas por su fabricante
    List<Tool>findByMakerLikeIgnoreCase(String maker);
//  Obtener las herramientas por su nombre
    List<Tool>findByToolNameLikeIgnoreCase(String toolName);
//  Obtener las herramientas por su referencia
    List<Tool>findByReference(String reference);
}

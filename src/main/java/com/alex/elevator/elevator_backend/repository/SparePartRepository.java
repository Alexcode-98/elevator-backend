package com.alex.elevator.elevator_backend.repository;


import com.alex.elevator.elevator_backend.entities.SparePart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SparePartRepository extends JpaRepository<SparePart, Integer> {
//  Obtener repuestos por su referencia
    List<SparePart> findByReference(String reference);
//  Obtener repuestos por su nombre
    List<SparePart> findBySparePartNameLikeIgnoreCase(String sparePartName);
//  Obtener repuesto por el tipo de repuesto
    List<SparePart> findBySparePartTypeLikeIgnoreCase(String sparePartType);
//  Obtener repuestos por su fabricante
    List<SparePart> findByMakerLikeIgnoreCase(String maker);

}

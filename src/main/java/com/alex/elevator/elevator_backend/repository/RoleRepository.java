package com.alex.elevator.elevator_backend.repository;


import com.alex.elevator.elevator_backend.entities.Role;
import com.alex.elevator.elevator_backend.security.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Integer> {
//  Obtener los Roles segÚn el tipo de rol (HAY QUE ARREGLARLO)
    List<Role> findByRoleType(String roleType);
}

package com.alex.elevator.elevator_backend.repository;


import com.alex.elevator.elevator_backend.entities.Permission;
import com.alex.elevator.elevator_backend.security.PermissionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
//  Obtener los permisos por el tipo de permiso (HAY QUE ARREGLARLO)
    List<Permission>findByPermissionType(PermissionType permissionType);
}

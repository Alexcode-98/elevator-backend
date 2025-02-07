package com.alex.elevator.elevator_backend.controller;

import com.alex.elevator.elevator_backend.entities.Permission;
import com.alex.elevator.elevator_backend.entities.Role;
import com.alex.elevator.elevator_backend.repository.PermissionRepository;
import com.alex.elevator.elevator_backend.security.PermissionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    PermissionRepository permissionRepository;
    //  PERMISSION
    @GetMapping("type")
    public ResponseEntity<List<HashMap<String, Object>>> processHttpGetRequestPermission(@RequestParam PermissionType permissionType) {

        List<Permission> permissions = permissionRepository.findByPermissionType(permissionType);

        List<HashMap<String, Object>> resultToShow = obtainPermission(permissions);

        return ResponseEntity.ok(resultToShow);
    }

    private List<HashMap<String, Object>> obtainPermission(List<Permission> permissions) {
        List<HashMap<String, Object>> resultToShow = new ArrayList<>();

        for (Permission permission : permissions) {
            HashMap<String, Object> permissionMap = new HashMap<>();

            permissionMap.put("permissionType", permission.getPermissionType());

            // Obtener los roles asociados al permiso
            List<HashMap<String, Object>> rolesList = new ArrayList<>();
            for (Role role : permission.getRoles()) {
                HashMap<String, Object> roleMap = new HashMap<>();
                roleMap.put("idRole", role.getIdRole());
                roleMap.put("roleType", role.getRoleType());
                rolesList.add(roleMap);
            }

            permissionMap.put("roles", rolesList);
            resultToShow.add(permissionMap);
        }

        return resultToShow;
    }
}

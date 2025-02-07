package com.alex.elevator.elevator_backend.controller;

import com.alex.elevator.elevator_backend.entities.Permission;
import com.alex.elevator.elevator_backend.entities.Role;
import com.alex.elevator.elevator_backend.repository.RoleRepository;
import com.alex.elevator.elevator_backend.security.PermissionType;
import com.alex.elevator.elevator_backend.security.RoleType;
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
@RequestMapping("/role")
public class RoleController {
    @Autowired
    RoleRepository roleRepository;
    //  PERMISSION
    @GetMapping("type")
    public ResponseEntity<List<HashMap<String, Object>>> processHttpGetRequestPermission(@RequestParam String roleType) {

        List<Role> roles = roleRepository.findByRoleType(roleType);

        List<HashMap<String, Object>> resultToShow = obtainRole(roles);

        return ResponseEntity.ok(resultToShow);
    }

    private List<HashMap<String, Object>> obtainRole(List<Role> roles) {
        List<HashMap<String, Object>> resultToShow = new ArrayList<>();

        for (Role role : roles) {
            HashMap<String, Object> roleMap = new HashMap<>();

            roleMap.put("roleType", role.getRoleType());

            // Obtener los roles asociados al permiso
            List<HashMap<String, Object>> permissionsList = new ArrayList<>();
            for (Permission permission : role.getPermissions()) {
                HashMap<String, Object> permissionMap = new HashMap<>();
                permissionMap.put("permissionId", permission.getIdPermission());
                permissionMap.put("permissionType", permission.getPermissionType());
                permissionsList.add(permissionMap);
            }

            roleMap.put("permission", permissionsList);
            resultToShow.add(roleMap);
        }

        return resultToShow;
    }
}

package com.alex.elevator.elevator_backend.entities;

import com.alex.elevator.elevator_backend.security.PermissionType;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

// Se crean la entidad contrato con todas sus relaciones necesarias
@Entity
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPermission;
    @Enumerated(EnumType.STRING) // Indica que se debe almacenar como cadena
    @Column(columnDefinition = "permission_type") // Indica que el tipo de columna es el ENUM definido
    private PermissionType permissionType;
    @ManyToMany
    @JoinTable(
            name = "role_permission",
            joinColumns = @JoinColumn(name = "id_permission"),
            inverseJoinColumns = @JoinColumn(name = "id_role")
    )
    //  Se crean los Getters and Setters de la entidad
    private Set<Role> roles = new HashSet<>();

    public Long getIdPermission() {
        return idPermission;
    }

    public void setIdPermission(Long idPermission) {
        this.idPermission = idPermission;
    }

    public PermissionType getPermissionType() {
        return permissionType;
    }

    public void setPermissionType(PermissionType permissionType) {
        this.permissionType = permissionType;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRols(Set<Role> roles) {
        this.roles = roles;
    }
}

package com.alex.elevator.elevator_backend.entities;

import com.alex.elevator.elevator_backend.security.RoleType;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Se crean la entidad contrato con todas sus relaciones necesarias
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRole;
    @OneToMany(mappedBy = "role")
    private List<Contract> contracts;
//    @Enumerated(EnumType.STRING) // Indica que se debe almacenar como cadena
    @Column(name = "role_type", columnDefinition = "role_type")
    private String roleType;
    @ManyToMany(mappedBy = "roles")
    private Set<Permission> permissions = new HashSet<>();
    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_role")
    )
    private Set<UserElevator> userElevators =new HashSet<UserElevator>();
    //  Se crean los Getters and Setters de la entidad
    public Long getIdRole() {
        return idRole;
    }

    public void setIdRole(Long idRole) {
        this.idRole = idRole;
    }

//    public RoleType getRoleType() {
//        return roleType;
//    }
//
//    public void setRoleType(RoleType roleType) {
//        this.roleType = roleType;
//    }


    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
    }

    public Set<UserElevator> getUserElevators() {
        return userElevators;
    }

    public void setUserElevators(Set<UserElevator> userElevators) {
        this.userElevators = userElevators;
    }
}

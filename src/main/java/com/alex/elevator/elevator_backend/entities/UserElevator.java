package com.alex.elevator.elevator_backend.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Se crean la entidad Usuarios con todas sus relaciones necesarias
@Entity
@Table(name = "user_elevator")
public class UserElevator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long idUser;
    private String username;
    private String password;
    private String email;
    private String fullName;
    private String address;
    private String phone;
    private LocalDate registrationDate;
    @ManyToMany(mappedBy = "userElevators")
    private Set<Role> roles = new HashSet<>();
    @ManyToMany(mappedBy = "userElevators")
    private Set<Intervention> interventions = new HashSet<>();
    @OneToMany
    @JsonManagedReference
    private List<Contract> contracts;

    //  Se crean los Getters and Setters de la entidad
    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
    }

    public Set<Intervention> getInterventions() {
        return interventions;
    }

    public void setInterventions(Set<Intervention> interventions) {
        this.interventions = interventions;
    }
}

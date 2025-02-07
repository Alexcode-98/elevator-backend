package com.alex.elevator.elevator_backend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;

import java.util.List;

// Se crean la entidad contrato con todas sus relaciones necesarias
@Entity
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idContract;
    private LocalDate startDate;
    private boolean permanency;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name ="id_role")
    private Role role;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "id_contract_type")
    private ContractType contractType;
    @OneToMany(mappedBy = "contract")
    @JsonManagedReference
    private List<Elevator> elevators;
    @OneToMany(mappedBy = "contract")
    @JsonManagedReference
    private List<ServiceElevator> serviceElevators;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "id_user")
    private UserElevator userElevator;

//  Se crean los Getters and Setters de la entidad
    public Long getIdContract() {
        return idContract;
    }

    public void setIdContract(Long idContract) {
        this.idContract = idContract;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public boolean isPermanency() {
        return permanency;
    }

    public void setPermanency(boolean permanency) {
        this.permanency = permanency;
    }

    public ContractType getContractType() {
        return contractType;
    }

    public void setContractType(ContractType contractType) {
        this.contractType = contractType;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Elevator> getElevators() {
        return elevators;
    }

    public void setElevators(List<Elevator> elevators) {
        elevators = elevators;
    }

    public List<ServiceElevator> getServiceElevators() {
        return serviceElevators;
    }

    public void setServiceElevators(List<ServiceElevator> serviceElevators) {
        this.serviceElevators = serviceElevators;
    }

    public UserElevator getUserElevator() {
        return userElevator;
    }

    public void setUserElevator(UserElevator userElevator) {
        this.userElevator = userElevator;
    }
}


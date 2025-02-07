package com.alex.elevator.elevator_backend.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

// Se crean la entidad Ascensores con todas sus relaciones necesarias
@Entity

public class Elevator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idElevator;
    @ManyToOne
    @JoinColumn(name = "id_Address")
    @JsonBackReference// Columna en la tabla Elevator que almacenará el ID de Address
    private Address address;
    private String maker;
    private int rae;
    private String status;
    private int stopNumbers;
    private String elevatorType;
    @ManyToOne
    @JoinColumn(name = "id_contract")
    @JsonBackReference
    private Contract contract;

    @OneToMany(mappedBy = "elevator")
    @JsonManagedReference
    private List<ServiceElevator> serviceElevators;

    //  Se crean los Getters and Setters de la entidad
    public Long getIdElevator() {
        return idElevator;
    }

    public void setIdElevator(Long idElevator) {
        this.idElevator = idElevator;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public int getRae() {
        return rae;
    }

    public void setRae(int rae) {
        this.rae = rae;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getStopNumbers() {
        return stopNumbers;
    }

    public void setStopNumbers(int stopNumbers) {
        this.stopNumbers = stopNumbers;
    }

    public String getElevatorType() {
        return elevatorType;
    }

    public void setElevatorType(String elevatorType) {
        this.elevatorType = elevatorType;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public List<ServiceElevator> getServiceElevators() {
        return serviceElevators;
    }

    public void setServiceElevators(List<ServiceElevator> serviceElevators) {
        this.serviceElevators = serviceElevators;
    }
}

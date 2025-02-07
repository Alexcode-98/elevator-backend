package com.alex.elevator.elevator_backend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

// Se crean la entidad contrato con todas sus relaciones necesarias
@Entity
@Table(name = "service")
public class ServiceElevator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_service")
    private Long idServiceElevator;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private double price;
    @ManyToOne
    @JoinColumn(name = "id_elevator")
    @JsonBackReference
    private Elevator elevator;
    @ManyToOne
    @JoinColumn(name = "id_contract")
    @JsonBackReference
    private Contract contract;

    @OneToMany(mappedBy = "serviceElevator")
    @JsonManagedReference
    private List<Intervention> interventions;
    //  Se crean los Getters and Setters de la entidad
    public Long getIdServiceElevator() {
        return idServiceElevator;
    }

    public void setIdServiceElevator(Long idServiceElevator) {
        this.idServiceElevator = idServiceElevator;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public Elevator getElevator() {
        return elevator;
    }

    public void setElevator(Elevator elevator) {
        this.elevator = elevator;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public List<Intervention> getInterventions() {
        return interventions;
    }

    public void setInterventions(List<Intervention> interventions) {
        this.interventions = interventions;
    }
}

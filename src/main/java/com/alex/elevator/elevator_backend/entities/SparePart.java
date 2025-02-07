package com.alex.elevator.elevator_backend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;


// Se crean la entidad contrato con todas sus relaciones necesarias
@Entity
public class SparePart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSparePart;
    private String reference;
    private String sparePartName;
    private String sparePartType;
    private String maker;
    private double price;
    private int unitsAvailable;
    private int unitsReserved;
    private String status;
    private String orderStatus;
    @ManyToOne
    @JoinColumn (name = "id_intervention")
    @JsonBackReference
    private Intervention intervention;
    //  Se crean los Getters and Setters de la entidad
    public Long getIdSparePart() {
        return idSparePart;
    }

    public void setIdSparePart(Long idSparePart) {
        this.idSparePart = idSparePart;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getSparePartName() {
        return sparePartName;
    }

    public void setSparePartName(String sparePartName) {
        this.sparePartName = sparePartName;
    }

    public String getSparePartType() {
        return sparePartType;
    }

    public void setSparePartType(String sparePartType) {
        this.sparePartType = sparePartType;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getUnitsAvailable() {
        return unitsAvailable;
    }

    public void setUnitsAvailable(int unitsAvailable) {
        this.unitsAvailable = unitsAvailable;
    }

    public int getUnitsReserved() {
        return unitsReserved;
    }

    public void setUnitsReserved(int unitsReserved) {
        this.unitsReserved = unitsReserved;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Intervention getIntervention() {
        return intervention;
    }

    public void setIntervention(Intervention intervention) {
        this.intervention = intervention;
    }
}

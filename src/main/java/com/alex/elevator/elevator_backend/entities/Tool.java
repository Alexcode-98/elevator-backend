package com.alex.elevator.elevator_backend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


// Se crean la entidad Herramientas con todas sus relaciones necesarias
@Entity

public class Tool {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTool;
    private String toolName;
    private int quantity;
    private boolean status;
    private LocalDate dateOfPurchase;
    private LocalDateTime checkoutDate;
    private LocalDateTime returnDate;
    private String maker;
    private double price;
    private String reference;
    @ManyToOne
    @JoinColumn(name = "id_intervention")
    @JsonBackReference
    private Intervention intervention;
    //  Se crean los Getters and Setters de la entidad
    public Long getIdTool() {
        return idTool;
    }

    public void setIdTool(Long idTool) {
        this.idTool = idTool;
    }

    public String getToolName() {
        return toolName;
    }

    public void setToolName(String toolName) {
        this.toolName = toolName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public LocalDate getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(LocalDate dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public LocalDateTime getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(LocalDateTime checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
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

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Intervention getIntervention() {
        return intervention;
    }

    public void setIntervention(Intervention intervention) {
        this.intervention = intervention;
    }
}

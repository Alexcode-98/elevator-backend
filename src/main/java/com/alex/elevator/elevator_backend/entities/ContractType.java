package com.alex.elevator.elevator_backend.entities;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

// Se crean la entidad Tipos de contrato con todas sus relaciones necesarias
@Entity
public class ContractType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idContractType;
    private String contractType;
    private Double price;


    @OneToMany(mappedBy = "contractType")
    @JsonManagedReference
    private List<Contract> contracts;
    //  Se crean los Getters and Setters de la entidad
    public Long getIdContractType() {
        return idContractType;
    }

    public void setIdContractType(Long idContractType) {
        this.idContractType = idContractType;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
    }


}

package com.alex.elevator.elevator_backend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Se crean la entidad contrato con todas sus relaciones necesarias
@Entity
public class Intervention {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id_intervention")
    private Long idIntervention;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String duration;
    private boolean privateIntervention;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_service")
    @JsonBackReference
    private ServiceElevator serviceElevator;

    @OneToMany(mappedBy = "intervention")
    @JsonManagedReference
    private List<SparePart> spareParts;
    @OneToMany(mappedBy = "intervention")
    @JsonManagedReference
    private List<Tool> tools;

    @ManyToMany
    @JoinTable(
            name = "user_intervention",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_intervention")
    )
    //  Se crean los Getters and Setters de la entidad
    private Set<UserElevator> userElevators =new HashSet<UserElevator>();

    public Long getIdIntervention() {
        return idIntervention;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setIdIntervention(Long idIntervention) {
        this.idIntervention = idIntervention;
    }

    public String getDuration() {
        return duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPrivateIntervention() {
        return privateIntervention;
    }

    public void setPrivateIntervention(boolean privateIntervention) {
        this.privateIntervention = privateIntervention;
    }

    public ServiceElevator getServiceElevator() {
        return serviceElevator;
    }

    public void setServiceElevator(ServiceElevator serviceElevator) {
        this.serviceElevator = serviceElevator;
    }

    public List<SparePart> getSpareParts() {
        return spareParts;
    }

    public void setSpareParts(List<SparePart> spareParts) {
        this.spareParts = spareParts;
    }

    public List<Tool> getTools() {
        return tools;
    }

    public void setTools(List<Tool> tools) {
        this.tools = tools;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Set<UserElevator> getUserElevators() {
        return userElevators;
    }

    public void setUserElevators(Set<UserElevator> userElevators) {
        this.userElevators = userElevators;
    }
}

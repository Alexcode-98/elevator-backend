package com.alex.elevator.elevator_backend.dtos;


public class ClientRegistered {
    private Long idUser;
    private String username;
    private String password;
    private String email;
    private String fullName;
    private String address;
    private String phone;
//    private LocalDate registrationDate;
//    @ManyToMany(mappedBy = "userElevators")
//    private Set<Role> roles = new HashSet<>();
//    @ManyToMany(mappedBy = "userElevators")
//    private Set<Intervention> interventions = new HashSet<>();
//    @OneToMany
//    @JsonManagedReference
//    private List<Contract> contracts;


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
}

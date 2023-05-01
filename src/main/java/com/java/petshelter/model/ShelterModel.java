package com.java.petshelter.model;

import jakarta.persistence.*;

@Entity
@Table(name = "shelter")
public class ShelterModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long petId;
    private String shelter_name;
    private String state;
    private String phone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPetId() {
        return petId;
    }

    public void setPetId(Long petId) {
        this.petId = petId;
    }

    public String getShelter_name() {
        return shelter_name;
    }

    public void setShelter_name(String shelter_name) {
        this.shelter_name = shelter_name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

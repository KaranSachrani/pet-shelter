package com.java.petshelter.model;

public class PetApplicationsDto {

    Long pet_id;
    String pet_name;
    String type;
    Integer age;
    Long user_id;
    String user_name;

    public PetApplicationsDto(Long user_id, Long pet_id, String first_name, String last_name, String pet_name, String type, Integer age) {
        this.user_id = user_id;
        this.pet_id = pet_id;
        this.user_name = first_name+" "+last_name;
        this.pet_name = pet_name;
        this.type = type;
        this.age = age;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public Long getPet_id() {
        return pet_id;
    }

    public void setPet_id(Long pet_id) {
        this.pet_id = pet_id;
    }

    public String getPet_name() {
        return pet_name;
    }

    public void setPet_name(String pet_name) {
        this.pet_name = pet_name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}

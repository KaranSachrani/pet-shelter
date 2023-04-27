package com.java.petshelter.repository;

import com.java.petshelter.model.PetModel;
import com.java.petshelter.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<PetModel,Long> {

    List<PetModel> findAll();
    PetModel save(UserModel userModel);
}

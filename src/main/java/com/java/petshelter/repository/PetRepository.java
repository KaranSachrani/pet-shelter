package com.java.petshelter.repository;

import com.java.petshelter.model.PetModel;
import com.java.petshelter.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Period;
import java.util.List;
import java.util.Optional;

@Repository
public interface PetRepository extends JpaRepository<PetModel,Long> {

    List<PetModel> findAll();
    PetModel save(UserModel userModel);
    List<PetModel> findByAdoptable(Boolean adoptable);
    List<PetModel> findByAgeOrType(Integer age, String type);
    Optional<Period> findById(Integer id);

}

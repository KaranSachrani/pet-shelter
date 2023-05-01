package com.java.petshelter.repository;

import com.java.petshelter.model.ShelterModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShelterRepository extends JpaRepository<ShelterModel,Long> {

    ShelterModel save(ShelterModel shelterModel);
    List<ShelterModel> findByPetId(Long id);
}

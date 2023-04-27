package com.java.petshelter.repository;

import com.java.petshelter.model.ShelterModel;
import com.java.petshelter.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShelterRepository extends JpaRepository<ShelterModel,Long> {
}

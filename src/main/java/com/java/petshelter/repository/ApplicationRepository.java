package com.java.petshelter.repository;

import com.java.petshelter.model.ApplicationModel;
import com.java.petshelter.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository<ApplicationModel,Long> {

    ApplicationModel save(ApplicationModel applicationModel);
    Optional<ApplicationModel> findByUserIdAndPetId(Long user_id,Long pet_id);
}

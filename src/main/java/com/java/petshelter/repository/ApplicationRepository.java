package com.java.petshelter.repository;

import com.java.petshelter.model.ApplicationModel;
import com.java.petshelter.model.PetApplicationsDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository<ApplicationModel,Long> {

    ApplicationModel save(ApplicationModel applicationModel);
    Optional<ApplicationModel> findByUserIdAndPetId(Long user_id,Long pet_id);
    Optional<ApplicationModel> findById(Long id);

    @Query("SELECT new com.java.petshelter.model.PetApplicationsDto(u.id, pet.id, u.first_name,u.last_name, pet.pet_name, pet.type, pet.age) FROM ApplicationModel app INNER JOIN  UserModel u ON app.userId = u.id INNER JOIN PetModel pet ON pet.id = app.petId ORDER BY app.petId ")
    List<PetApplicationsDto> getAllApplication();

    void deleteByPetId(Long pet_id);

    List<ApplicationModel> findByPetId(Long pet_id);
}

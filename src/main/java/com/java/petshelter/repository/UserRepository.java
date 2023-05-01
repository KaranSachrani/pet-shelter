package com.java.petshelter.repository;

import com.java.petshelter.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel,Long> {

    List<UserModel> findAll();
    UserModel save(UserModel userModel);
    UserModel findByEmail(String email);
    Optional<UserModel> findById(Long id);



}

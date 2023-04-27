package com.java.petshelter.repository;

import com.java.petshelter.model.ApplicationModel;
import com.java.petshelter.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends JpaRepository<ApplicationModel,Long> {
}

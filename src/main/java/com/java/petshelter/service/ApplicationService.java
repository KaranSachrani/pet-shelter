package com.java.petshelter.service;

import com.java.petshelter.model.ApplicationModel;
import com.java.petshelter.model.PetModel;
import com.java.petshelter.model.UserModel;
import com.java.petshelter.repository.ApplicationRepository;
import com.java.petshelter.repository.PetRepository;
import com.java.petshelter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class ApplicationService {


    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PetRepository petRepository;

    public String save(ApplicationModel applicationModel){
        /*if(applicationModel==null || applicationModel.getUser_id()==null || applicationModel.getPet_id()==null){
            return "Invalid Request.";
        }
        if(applicationModel.getUser_id()==0 || applicationModel.getPet_id()==0){
            return "Id cannot be 0.";
        }*/

        Optional<UserModel> userModel = userRepository.findById(applicationModel.getUserId());
        Optional<PetModel>  petModel = petRepository.findById(applicationModel.getPetId());

        if(!userModel.isPresent()){
            return "Invalid UserId.";
        }

        if(!petModel.isPresent()){
            return "Invalid PetId.";
        }

        Optional<ApplicationModel> applicationModel1 = applicationRepository.findByUserIdAndPetId(applicationModel.getUserId(), applicationModel.getPetId());
        if(!applicationModel1.isPresent()){
            return "Duplicate Application.";
        }


        applicationRepository.save(applicationModel);
        return "Record Saved Successfully.";
    }
}

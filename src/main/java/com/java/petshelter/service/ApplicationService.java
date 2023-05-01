package com.java.petshelter.service;

import com.java.petshelter.model.ApplicationModel;
import com.java.petshelter.model.PetModel;
import com.java.petshelter.model.UserModel;
import com.java.petshelter.repository.ApplicationRepository;
import com.java.petshelter.repository.PetRepository;
import com.java.petshelter.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        if(petModel.isPresent() && petModel.get().getAdoptable()==false){
            return "Sorry the PET with id: "+applicationModel.getPetId()+" is already adopted.";
        }

        Optional<ApplicationModel> applicationModel1 = applicationRepository.findByUserIdAndPetId(applicationModel.getUserId(), applicationModel.getPetId());
        if(applicationModel1.isPresent()){
            return "Application of User id: '"+applicationModel.getUserId()+"' for the Pet id: '"+applicationModel.getPetId()+"' already exist.";
        }

        applicationModel.setId(null);
        applicationRepository.save(applicationModel);
        return "Record Saved Successfully.";
    }


    public String update(ApplicationModel applicationModel){
        Optional<ApplicationModel> applicationModel1 = applicationRepository.findById(applicationModel.getId());
        Optional<UserModel> userModel = userRepository.findById(applicationModel.getUserId());
        Optional<PetModel>  petModel = petRepository.findById(applicationModel.getPetId());

        if(!applicationModel1.isPresent()){
            return "Invalid Application ID.";
        }

        if(!userModel.isPresent()){
            return "Invalid UserId.";
        }

        if(!petModel.isPresent()){
            return "Invalid PetId.";
        }

        Optional<ApplicationModel> applicationModel2 = applicationRepository.findByUserIdAndPetId(applicationModel.getUserId(), applicationModel.getPetId());
        if(applicationModel2.isPresent()){
            return "User id: '"+applicationModel.getUserId()+"' has already applied for the pet id: '"+applicationModel.getPetId()+"' with application id: '"+applicationModel2.get().getId()+"'.";
        }


        applicationRepository.save(applicationModel);
        return "Record Saved Successfully.";
    }

    public Object getAllApplications() {
        return applicationRepository.getAllApplication();
    }

    @Transactional
    public String delete(Long pet_id) {
        if(applicationRepository.findByPetId(pet_id).size()==0){
            return "Invalid Pet_id";
        }

        applicationRepository.deleteByPetId(pet_id);
        Optional<PetModel> petModel =  petRepository.findById(pet_id);
        if(petModel.isPresent()){
            petModel.get().setAdoptable(false);
            petRepository.save(petModel.get());
        }

        return "Applications for Pet_ID: "+pet_id+" has been deleted successfully and pet status has been updated to Adopted.";
    }
}

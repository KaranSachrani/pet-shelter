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


    //Receives the adoption application and save them into the application table
    public String save(ApplicationModel applicationModel){

        //Checks weather the user and pet exist in database or not, if not it return invalid parameter error.
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

        //Checks the request with the same user_id and pet_id should not made already, if yes then returns error msg.
        Optional<ApplicationModel> applicationModel1 = applicationRepository.findByUserIdAndPetId(applicationModel.getUserId(), applicationModel.getPetId());
        if(applicationModel1.isPresent()){
            return "Application of User id: '"+applicationModel.getUserId()+"' for the Pet id: '"+applicationModel.getPetId()+"' already exist.";
        }

        //Make ID null, otherwise if request contains the ID it will update the record with the passed id in db instead of inserting new record.
        applicationModel.setId(null);
        applicationRepository.save(applicationModel);
        return "Record Saved Successfully.";
    }


    //Receives the adoption request and update the record with passed id.
    public String update(ApplicationModel applicationModel){

        //checks weather the passed id, user_id and pet_id exist in relavent tables or not
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

        //Checks the request with the same user_id and pet_id should not made already, if yes then returns error msg
        Optional<ApplicationModel> applicationModel2 = applicationRepository.findByUserIdAndPetId(applicationModel.getUserId(), applicationModel.getPetId());
        if(applicationModel2.isPresent()){
            return "User id: '"+applicationModel.getUserId()+"' has already applied for the pet id: '"+applicationModel.getPetId()+"' with application id: '"+applicationModel2.get().getId()+"'.";
        }
        // here we are not making the id null becuase we need to update the record instead of inserting new, so we must pass id.
        //saves the record into db, after all validations
        applicationRepository.save(applicationModel);
        return "Record Saved Successfully.";
    }


    public Object getAllApplications() {
        return applicationRepository.getAllApplication();

    }

    //deletes the record with pet_id, we are using @transactional annotation here because delete operation is sensitive, so this annotation will make sure
    //either the operations fully completed or nothing at all.
    @Transactional
    public String delete(Long pet_id) {
        if(applicationRepository.findByPetId(pet_id).size()==0){
            return "Invalid Pet_id";
        }

        //deletes the application after successfull adoption
        applicationRepository.deleteByPetId(pet_id);

        //updates the adoptable attribute to false so that no one else can adopt this pet again
        Optional<PetModel> petModel =  petRepository.findById(pet_id);
        if(petModel.isPresent()){
            petModel.get().setAdoptable(false);
            petRepository.save(petModel.get());
        }

        return "Applications for Pet_ID: "+pet_id+" has been deleted successfully and pet status has been updated to Adopted.";
    }
}

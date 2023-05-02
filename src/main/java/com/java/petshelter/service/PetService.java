package com.java.petshelter.service;

import com.java.petshelter.model.PetModel;
import com.java.petshelter.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    public List<PetModel> getAllPets(){
        return petRepository.findAll();
    }

    //Receives the Pet object and save it into the Pet table
    public String save(PetModel petModel){

        //Performs the attribute validation, no attribute should be null or empty
        if(petModel==null || petModel.getPet_name()==null || petModel.getType()==null
                || petModel.getAge()==null){
            return "Invalid Request.";
        }

        if(petModel.getPet_name().isEmpty() || petModel.getType().isEmpty()){
            return "Empty Parameters found.";
        }

        //Saves it after validation
        petRepository.save(petModel);
        return "Record Saved Successfully.";
    }

    public List<PetModel> getAdoptablePets() {
        return petRepository.findByAdoptable(true);
    }

    public List<PetModel> getPetByAgeOrType(Integer age, String type) {
        return petRepository.findByAgeOrType(age,type);
    }
}

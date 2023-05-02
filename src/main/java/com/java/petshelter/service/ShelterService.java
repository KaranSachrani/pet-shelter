package com.java.petshelter.service;

import com.java.petshelter.model.ShelterModel;
import com.java.petshelter.repository.ShelterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShelterService {

    @Autowired
    ShelterRepository shelterRepository;

    //Receives the Pet object and save it into the Pet table
    public String create(ShelterModel shelterModel) {

        //Performs validation for null and empty attributes
        if(shelterModel==null || shelterModel.getPetId()==null || shelterModel.getShelter_name()==null
                || shelterModel.getPhone()==null || shelterModel.getState()==null){
            return "Invalid Request.";
        }

        if(shelterModel.getShelter_name().isEmpty() || shelterModel.getPhone().isEmpty()
            || shelterModel.getState().isEmpty() || shelterModel.getPetId()==0){
            return "Empty Parameters found.";
        }

        //Sets id null becasue we need to insert it as new record not update
        shelterModel.setId(null);
        //finaly saves the object into table
        shelterRepository.save(shelterModel);
        return "Shelter Created Successfully.";
    }

    public List<ShelterModel> getByPetId(Long id) {
        return shelterRepository.findByPetId(id);
    }
}

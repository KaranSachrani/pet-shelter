package com.java.petshelter.service;

import com.java.petshelter.model.UserModel;
import com.java.petshelter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserModel> getAllUsers(){
        return userRepository.findAll();
    }

    //Receives the User object and save it into the User table
    public String save(UserModel userModel){

        //Performs the validation for null and empty attributes
        if(userModel==null || userModel.getEmail()==null || userModel.getFirst_name()==null
                || userModel.getLast_name()==null){
            return "Invalid Request.";
        }

        //checks weather someone else have the same email address
        if(userRepository.findByEmail(userModel.getEmail())!=null){
            return "Email Address Already Exist.";
        }

        if(userModel.getEmail().isEmpty() || userModel.getFirst_name().isEmpty() || userModel.getLast_name().isEmpty()){
            return "Empty Parameters found.";
        }

        //saves the record into the table
        userRepository.save(userModel);
        return "Record Saved Successfully.";
    }

    //Receives the User model object in request and update the record with passed id.
    public String update(UserModel userModel){

        //performs the validation for null and empty attributes
        if(userModel==null || userModel.getId()==null
                || userModel.getFirst_name()==null
                || userModel.getLast_name()==null){
            return "Invalid Request.";
        }

        if(userModel.getFirst_name().isEmpty() || userModel.getLast_name().isEmpty()){
            return "Empty Parameters found.";
        }

        //first fetch the object from db to update
        Optional<UserModel> userModel1 = userRepository.findById(userModel.getId());
        if(!userModel1.isPresent()){
            return "Invalid User_Id.";
        }

        //updates the attributes of fetched object
        userModel1.get().setFirst_name(userModel.getFirst_name());
        userModel1.get().setLast_name(userModel.getLast_name());

        // finally save the updated object into the db, the fetched object already have the id so it will update the record instead of inserting new
        userRepository.save(userModel1.get()).toString();
        return "Record Updated Successfully.";
    }
}

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

    public String save(UserModel userModel){
        if(userModel==null || userModel.getEmail()==null || userModel.getFirst_name()==null
                || userModel.getLast_name()==null){
            return "Invalid Request.";
        }
        if(userRepository.findByEmail(userModel.getEmail())!=null){
            return "Email Address Already Exist.";
        }

        if(userModel.getEmail().isEmpty() || userModel.getFirst_name().isEmpty() || userModel.getLast_name().isEmpty()){
            return "Empty Parameters found.";
        }
        userRepository.save(userModel).toString();
        return "Record Saved Successfully.";
    }

    public String update(UserModel userModel){
        if(userModel==null || userModel.getId()==null
                || userModel.getFirst_name()==null
                || userModel.getLast_name()==null){
            return "Invalid Request.";
        }

        if(userModel.getFirst_name().isEmpty() || userModel.getLast_name().isEmpty()){
            return "Empty Parameters found.";
        }

        Optional<UserModel> userModel1 = userRepository.findById(userModel.getId());
        if(!userModel1.isPresent()){
            return "Invalid User_Id.";
        }

        userModel1.get().setFirst_name(userModel.getFirst_name());
        userModel1.get().setLast_name(userModel.getLast_name());
        userRepository.save(userModel1.get()).toString();
        return "Record Updated Successfully.";
    }
}

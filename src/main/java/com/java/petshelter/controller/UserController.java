package com.java.petshelter.controller;

import com.java.petshelter.model.UserModel;
import com.java.petshelter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/get/all")
    public ResponseEntity<Object> getUsers(){
        return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Object> saveUser(@RequestBody UserModel userModel){
        String response= userService.save(userModel);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PutMapping("update")
    public ResponseEntity<Object> updateUser(@RequestBody UserModel userModel){
        String response= userService.update(userModel);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}

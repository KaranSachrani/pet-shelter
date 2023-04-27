package com.java.petshelter.controller;

import com.java.petshelter.model.PetModel;
import com.java.petshelter.model.UserModel;
import com.java.petshelter.service.PetService;
import com.java.petshelter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private PetService petService;

    @GetMapping("/get/all")
    public ResponseEntity<Object> getPets(){
        return new ResponseEntity<>(petService.getAllPets(), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Object> savePet(@RequestBody PetModel petModel){
        String response=petService.save(petModel);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}

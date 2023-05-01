package com.java.petshelter.controller;

import com.java.petshelter.model.ShelterModel;
import com.java.petshelter.service.ShelterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shelter")
public class ShelterController {

    @Autowired
    ShelterService shelterService;

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody ShelterModel shelterModel){
        String response=shelterService.create(shelterModel);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/getByPetId")
    public ResponseEntity<Object> getByPetId(@RequestParam Long pet_id){
        return new ResponseEntity<>(shelterService.getByPetId(pet_id), HttpStatus.OK);
    }
}

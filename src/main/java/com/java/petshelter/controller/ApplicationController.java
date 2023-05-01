package com.java.petshelter.controller;

import com.java.petshelter.model.ApplicationModel;
import com.java.petshelter.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/application")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;


    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody ApplicationModel applicationModel){
        String response= applicationService.save(applicationModel);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Object> update(@RequestBody ApplicationModel applicationModel){
        String response= applicationService.update(applicationModel);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get/all")
    public ResponseEntity<Object> getAllApplications(){
        return new ResponseEntity<>(applicationService.getAllApplications(), HttpStatus.OK);
    }

    @DeleteMapping("/adoptPet")
    public ResponseEntity<Object> delete(@RequestParam Long pet_id){
        String response= applicationService.delete(pet_id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}

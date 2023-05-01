package com.java.petshelter.controller;

import com.java.petshelter.model.ApplicationModel;
import com.java.petshelter.model.UserModel;
import com.java.petshelter.service.ApplicationService;
import com.java.petshelter.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

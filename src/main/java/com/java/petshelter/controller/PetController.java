package com.java.petshelter.controller;

import com.java.petshelter.model.PetModel;
import com.java.petshelter.service.PetService;
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

    @GetMapping("/get/adoptable")
    public ResponseEntity<Object> getAdoptablePets(){
        return new ResponseEntity<>(petService.getAdoptablePets(), HttpStatus.OK);
    }

    @GetMapping("/getbyAgeType")
    public ResponseEntity<Object> getPetByAgeOrType(@RequestParam Integer age, String type){
        return new ResponseEntity<>(petService.getPetByAgeOrType(age,type), HttpStatus.OK);
    }
}

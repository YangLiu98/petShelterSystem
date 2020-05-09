package com.example.demo.controller;

import com.example.demo.entity.Pet;
import com.example.demo.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pet")
public class PetController {


    @Autowired
    private PetService petService;

    @PostMapping("insert")
    public ResponseEntity<String> signUp(@RequestBody Pet pet){
        System.out.println(pet);
        return petService.insertPet(pet);
    }
}

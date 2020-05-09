package com.example.demo.service;

import com.example.demo.entity.Pet;
import org.springframework.http.ResponseEntity;

public interface PetService {

    //插入宠物
    public ResponseEntity<String> insertPet( Pet pet);
}

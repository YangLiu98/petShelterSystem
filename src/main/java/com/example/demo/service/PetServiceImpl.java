package com.example.demo.service;

import com.example.demo.dao.PetDao;
import com.example.demo.entity.Pet;
import com.example.demo.util.response.ResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class PetServiceImpl implements PetService {

    @Autowired
    private PetDao petDao;
    @Override
    public ResponseEntity<String> insertPet(Pet pet) {
        int count=petDao.insert(pet);
        if(count==0){
            return ResponseFactory.badRequest("Failed to insert");
        }
        return ResponseFactory.success("Sucess to insert");
    }
}

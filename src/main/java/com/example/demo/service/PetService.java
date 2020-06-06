package com.example.demo.service;

import com.example.demo.entity.Pet;
import com.example.demo.entity.TagSet;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface PetService {

    //插入宠物
    public ResponseEntity<String> insertPet( Pet pet);

    //获取宠物信息
    public ResponseEntity<Pet> getDetial(Pet pet);

    //获取宠物列表
    public ResponseEntity<List<Map<String,Object>>> getAll();

    //精确查找
    public ResponseEntity<List<Map<String,Object>>> select(Pet pet);

    //Tag查找
    public List<Map<String,Object>> selectByTag(TagSet tagSet);

    //更新宠物信息
    public ResponseEntity<String> update(Pet pet);
}

package com.example.demo.dao;

import com.example.demo.entity.Pet;
import com.example.demo.entity.TagSet;


import java.util.List;
import java.util.Map;

public interface PetDao {
    public int insert(Pet pet);

    public Pet selectPetByPetId(Pet pet);

    public List<Map<String,Object>> selectAll();

    public List<Map<String,Object>> select(Pet pet);

    public List<Map<String,Object>> selectByTag(TagSet tagSet);
}

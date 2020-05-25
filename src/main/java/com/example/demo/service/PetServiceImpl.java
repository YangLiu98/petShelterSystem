package com.example.demo.service;

import com.example.demo.dao.PetDao;
import com.example.demo.entity.Pet;
import com.example.demo.entity.TagSet;
import com.example.demo.util.response.ResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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

    @Override
    public ResponseEntity<Pet> getDetial(Pet pet) {
        Pet temp=petDao.selectPetByPetId(pet);
        if(temp!=null){
            return ResponseFactory.success(temp);
        };
        return ResponseFactory.badRequest(null);
    }

    @Override
    public ResponseEntity<List<Map<String,Object>>> getAll() {
        return ResponseFactory.success(petDao.selectAll());
    }

    @Override
    public ResponseEntity<List<Map<String,Object>>> select(Pet pet) {
        return ResponseFactory.success(petDao.select(pet));
    }

    @Override
    public ResponseEntity<List<Map<String, Object>>> selectByTag(TagSet tagSet) {
        List<Map<String, Object>> temp=petDao.selectByTag(tagSet);
        System.out.println(temp.size());
        ArrayList<Map<String, Object>> result=new ArrayList<>();
        for (Map<String,Object> map:temp
             ) {
            int containNum=0;
            if(tagSet.getTag1()!=null&&map.get("tag").toString().contains(tagSet.getTag1())){
                containNum++;
            }
            if(tagSet.getTag2()!=null&&map.get("tag").toString().contains(tagSet.getTag2())){
                containNum++;
            }
            if(tagSet.getTag3()!=null&&map.get("tag").toString().contains(tagSet.getTag3())){
                containNum++;
            }
            if(tagSet.getTag4()!=null&&map.get("tag").toString().contains(tagSet.getTag4())){
                containNum++;
            }
            if(tagSet.getTag5()!=null&&map.get("tag").toString().contains(tagSet.getTag5())){
                containNum++;
            }
            map.put("containNum",containNum);
            result.add(map);
        }
        temp.clear();
        int position[]=new int[6];
        for(int i = 0 ; i < result.size() ; i++) {
               for(int k=5;k>=0;k--){
                   if(result.get(i).get("containNum").toString().equals(k+"")){
                       temp.add(position[k],result.get(i));
                       for(int j=k-1;j>=0;j--){
                           position[j]++;
                       }
                       continue;
                   }
               }
               result.get(i).remove("containNum");
        }
        return ResponseFactory.success(temp);
    }
}
